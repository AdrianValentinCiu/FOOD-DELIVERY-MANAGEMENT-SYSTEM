package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class ClientGUI extends JFrame {
    private final JButton btnGoBack;
    private final DefaultTableModel model;
    private final JTable table;
    private final JButton btnAddItem;
    private final JButton btnSearch;
    private final JTextField textTitle;
    private final JTextField textRating;
    private final JTextField textCalories;
    private final JTextField textProtein;
    private final JTextField textFat;
    private final JTextField textSodium;
    private final JTextField textPrice;

    public ClientGUI(DefaultTableModel myModel) {
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
        contentPane.setLayout(null);
        contentPane.setLayout(null);

        btnAddItem = new JButton("Add Item");
        btnAddItem.setBounds(20, 358, 136, 31);
        btnAddItem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        contentPane.add(btnAddItem);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(166, 63, 550, 326);
        panel.add(new JScrollPane(this.table), "Center");
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, "South");
        contentPane.add(panel);

        JLabel lbSearch = new JLabel("Search:");
        lbSearch.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbSearch.setBounds(10, 11, 82, 51);
        contentPane.add(lbSearch);

        textTitle = new JTextField();
        textTitle.setBounds(61, 67, 95, 25);
        contentPane.add(textTitle);
        textTitle.setColumns(10);

        JLabel lbTitle = new JLabel("Title:");
        lbTitle.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lbTitle.setBounds(10, 68, 40, 20);
        contentPane.add(lbTitle);

        textRating = new JTextField();
        textRating.setColumns(10);
        textRating.setBounds(61, 103, 95, 25);
        contentPane.add(textRating);

        JLabel lblRating = new JLabel("Rating:");
        lblRating.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblRating.setBounds(10, 104, 53, 20);
        contentPane.add(lblRating);

        textCalories = new JTextField();
        textCalories.setColumns(10);
        textCalories.setBounds(61, 139, 95, 25);
        contentPane.add(textCalories);

        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblCalories.setBounds(10, 140, 53, 20);
        contentPane.add(lblCalories);

        textProtein = new JTextField();
        textProtein.setColumns(10);
        textProtein.setBounds(61, 175, 95, 25);
        contentPane.add(textProtein);

        JLabel lblProtein = new JLabel("Protein:");
        lblProtein.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblProtein.setBounds(10, 176, 53, 20);
        contentPane.add(lblProtein);

        textFat = new JTextField();
        textFat.setColumns(10);
        textFat.setBounds(61, 211, 95, 25);
        contentPane.add(textFat);

        JLabel lblFat = new JLabel("Fat:");
        lblFat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblFat.setBounds(10, 212, 53, 20);
        contentPane.add(lblFat);

        textSodium = new JTextField();
        textSodium.setColumns(10);
        textSodium.setBounds(61, 247, 95, 25);
        contentPane.add(textSodium);

        JLabel lblSodium = new JLabel("Sodium");
        lblSodium.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblSodium.setBounds(10, 248, 53, 20);
        contentPane.add(lblSodium);

        textPrice = new JTextField();
        textPrice.setColumns(10);
        textPrice.setBounds(61, 283, 95, 25);
        contentPane.add(textPrice);

        JLabel lbPrice = new JLabel("Price:");
        lbPrice.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lbPrice.setBounds(10, 284, 53, 20);
        contentPane.add(lbPrice);

        this.btnGoBack = new JButton("<--");
        this.btnGoBack.setBounds(0, 0, 62, 23);
        this.btnGoBack.setFont(new Font("Times New Roman", Font.BOLD, 14));
        this.btnGoBack.setHorizontalAlignment(2);
        contentPane.add(this.btnGoBack);

        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnSearch.setBounds(20, 319, 136, 31);
        contentPane.add(btnSearch);

    }

    public JTable getTable() {
        return this.table;
    }

    public DefaultTableModel getModel() {
        return this.model;
    }

    public void btnAddItem(ActionListener action) {
        this.btnAddItem.addActionListener(action);
    }

    public void btnSearch(ActionListener action) {
        this.btnSearch.addActionListener(action);
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

    public void btnGoBackListener(ActionListener action) {
        this.btnGoBack.addActionListener(action);
    }

    public String getTextName() {
        return this.textTitle.getText();
    }

    public String getTextRating() {
        return textRating.getText();
    }

    public String getTextCalories() {
        return textCalories.getText();
    }

    public String getTextProtein() {
        return textProtein.getText();
    }

    public String getTextFat() {
        return textFat.getText();
    }

    public String getTextSodium() {
        return textSodium.getText();
    }

    public String getTextPrice() {
        return textPrice.getText();
    }
}


