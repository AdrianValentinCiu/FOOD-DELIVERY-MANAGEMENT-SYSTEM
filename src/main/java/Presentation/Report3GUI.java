package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Report3GUI extends JFrame {

    private final JTextField textNrofTimes;
    private final JTextField textAmount;
    private final JButton btnGenerateReport;

    public Report3GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textNrofTimes = new JTextField();
        textNrofTimes.setColumns(10);
        textNrofTimes.setBounds(211, 33, 104, 32);
        contentPane.add(textNrofTimes);

        JLabel lblNumberOfTimes = new JLabel("Number of Times");
        lblNumberOfTimes.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        lblNumberOfTimes.setBounds(21, 33, 192, 32);
        contentPane.add(lblNumberOfTimes);

        textAmount = new JTextField();
        textAmount.setColumns(10);
        textAmount.setBounds(211, 84, 104, 32);
        contentPane.add(textAmount);

        JLabel lbAmount = new JLabel("Amount");
        lbAmount.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        lbAmount.setBounds(21, 84, 192, 32);
        contentPane.add(lbAmount);

        btnGenerateReport = new JButton("Generate");
        btnGenerateReport.setBounds(211, 134, 101, 32);
        contentPane.add(btnGenerateReport);
    }

    public void btnGenerateReport(ActionListener action) { this.btnGenerateReport.addActionListener(action); }

    public String getTextNrofTimes() {
        return textNrofTimes.getText();
    }

    public String getTextAmount() {
        return textAmount.getText();
    }
}
