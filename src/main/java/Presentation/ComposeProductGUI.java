package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class ComposeProductGUI extends JFrame {
    private final DefaultTableModel model;
    private final JTable table;
    private final JTextField textTitle;
    private final JButton btnCompose;

    public ComposeProductGUI(DefaultTableModel myModel) {
        this.model = myModel;
        this.table = new JTable(this.model) {
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        this.table.setSelectionMode(0);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter(this.table.getModel());
        this.table.setRowSorter(rowSorter);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 745, 459);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(null);

        btnCompose = new JButton("Compose");
        btnCompose.setBounds(10, 179, 146, 43);
        btnCompose.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        contentPane.add(btnCompose);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(166, 63, 550, 326);
        panel.add(new JScrollPane(this.table), "Center");
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, "South");
        contentPane.add(panel);

        JLabel lbInsertTitle = new JLabel("Insert Title:");
        lbInsertTitle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbInsertTitle.setBounds(10, 63, 134, 51);
        contentPane.add(lbInsertTitle);

        textTitle = new JTextField();
        textTitle.setBounds(10, 125, 146, 43);
        contentPane.add(textTitle);
        textTitle.setColumns(10);

    }

    public JTable getTable() {
        return this.table;
    }

    public String getTextName() {
        return this.textTitle.getText();
    }

    public DefaultTableModel getModel() {
        return this.model;
    }

    public void btnCompose(ActionListener action) {
        this.btnCompose.addActionListener(action);
    }

    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

    public String getTextTitle() {
        return textTitle.getText();
    }

    public void removeAllTableRows(DefaultTableModel myTableModel) {
        if (myTableModel.getRowCount() > 0)
            for (int i = myTableModel.getRowCount() - 1; i > -1; i--)
                myTableModel.removeRow(i);
    }
}
