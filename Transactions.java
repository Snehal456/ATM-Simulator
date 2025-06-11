// this is the main dashboard after login where users can choose:
// Deposit
// Withdraw
// Balance Enquiry
// Exit
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {

    JButton depositBtn, withdrawBtn, balanceBtn, miniStmtBtn, exitBtn;
    String cardno;

    Transactions(String cardno) {
        this.cardno = cardno;

        setTitle("ATM Transactions");
        setLayout(null);

        JLabel label = new JLabel("Select Transaction");
        label.setFont(new Font("System", Font.BOLD, 20));
        label.setBounds(100, 30, 300, 30);
        add(label);

        depositBtn = new JButton("Deposit");
        depositBtn.setBounds(100, 80, 150, 30);
        depositBtn.addActionListener(this);
        add(depositBtn);

        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(100, 120, 150, 30);
        withdrawBtn.addActionListener(this);
        add(withdrawBtn);

        balanceBtn = new JButton("Balance Enquiry");
        balanceBtn.setBounds(100, 160, 150, 30);
        balanceBtn.addActionListener(this);
        add(balanceBtn);

        miniStmtBtn = new JButton("Mini Statement");
        miniStmtBtn.setBounds(100, 200, 150, 30);
        miniStmtBtn.addActionListener(this);
        add(miniStmtBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(100, 240, 150, 30);
        exitBtn.addActionListener(this);
        add(exitBtn);

        setSize(400, 350);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == depositBtn) {
            setVisible(false);
            new Deposit(cardno).setVisible(true);
        } else if (ae.getSource() == withdrawBtn) {
            setVisible(false);
            new Withdrawl(cardno).setVisible(true);
        } else if (ae.getSource() == balanceBtn) {
            setVisible(false);
            new BalanceEnquiry(cardno).setVisible(true);
        } else if (ae.getSource() == miniStmtBtn) {
            setVisible(false);
            new MiniStatement(cardno).setVisible(true);
        } else if (ae.getSource() == exitBtn) {
            JOptionPane.showMessageDialog(null, "Thank you for using ATM!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Transactions("123456789");  // For testing purpose
    }
}
