import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField amountField;
    JButton withdrawBtn, backBtn;
    String cardno;

    Withdrawl(String cardno) {
        this.cardno = cardno;

        setTitle("Withdraw");
        setLayout(null);

        JLabel label = new JLabel("Enter Amount to Withdraw:");
        label.setBounds(50, 50, 200, 30);
        add(label);

        amountField = new JTextField();
        amountField.setBounds(250, 50, 150, 30);
        add(amountField);

        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(100, 120, 100, 30);
        withdrawBtn.addActionListener(this);
        add(withdrawBtn);

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
        if (ae.getSource() == withdrawBtn) {
            String amount = amountField.getText();
            if (amount.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter amount");
            } else {
                try {
                    Conn c = new Conn();

                    // Calculate current balance
                    ResultSet rs = c.s.executeQuery("SELECT type, amount FROM bank WHERE card_no = '" + cardno + "'");
                    double balance = 0;
                    while (rs.next()) {
                        if (rs.getString("type").equals("Deposit")) {
                            balance += rs.getDouble("amount");
                        } else {
                            balance -= rs.getDouble("amount");
                        }
                    }

                    double withdrawAmount = Double.parseDouble(amount);
                    if (withdrawAmount > balance) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    } else {
                        String q = "INSERT INTO bank (card_no, type, amount) VALUES ('" + cardno + "', 'Withdraw', "
                                + withdrawAmount + ")";
                        c.s.executeUpdate(q);
                        JOptionPane.showMessageDialog(null, "Rs. " + amount + " Withdrawn Successfully");
                        setVisible(false);
                        new Transactions(cardno).setVisible(true);
                    }

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
