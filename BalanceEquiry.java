import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton backBtn;
    String cardno;

    BalanceEnquiry(String cardno) {
        this.cardno = cardno;

        setTitle("Balance Enquiry");
        setLayout(null);

        JLabel label = new JLabel("Your Current Balance:");
        label.setBounds(50, 50, 200, 30);
        add(label);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setBounds(250, 50, 200, 30);
        add(balanceLabel);

        double balance = 0;
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT type, amount FROM bank WHERE card_no = '" + cardno + "'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += rs.getDouble("amount");
                } else {
                    balance -= rs.getDouble("amount");
                }
            }
            balanceLabel.setText("Rs. " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        backBtn = new JButton("Back");
        backBtn.setBounds(150, 120, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        setSize(450, 250);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(cardno).setVisible(true);
    }
}
