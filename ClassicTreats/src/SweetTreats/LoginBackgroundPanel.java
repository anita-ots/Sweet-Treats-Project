package SweetTreats;

import javax.swing.*;
import java.awt.*;

public class LoginBackgroundPanel extends JPanel {
    private Image backgroundImage;

    public LoginBackgroundPanel(String imagePath) {
        try {
            backgroundImage = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout()); // or whatever layout you need
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}