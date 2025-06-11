import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener {

    JTextField amountField;
    JButton depositBtn, backBtn;
    String cardno;

    Deposit(String cardno) {
        this.cardno = cardno;

        setTitle("Deposit");
        setLayout(null);

        JLabel label = new JLabel("Enter Amount to Deposit:");
        label.setBounds(50, 50, 200, 30);
        add(label);

        amountField = new JTextField();
        amountField.setBounds(250, 50, 150, 30);
        add(amountField);

        depositBtn = new JButton("Deposit");
        depositBtn.setBounds(100, 120, 100, 30);
        depositBtn.addActionListener(this);
        add(depositBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(220, 120, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        setSize(450, 250);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == depositBtn) {
            String amount = amountField.getText();
            if (amount.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter amount");
            } else {
                try {
                    Conn c = new Conn();
                    String q = "INSERT INTO bank (card_no, type, amount) VALUES ('" + cardno + "', 'Deposit', " + amount
                            + ")";
                    c.s.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
                    setVisible(false);
                    new Transactions(cardno).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new Transactions(cardno).setVisible(true);
        }
    }
}
