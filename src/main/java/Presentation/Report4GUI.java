package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Report4GUI extends JFrame {

    private final JTextField textDay;
    private final JButton btnGenerateReport;

    public Report4GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnGenerateReport = new JButton("Generate");
        btnGenerateReport.setBounds(217, 138, 101, 32);
        contentPane.add(btnGenerateReport);

        JLabel lbDay = new JLabel("Day");
        lbDay.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        lbDay.setBounds(24, 80, 192, 32);
        contentPane.add(lbDay);

        textDay = new JTextField();
        textDay.setColumns(10);
        textDay.setBounds(214, 80, 104, 32);
        contentPane.add(textDay);
    }

    public void btnGenerateReport(ActionListener action) { this.btnGenerateReport.addActionListener(action); }

    public String getTextDay() {
        return textDay.getText();
    }
}
