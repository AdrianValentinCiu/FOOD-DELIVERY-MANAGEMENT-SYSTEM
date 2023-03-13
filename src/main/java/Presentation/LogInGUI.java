package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInGUI extends JFrame {

    private final JTextField textUserName;
    private final JPasswordField textUserPassword;

    private final JButton btnRegister;
    private final JButton btnGoBack;
    private final JButton btnLogIn;

    public LogInGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 481, 351);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel logIn = new JLabel("Log In");
        logIn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        logIn.setBounds(193, 40, 85, 52);
        contentPane.add(logIn);

        JLabel lbUserName = new JLabel("User Name:");
        lbUserName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbUserName.setBounds(29, 119, 103, 30);
        contentPane.add(lbUserName);

        JLabel lblUserPassword = new JLabel("User Password:");
        lblUserPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblUserPassword.setBounds(29, 160, 150, 30);
        contentPane.add(lblUserPassword);

        textUserName = new JTextField();
        textUserName.setBounds(193, 126, 139, 20);
        contentPane.add(textUserName);
        textUserName.setColumns(10);

        textUserPassword = new JPasswordField();
        textUserPassword.setBounds(193, 167, 139, 20);
        contentPane.add(textUserPassword);
        textUserPassword.setColumns(10);

        btnLogIn = new JButton("Log In");
        btnLogIn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnLogIn.setBounds(193, 198, 114, 23);
        contentPane.add(btnLogIn);

        btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnRegister.setBounds(193, 253, 114, 23);
        contentPane.add(btnRegister);

        btnGoBack = new JButton("<-");
        btnGoBack.setBounds(0, 0, 45, 23);
        contentPane.add(btnGoBack);
    }

    public String getTextUserName() {
        return textUserName.getText();
    }

    public String getTextUserPassword() {
        return String.valueOf(textUserPassword.getPassword());
    }

    public void btnGoBack(ActionListener action) {
        this.btnGoBack.addActionListener(action);
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public void btnLogIn(ActionListener action) {
        this.btnLogIn.addActionListener(action);
    }

    public void btnRegister(ActionListener action) {
        this.btnRegister.addActionListener(action);
    }

    public void showMsg(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
