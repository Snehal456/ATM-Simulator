// his will show the GUI login screen and call the Conn class to validate login credentials from the database.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField tf1;
    JPasswordField pf1;
    JButton b1, b2, b3; // Added Register button

    Login() {
        super("ATM Login");

        l1 = new JLabel("Card No:");
        l1.setBounds(50, 40, 100, 30);
        add(l1);

        l2 = new JLabel("PIN:");
        l2.setBounds(50, 90, 100, 30);
        add(l2);

        tf1 = new JTextField();
        tf1.setBounds(150, 40, 200, 30);
        add(tf1);

        pf1 = new JPasswordField();
        pf1.setBounds(150, 90, 200, 30);
        add(pf1);

        b1 = new JButton("Sign In");
        b1.setBounds(50, 150, 90, 30);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Clear");
        b2.setBounds(150, 150, 90, 30);
        b2.addActionListener(this);
        add(b2);

        b3 = new JButton("Register");
        b3.setBounds(250, 150, 100, 30);
        b3.addActionListener(this);
        add(b3);

        setLayout(null);
        setSize(420, 280);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { // Sign In
            try {
                String cardno = tf1.getText();
                String pin = new String(pf1.getPassword());

                Conn c1 = new Conn();
                String q = "SELECT * FROM login WHERE card_no = '" + cardno + "' AND pin = '" + pin + "'";
                ResultSet rs = c1.s.executeQuery(q);

                if (rs.next()) {
                    setVisible(false);
                    new Transactions(cardno).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card No or PIN");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == b2) { // Clear
            tf1.setText("");
            pf1.setText("");

        } else if (ae.getSource() == b3) { // Register
            setVisible(false);
            new Signup().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
