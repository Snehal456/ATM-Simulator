// where users can register a new Card Number and PIN.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {

    JTextField cardField;
    JPasswordField pinField, confirmPinField;
    JButton registerButton, backButton;

    Signup() {
        setTitle("Register New User");

        JLabel cardLabel = new JLabel("Card Number:");
        cardLabel.setBounds(50, 40, 150, 30);
        add(cardLabel);

        cardField = new JTextField();
        cardField.setBounds(200, 40, 200, 30);
        add(cardField);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(50, 90, 150, 30);
        add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(200, 90, 200, 30);
        add(pinField);

        JLabel confirmPinLabel = new JLabel("Confirm PIN:");
        confirmPinLabel.setBounds(50, 140, 150, 30);
        add(confirmPinLabel);

        confirmPinField = new JPasswordField();
        confirmPinField.setBounds(200, 140, 200, 30);
        add(confirmPinField);

        registerButton = new JButton("Register");
        registerButton.setBounds(100, 200, 100, 30);
        registerButton.addActionListener(this);
        add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(220, 200, 100, 30);
        backButton.addActionListener(this);
        add(backButton);

        setLayout(null);
        setSize(450, 300);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == registerButton) {
            String card = cardField.getText();
            String pin = new String(pinField.getPassword());
            String confirm = new String(confirmPinField.getPassword());

            if (card.equals("") || pin.equals("") || confirm.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields required");
                return;
            }

            if (!pin.equals(confirm)) {
                JOptionPane.showMessageDialog(null, "PINs do not match");
                return;
            }

            try {
                Conn c = new Conn();
                String q = "INSERT INTO login (card_no, pin) VALUES ('" + card + "', '" + pin + "')";
                c.s.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Account Registered Successfully");
                setVisible(false);
                new Login().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Login().setVisible(true);
        }
    }
}
