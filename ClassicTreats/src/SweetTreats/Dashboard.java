
package SweetTreats;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Dashboard extends JFrame {

    private JPanel panel;
    private Connection conn;
    private String username;   

private java.util.Map<String, int[]> customerCart = new java.util.HashMap<>();

    public Dashboard(String role, String username) {
    this.username = username; 
        setTitle(role + " Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        
    
        connectToDatabase();
panel = new ImagePanel("C:\\Users\\Anita\\Downloads\\pexels-guvo59-21790554.jpg");
panel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 20)); // ← tighter spacing
panel.setBorder(BorderFactory.createEmptyBorder(120, 80, 80, 80)); // ← pushes buttons down and inward so they sit nicely centered
  
add(panel, BorderLayout.CENTER);


setupButtons(role);  

setVisible(true);

    }
    
    
  
// VIEW ALL RECEIPTS (admin vi

public void viewAllReceipts() {
    String sql = "SELECT username, product_name, quantity, price, purchase_date FROM receipts ORDER BY username, purchase_date";

    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        
        java.util.Map<String, java.util.Map<String, java.util.List<String>>> allReceipts = new java.util.LinkedHashMap<>();

        while (rs.next()) {
            String username = rs.getString("username");
            String date = rs.getTimestamp("purchase_date").toLocalDateTime().toLocalDate().toString();
            String productLine = rs.getString("product_name") + " x" + rs.getInt("quantity") +
                                 " - P" + String.format("%.2f", rs.getDouble("price"));

            allReceipts
                .computeIfAbsent(username, k -> new java.util.LinkedHashMap<>())
                .computeIfAbsent(date, k -> new java.util.ArrayList<>())
                .add(productLine);
        }

        if (allReceipts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No receipts found.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (String user : allReceipts.keySet()) {
            sb.append("Customer: ").append(user).append("\n");
            java.util.Map<String, java.util.List<String>> userReceipts = allReceipts.get(user);
            for (String date : userReceipts.keySet()) {
                sb.append("  Date: ").append(date).append("\n");
                for (String line : userReceipts.get(date)) {
                    sb.append("    ").append(line).append("\n");
                }
            }
            
            sb.append("\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString());

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching receipts: " + e.getMessage());
    }
}

    
    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sweet_treats", "root", "your_password_here");
            System.out.println("Database connected.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed. Please check your connection settings.");
        }
    }

    private void setupButtons(String role) {
        switch (role.toLowerCase()) {
           case "admin":
    addButton("Manage Users");             
    
    addButton("Inventory / Low Stock");    
    addButton("Product Management");        
    addButton("View Reports");   
    addButton("View All Receipts");
                
        
                
                break;
            case "manager":
                addButton("Add Product");
                addButton("Delete Product");
                addButton("Update Product");
                addButton("Search Product");
                addButton("View Inventory");
                break;
            case "salesperson":
                addButton("Search Product");
                addButton("View Inventory");
                addButton("Report Low Stock");
                break;
            case "customer":
                addButton("Search Product");
                addButton("Purchase Product");
                addButton("View Receipts");
                addButton("View Inventory");
                break;
            default:
                JOptionPane.showMessageDialog(this, "No buttons configured for role: " + role);
       
       
        }
   
     addExitButton();
    addHomeButton();
    
    
    }
    
    
    private void addHomeButton() {
    JButton homeButton = new JButton("🏠 Home");
    homeButton.setPreferredSize(new Dimension(200, 45));
    homeButton.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
    homeButton.setBackground(new Color(101, 67, 33)); // chocolate brown
    homeButton.setForeground(Color.WHITE);
    homeButton.setBorder(BorderFactory.createLineBorder(new Color(193, 120, 23), 2, true));
    homeButton.setFocusPainted(false);
    homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    homeButton.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to go back to Login?", 
            "Back to Login", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            LoginSystem loginFrame = new LoginSystem();
            loginFrame.setVisible(true);
            loginFrame.setLocationRelativeTo(null);
            this.dispose();
        }
    });
    
    panel.add(homeButton);
}

private void addExitButton() {
    JButton exitButton = new JButton("✖ Exit");
    exitButton.setPreferredSize(new Dimension(200, 45));
    exitButton.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
    exitButton.setBackground(new Color(169, 50, 50)); // ← red to stand out
    exitButton.setForeground(Color.WHITE);
    exitButton.setBorder(BorderFactory.createLineBorder(new Color(200, 50, 50), 2, true));
    exitButton.setFocusPainted(false);
    exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    exitButton.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to exit?", 
            "Exit", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    });
    
    panel.add(exitButton);
}
    
    
    
    
    
    
    
    
    
    
    
    

    private void addButton(String text) {
      JButton button = new JButton(text);
    button.setPreferredSize(new Dimension(200, 45)); // ← slimmer height (was 80)
    button.setFont(new Font("Gill Sans MT", Font.BOLD, 18));// ← same font, slightly smaller
    button.setBackground(new Color(101, 67, 33)); // ← chocolate brown
    button.setForeground(Color.WHITE);
    button.setBorder(BorderFactory.createLineBorder(new Color(193, 120, 23), 2, true)); // ← caramel border, thinner
    button.setFocusPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));  

        panel.add(button);

        button.addActionListener(e -> handleButtonClick(text));
    }

   private void handleButtonClick(String action) {
    switch (action) {
        case "Add Product":
            addProduct();
            break;
        case "Delete Product":
            deleteProduct();
            break;
        case "Update Product":
            updateProduct();
            break;
        case "Search Product":
            searchProduct();
            break;
        case "View Inventory":
            viewInventory();
            break;
        case "Report Low Stock":
            reportLowStock();
            break;
        case "Purchase Product":
            purchaseProduct();
            break;
        case "View Receipts":
            viewReceipts();
            break;
            
        case "View All Receipts":
            viewAllReceipts();
            break;
            
            
        case "Manage Users":
            manageUsers();
            break;
            

            
        case "View Reports":
            viewReports();
            break;

        // ADD THESE TWO:
        case "Inventory / Low Stock":
            inventoryOptions();  // Shows the popup for view inventory, low stock, restock
            break;

        case "Product Management":
            productManagementOptions();  // Shows the popup for Add/Delete/Update/Search
            break;

        default:
            JOptionPane.showMessageDialog(this, "No functionality yet for: " + action);
    }
}

   private void addProduct() {
    JFrame addProductFrame = new JFrame("Add Product");
    addProductFrame.setSize(400, 300);
    addProductFrame.setLayout(new GridLayout(5, 2));
    addProductFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JTextField nameField = new JTextField();
    JTextField descriptionField = new JTextField();
    JTextField priceField = new JTextField();
    JTextField quantityField = new JTextField();

    JButton saveButton = new JButton("Save Product");
    saveButton.addActionListener(e -> {
        try {
            String productName = nameField.getText().trim();
            
            // ← CHECK IF PRODUCT ALREADY EXISTS FIRST
            String checkQuery = "SELECT COUNT(*) FROM products WHERE LOWER(product_name) = LOWER(?)";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, productName);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            
            if (count > 0) {
                // ← PRODUCT ALREADY EXISTS, STOP HERE
                JOptionPane.showMessageDialog(addProductFrame, 
                    "Product '" + productName + "' already exists! Use Update Product to change it.");
                return;
            }
            
            // ← ONLY INSERTS IF PRODUCT DOESNT EXIST
            String query = "INSERT INTO products (product_name, description, price, quantity) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, productName);
                stmt.setString(2, descriptionField.getText());
                stmt.setDouble(3, Double.parseDouble(priceField.getText()));
                stmt.setInt(4, Integer.parseInt(quantityField.getText()));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(addProductFrame, "Product added successfully!");
                addProductFrame.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(addProductFrame, "Error: " + ex.getMessage());
        }
    });

    addProductFrame.add(new JLabel("Product Name:"));
    addProductFrame.add(nameField);
    addProductFrame.add(new JLabel("Description:"));
    addProductFrame.add(descriptionField);
    addProductFrame.add(new JLabel("Price: P"));
    addProductFrame.add(priceField);
    addProductFrame.add(new JLabel("Quantity:"));
    addProductFrame.add(quantityField);
    addProductFrame.add(new JLabel());
    addProductFrame.add(saveButton);

    addProductFrame.setVisible(true);
}
 
   
   
   
   
   
private void inventoryOptions() {
    String[] options = {"View Inventory", "View Low Stock", "Restock Product"};
    int choice = JOptionPane.showOptionDialog(this, "Select action:", "Inventory Options",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

    switch(choice) {
        case 0: viewInventory(); break;
        case 1: lowStockReports(); break;
        case 2: restockProduct(); break;
    }
}


public void viewReceipts() {
    String sql = "SELECT product_name, quantity, price, purchase_date FROM receipts WHERE username = ?";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();

        StringBuilder sb = new StringBuilder();
        sb.append("Purchase History for ").append(username).append(":\n\n");

        while (rs.next()) {
            sb.append("Product: ").append(rs.getString("product_name")).append("\n");
            sb.append("Quantity: ").append(rs.getInt("quantity")).append("\n");
           sb.append("Price: P").append(String.format("%.2f", rs.getDouble("price"))).append("\n");
            sb.append("Date: ").append(rs.getTimestamp("purchase_date")).append("\n");
            sb.append("-----------------------------\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString());

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching receipts: " + e.getMessage());
    }
}





private void productManagementOptions() {
    String[] options = {"Add Product", "Delete Product", "Update Product", "Search Product"};
    int choice = JOptionPane.showOptionDialog(this, "Select action:", "Product Management",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

    switch(choice) {
        case 0: addProduct(); break;
        case 1: deleteProduct(); break;
        case 2: updateProduct(); break;
        case 3: searchProduct(); break;
    }
}

private void restockProduct() {
    try {
        String product = JOptionPane.showInputDialog(this, "Enter product name to restock:");
        if(product != null && !product.isEmpty()){
            String qtyStr = JOptionPane.showInputDialog(this, "Enter quantity to add:");
            int qty = Integer.parseInt(qtyStr);

            String updateQuery = "UPDATE products SET quantity = quantity + ? WHERE product_name = ?";
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            stmt.setInt(1, qty);
            stmt.setString(2, product);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Product restocked successfully!");
        }
    } catch(Exception e){
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}



    // Delete Product
    private void deleteProduct() {
        String name = JOptionPane.showInputDialog(this, "Enter product name to delete:");
        if (name != null && !name.isEmpty()) {
            try {
                String query = "DELETE FROM products WHERE product_name = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, name);
                    int rows = stmt.executeUpdate();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Product not found.");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    // Update Product
    private void updateProduct() {
        String name = JOptionPane.showInputDialog(this, "Enter product name to update:");
        if (name != null && !name.isEmpty()) {
            try {
                String query = "SELECT * FROM products WHERE product_name = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, name);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String newDescription = JOptionPane.showInputDialog(this, "Enter new description:", rs.getString("description"));
                        String newPrice = JOptionPane.showInputDialog(this, "Enter new price:", rs.getDouble("price"));
                        String newQuantity = JOptionPane.showInputDialog(this, "Enter new quantity:", rs.getInt("quantity"));

                        String updateQuery = "UPDATE products SET description = ?, price = ?, quantity = ? WHERE product_name = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                            updateStmt.setString(1, newDescription);
                            updateStmt.setDouble(2, Double.parseDouble(newPrice));
                            updateStmt.setInt(3, Integer.parseInt(newQuantity));
                            updateStmt.setString(4, name);
                            updateStmt.executeUpdate();
                            JOptionPane.showMessageDialog(this, "Product updated successfully!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Product not found.");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    // Search Product
    private void searchProduct() {
        String name = JOptionPane.showInputDialog(this, "Enter product name to search:");
        if (name != null && !name.isEmpty()) {
            try {
                String query = "SELECT * FROM products WHERE product_name = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, name);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String info = "Name: " + rs.getString("product_name") +
                                "\nDescription: " + rs.getString("description") +
                                "\nPrice: " + rs.getDouble("price") +
                                "\nQuantity: " + rs.getInt("quantity");
                        JOptionPane.showMessageDialog(this, info);
                    } else {
                        JOptionPane.showMessageDialog(this, "Product not found.");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    // View Inventory
    private void viewInventory() {
        try {
            String query = "SELECT * FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            StringBuilder inventory = new StringBuilder();
            while (rs.next()) {
                inventory.append(rs.getString("product_name"))
                        .append(" - Qty: ")
                        .append(rs.getInt("quantity"))
                        .append(", Price:P")
                        .append(rs.getDouble("price"))
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, inventory.length() > 0 ? inventory.toString() : "No products available.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Report Low Stock
    private void reportLowStock() {
        try {
            String query = "SELECT * FROM products WHERE quantity <= 5";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            StringBuilder lowStock = new StringBuilder();
            while (rs.next()) {
                lowStock.append(rs.getString("product_name"))
                        .append(" - Only ")
                        .append(rs.getInt("quantity"))
                        .append(" left!\n");
            }
            JOptionPane.showMessageDialog(this, lowStock.length() > 0 ? lowStock.toString() : "No low stock products.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    
    

private void purchaseProduct() {
    while(true){
        String productName = JOptionPane.showInputDialog(this, "Enter product name to purchase (Cancel to finish):");
        if(productName == null || productName.isEmpty()) break;  // stop adding

        try {
            String query = "SELECT * FROM products WHERE product_name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int availableQty = rs.getInt("quantity");
                String qtyStr = JOptionPane.showInputDialog(this,
                        "Enter quantity to purchase (Available: " + availableQty + "):");
                int qty = Integer.parseInt(qtyStr);

                if(qty <= 0){
                    JOptionPane.showMessageDialog(this, "Invalid quantity.");
                    continue;
                }
                if(qty > availableQty){
                    JOptionPane.showMessageDialog(this, "Not enough stock available.");
                    continue;
                }

                double price = rs.getDouble("price");
                // Add to cart: sum if already exists
                if(customerCart.containsKey(productName)){
                    int[] prev = customerCart.get(productName);
                    prev[0] += qty;  // quantity
                } else {
                    customerCart.put(productName, new int[]{qty, (int)(price*100)});
                }

                int option = JOptionPane.showConfirmDialog(this, "Add more products?", "Continue Shopping",
                        JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.NO_OPTION) break;  // stop adding

            } else {
                JOptionPane.showMessageDialog(this, "Product not found.");
            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    if(!customerCart.isEmpty()) checkout(); // proceed to checkout
}

private void checkout() {
    while(true) {
        StringBuilder cartText = new StringBuilder("Your Cart:\n");
        double total = 0.0;

        for(String product : customerCart.keySet()){
            int[] info = customerCart.get(product);
            double price = info[1]/100.0;
            cartText.append(product)
                    .append(" - Qty: ").append(info[0])
                    .append(", Price each:P").append(price)
                    .append("\n");
            total += info[0]*price;
        }
        cartText.append("Total: ").append(total);

        int option = JOptionPane.showConfirmDialog(this, cartText + "\nConfirm purchase?",
                "Checkout", JOptionPane.YES_NO_OPTION);

        if(option == JOptionPane.YES_OPTION){
            try{
                // Reduce stock and create receipt in DB
                for(String product : customerCart.keySet()){
                    int[] info = customerCart.get(product);
                    int qty = info[0];

                    String updateQuery = "UPDATE products SET quantity = quantity - ? WHERE product_name = ?";
                    PreparedStatement stmt = conn.prepareStatement(updateQuery);
                    stmt.setInt(1, qty);
                    stmt.setString(2, product);
                    stmt.executeUpdate();
                }

                
                for(String product : customerCart.keySet()) {
    int[] info = customerCart.get(product);
    saveReceipt(product, info[0], info[1]/100.0);  // pass product, quantity, price
}
                customerCart.clear();
                JOptionPane.showMessageDialog(this, "Purchase successful! Receipt saved.");
                break;

            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else {
            // Ask if user wants to remove items
            int removeOption = JOptionPane.showConfirmDialog(this,
                    "Do you want to remove an item before confirming?", "Edit Cart", JOptionPane.YES_NO_OPTION);
            if(removeOption == JOptionPane.YES_OPTION){
                String removeProduct = JOptionPane.showInputDialog(this, "Enter product name to remove:");
                if(removeProduct != null && customerCart.containsKey(removeProduct)){
                    customerCart.remove(removeProduct);
                }
            } else {
                break; // exit checkout
            }
        }
    }
}


public void saveReceipt(String productName, int quantity, double price) {
    String sql = "INSERT INTO receipts (username, product_name, quantity, price) VALUES (?, ?, ?, ?)";

    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);       // logged in user
        stmt.setString(2, productName);
        stmt.setInt(3, quantity);
        stmt.setDouble(4, price);

        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Receipt saved successfully!");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error saving receipt: " + e.getMessage());
    }
}



private void manageUsers() {
    JFrame userFrame = new JFrame("Manage Users");
    userFrame.setSize(500, 400);
    userFrame.setLayout(new GridLayout(6, 2, 10, 10));
    userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    userFrame.setLocationRelativeTo(null);

    JTextField usernameField = new JTextField();
    JTextField passwordField = new JTextField();
    String[] roles = {"Admin", "Manager", "Salesperson", "Customer"};
    JComboBox<String> roleCombo = new JComboBox<>(roles);

    JButton addUserButton = new JButton("Add User");
    JButton deleteUserButton = new JButton("Delete User");
    JButton viewUsersButton = new JButton("View Users");

    // ADD USER
    addUserButton.addActionListener(e -> {
        String uname = usernameField.getText().trim();
        String pword = passwordField.getText().trim();
        String role = (String) roleCombo.getSelectedItem();

        if (uname.isEmpty() || pword.isEmpty()) {
            JOptionPane.showMessageDialog(userFrame, "All fields are required!");
            return;
        }

        try {
            // Use subquery to get role_id from roles table
            String query = "INSERT INTO users (username, password, role_id) " +
                           "VALUES (?, ?, (SELECT role_id FROM roles WHERE LOWER(role_name) = LOWER(?)))";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, uname);
            stmt.setString(2, pword);
            stmt.setString(3, role);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(userFrame, "User added successfully!");
                usernameField.setText("");
                passwordField.setText("");
            } else {
                JOptionPane.showMessageDialog(userFrame, "Failed to add user. Check role name.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(userFrame, "Error adding user: " + ex.getMessage());
        }
    });

    // DELETE USER
    deleteUserButton.addActionListener(e -> {
        String delUser = JOptionPane.showInputDialog(userFrame, "Enter username to delete:");
        if (delUser != null && !delUser.isEmpty()) {
            try {
                String query = "DELETE FROM users WHERE LOWER(username) = LOWER(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, delUser);
                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(userFrame, "User deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(userFrame, "User not found.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(userFrame, "Error deleting user: " + ex.getMessage());
            }
        }
    });

    // VIEW USERS
    viewUsersButton.addActionListener(e -> {
        try {
            // Join users with roles table to get role name
            String query = "SELECT u.username, r.role_name FROM users u " +
                           "LEFT JOIN roles r ON u.role_id = r.role_id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            JTextArea textArea = new JTextArea(15, 30);
            textArea.setEditable(false);
            StringBuilder sb = new StringBuilder("Registered Users:\n\n");

            while (rs.next()) {
                sb.append("Username: ").append(rs.getString("username"))
                  .append(" - Role: ").append(rs.getString("role_name"))
                  .append("\n");
            }

            textArea.setText(sb.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(userFrame, scrollPane, "Registered Users", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(userFrame, "Error fetching users: " + ex.getMessage());
        }
    });

    // ADD COMPONENTS
    userFrame.add(new JLabel("Username:"));
    userFrame.add(usernameField);
    userFrame.add(new JLabel("Password:"));
    userFrame.add(passwordField);
    userFrame.add(new JLabel("Role:"));
    userFrame.add(roleCombo);
    userFrame.add(addUserButton);
    userFrame.add(deleteUserButton);
    userFrame.add(new JLabel());
    userFrame.add(viewUsersButton);

    userFrame.setVisible(true);
}


















































  
    

    // View Reports
    private void viewReports() {
        try {
            String query = "SELECT product_name, price, quantity FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            StringBuilder report = new StringBuilder();
            while (rs.next()) {
                report.append(rs.getString("product_name"))
                        .append(" - Price:P")
                        .append(rs.getDouble("price"))
                        .append(", Quantity: ")
                        .append(rs.getInt("quantity")) 
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, report.length() > 0 ? report.toString() : "No data available.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); 
        }
    }

 private void lowStockReports() {
        try {
            String query = "SELECT product_name, quantity FROM products WHERE quantity <= 5";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            StringBuilder lowStock = new StringBuilder();
            while (rs.next()) {
                lowStock.append(rs.getString("product_name"))
                        .append(" - Qty: ")
                        .append(rs.getInt("quantity"))
                        .append("\n");
            }

            if(lowStock.length() == 0){
                JOptionPane.showMessageDialog(this, "No low stock products.");
                return;
            }

            String product = JOptionPane.showInputDialog(this,
                    "Low stock products:\n" + lowStock +
                    "\nEnter product name to restock (or cancel to just view):");

            if(product != null && !product.isEmpty()){
                String qtyStr = JOptionPane.showInputDialog(this, "Enter quantity to add:");
                int qty = Integer.parseInt(qtyStr);

                String updateQuery = "UPDATE products SET quantity = quantity + ? WHERE product_name = ?";
                PreparedStatement stmt2 = conn.prepareStatement(updateQuery);
                stmt2.setInt(1, qty);
                stmt2.setString(2, product);
                stmt2.executeUpdate();

                JOptionPane.showMessageDialog(this, "Product restocked successfully!");
            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }







}

