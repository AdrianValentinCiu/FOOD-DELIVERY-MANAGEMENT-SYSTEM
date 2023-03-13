package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Report2GUI extends JFrame {

    private final JTextField textNumberofTimes;
    private final JButton btnGenerateReport;

    public Report2GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNumberOfTimes = new JLabel("Number of Times");
        lblNumberOfTimes.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        lblNumberOfTimes.setBounds(10, 44, 192, 32);
        contentPane.add(lblNumberOfTimes);

        textNumberofTimes = new JTextField();
        textNumberofTimes.setColumns(10);
        textNumberofTimes.setBounds(200, 44, 104, 32);
        contentPane.add(textNumberofTimes);

        btnGenerateReport = new JButton("Generate");
        btnGenerateReport.setBounds(203, 110, 101, 32);
        contentPane.add(btnGenerateReport);
    }

    public void btnGenerateReport(ActionListener action) { this.btnGenerateReport.addActionListener(action); }

    public String getTextNumberofTimes() {
        return textNumberofTimes.getText();
    }
}
