
package SweetTreats;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.*;

public class LoginSystem extends javax.swing.JFrame {

private Connection conn;

    public LoginSystem() {
        connectToDatabase();
        initComponents();
        
        
        
        LoginBackgroundPanel loginBg = new LoginBackgroundPanel("C:\\Users\\Anita\\Downloads\\pexels-feelartfeelant-1028714.jpg");
getContentPane().add(loginBg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 538));
setSize(960, 538);
setLocationRelativeTo(null);
setVisible(true); 
        
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
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        users = new javax.swing.JTextField();
        roles = new javax.swing.JComboBox<>();
        password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        login1 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("WELCOME!!(:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 260, -1));

        users.setFont(new java.awt.Font("Gill Sans MT", 2, 18)); // NOI18N
        users.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersActionPerformed(evt);
            }
        });
        getContentPane().add(users, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 170, 30));

        roles.setFont(new java.awt.Font("Gill Sans MT", 2, 18)); // NOI18N
        roles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Admin", "Manager", "Salesperson", "Customer" }));
        roles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        roles.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        roles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rolesActionPerformed(evt);
            }
        });
        getContentPane().add(roles, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 170, 30));

        password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 170, 30));

        jLabel2.setBackground(new java.awt.Color(221, 119, 69));
        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 3, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 120, 30));

        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 3, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Roles:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 90, 30));

        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 3, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 100, 30));

        login1.setBackground(new java.awt.Color(169, 50, 50));
        login1.setFont(new java.awt.Font("Gill Sans MT", 2, 18)); // NOI18N
        login1.setForeground(new java.awt.Color(255, 255, 255));
        login1.setText("Login");
        login1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(169, 50, 50), 3, true));
        login1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login1ActionPerformed(evt);
            }
        });
        getContentPane().add(login1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 90, 30));

        jButton1.setBackground(new java.awt.Color(169, 50, 50));
        jButton1.setFont(new java.awt.Font("Gill Sans MT", 2, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Sign Up");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 90, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void rolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rolesActionPerformed
    String selectedRole = (String) roles.getSelectedItem();

// Prevent errors if the first blank option is selected
if (selectedRole == null || selectedRole.trim().isEmpty()) {
    System.out.println("No role selected yet.");
    users.setEnabled(true); 
    return; // stop here
}

System.out.println("Role selected: " + selectedRole);

// UI checks
if (selectedRole.equals("Customer")) {
    users.setEnabled(false);
} else {
    users.setEnabled(true);
}
   
    }//GEN-LAST:event_rolesActionPerformed

    private void usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersActionPerformed
      

                                        

    }//GEN-LAST:event_usersActionPerformed

    private void login1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login1ActionPerformed
                                        
 
    String usernameInput = users.getText().trim();  // Get and trim the username
    String passwordInput = new String(password.getPassword());  // Get the password
    String roleInput = roles.getSelectedItem().toString();  // Get the role input

    // Debugging: Print out the values
    System.out.println("Username input: [" + usernameInput + "]");
    System.out.println("Password input: [" + passwordInput + "]");
    System.out.println("Role selected: " + roleInput);

    // Check if the username is still the placeholder text ("Username") or is empty
    if (usernameInput.equals("Username") || usernameInput.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a valid username.");
        return;  // Exit early if the username is invalid
    }

    // SQL query to match username, password, and role
    String query = "SELECT * FROM users WHERE LOWER(username) = LOWER(?) AND LOWER(password) = LOWER(?) AND role_id = (SELECT role_id FROM roles WHERE LOWER(role_name) = LOWER(?))";

    PreparedStatement stmt = null;
    ResultSet rs = null;

    // Debugging: Print the query and parameters before executing it
    System.out.println("Query: " + query);
    System.out.println("Username: " + usernameInput);
    System.out.println("Password: " + passwordInput);
    System.out.println("Role: " + roleInput);

    try {
        
        stmt = conn.prepareStatement(query);

        
        stmt.setString(1, usernameInput);  // Bind username
        stmt.setString(2, passwordInput);  // Bind password
        stmt.setString(3, roleInput);      // Bind role

        
        rs = stmt.executeQuery();

      
        if (rs.next()) {
            // Successful login
            System.out.println("Login successful!");
            JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + roleInput);
            
           Dashboard dash = new Dashboard(roleInput, usernameInput);
dash.setVisible(true);
dispose();
            
            
            
            
            

        } else {
            
            JOptionPane.showMessageDialog(this, "Invalid username, password, or role.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while processing the login.");
    } finally {
        // Ensure resources are closed after use
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

                                            

    }//GEN-LAST:event_login1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

                                        
   SignUpSystem signUpFrame = new SignUpSystem();
    signUpFrame.setVisible(true);
    signUpFrame.pack();
    signUpFrame.setLocationRelativeTo(null);
    this.dispose();




    }//GEN-LAST:event_jButton1ActionPerformed

   public static void main(String args[]) {
    //Set the Nimbus look and feel 
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(LoginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(LoginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(LoginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(LoginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    // Create and display the form 
    java.awt.EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            new LoginSystem().setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JToggleButton login1;
    private javax.swing.JPasswordField password;
    private javax.swing.JComboBox<String> roles;
    private javax.swing.JTextField users;
    // End of variables declaration//GEN-END:variables
}