package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelectLogInGUI extends JFrame {
    private final JButton btnAdministrator;
    private final JButton btnClient;
    private final JButton btnEmployee;

    public SelectLogInGUI() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 433, 330);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setLayout(null);
        JLabel lbLogIn = new JLabel("Select Log In");
        lbLogIn.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lbLogIn.setBounds(160, 11, 135, 44);
        contentPane.add(lbLogIn);
        this.btnAdministrator = new JButton("Administrator");
        this.btnAdministrator.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        this.btnAdministrator.setBounds(144, 66, 143, 34);
        contentPane.add(this.btnAdministrator);
        JFormattedTextField formattedTextField = new JFormattedTextField();
        formattedTextField.setBounds(224, 75, 7, 20);
        contentPane.add(formattedTextField);
        this.btnClient = new JButton("Client");
        this.btnClient.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        this.btnClient.setBounds(144, 122, 143, 34);
        contentPane.add(this.btnClient);
        this.btnEmployee = new JButton("Employee");
        this.btnEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        this.btnEmployee.setBounds(144, 178, 143, 34);
        contentPane.add(this.btnEmployee);
    }

    public void btnAdministrator(ActionListener action) {
        this.btnAdministrator.addActionListener(action);
    }

    public void btnClient(ActionListener action) {
        this.btnClient.addActionListener(action);
    }

    public void btnEmployee(ActionListener action) {
        this.btnEmployee.addActionListener(action);
    }
}
