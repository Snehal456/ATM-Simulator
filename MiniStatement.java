import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    MiniStatement(String cardno) {
        setTitle("Mini Statement");

        JLabel heading = new JLabel("Last 5 Transactions:");
        heading.setFont(new Font("System", Font.BOLD, 16));
        heading.setBounds(20, 10, 300, 30);
        add(heading);

        JTextArea area = new JTextArea();
        area.setFont(new Font("System", Font.PLAIN, 14));
        area.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBounds(20, 50, 350, 180);
        add(scrollPane);

        try {
            Conn c = new Conn();
            String q = "SELECT * FROM bank WHERE card_no = '" + cardno + "' ORDER BY date DESC LIMIT 5";
            ResultSet rs = c.s.executeQuery(q);

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("Date: ").append(rs.getString("date"))
                  .append(" | Type: ").append(rs.getString("type"))
                  .append(" | Amount: ₹").append(rs.getString("amount"))
                  .append("\n");
            }

            area.setText(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
            area.setText("Error retrieving mini statement.");
        }

        setLayout(null);
        setSize(400, 300);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
