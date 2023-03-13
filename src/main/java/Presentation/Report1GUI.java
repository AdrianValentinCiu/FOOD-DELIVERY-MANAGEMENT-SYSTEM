package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Report1GUI extends JFrame {

    private final JTextField textStartHour;
    private final JTextField textFinishHour;
    private final JButton btnGenerateReport;

    public Report1GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbStartHour = new JLabel("Start Hour");
        lbStartHour.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        lbStartHour.setBounds(10, 58, 111, 32);
        contentPane.add(lbStartHour);

        JLabel lbFinishHour = new JLabel("Finish Hour");
        lbFinishHour.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        lbFinishHour.setBounds(10, 101, 111, 32);
        contentPane.add(lbFinishHour);

        textStartHour = new JTextField();
        textStartHour.setBounds(151, 61, 104, 32);
        contentPane.add(textStartHour);
        textStartHour.setColumns(10);

        textFinishHour = new JTextField();
        textFinishHour.setColumns(10);
        textFinishHour.setBounds(151, 101, 104, 32);
        contentPane.add(textFinishHour);

        btnGenerateReport = new JButton("Generate");
        btnGenerateReport.setBounds(154, 161, 101, 32);
        contentPane.add(btnGenerateReport);
    }

    public void btnGenerateReport(ActionListener action) { this.btnGenerateReport.addActionListener(action); }

    public String getTextStartHour() {
        return textStartHour.getText();
    }

    public String getTextFinishHour() {
        return textFinishHour.getText();
    }
}

