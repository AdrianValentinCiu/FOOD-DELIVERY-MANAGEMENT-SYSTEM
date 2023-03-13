package Presentation;

import BLL.MenuItem;
import BLL.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeGUI extends JFrame implements Observer {
    private final JButton btnGoBack;

    public EmployeeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 580, 384);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.btnGoBack = new JButton("<--");
        this.btnGoBack.setBounds(0, 0, 62, 23);
        this.btnGoBack.setFont(new Font("Times New Roman", Font.BOLD, 14));
        this.btnGoBack.setHorizontalAlignment(2);
        contentPane.add(this.btnGoBack);
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    public void showOrderedMenu(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void btnGoBackListener(ActionListener action) {
        this.btnGoBack.addActionListener(action);
    }

    @Override
    public void update(ArrayList<MenuItem> menuItem) {
        StringBuilder prepareMenu = new StringBuilder();
        for(MenuItem item : menuItem)
            prepareMenu.append(item.toString()).append("\n");
        showOrderedMenu(prepareMenu.toString());
    }
}