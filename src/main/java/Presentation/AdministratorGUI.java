package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class AdministratorGUI extends JFrame {
    private final JLabel lbTitle;
    private final JLabel lbRating;
    private final JLabel lbCalories;
    private final JButton btnAdd;
    private final JButton btnEdit;
    private final JButton btnDelete;
    private final JButton btnView;
    private final JButton btnGoBack;
    private final DefaultTableModel model;
    private final JTable table;
    private final JPanel panel;
    private final JTextField textTitle;
    private final JTextField textRating;
    private final JTextField textCalories;
    private final JButton btnImportProducts;
    private final JTextField textSodium;
    private final JLabel lbSodium;
    private final JLabel lbFat;
    private final JLabel lbPrice;
    private final JTextField textFat;
    private final JTextField textProtein;
    private final JLabel lbProtein;
    private final JTextField textPrice;
    private final JButton btnComposeProduct;
    private final JComboBox comboBoxReport;

    public AdministratorGUI(DefaultTableModel myModel) {
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
        this.setBounds(100, 100, 757, 482);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setLayout(null);
        this.btnAdd = new JButton("Add Product");
        this.btnAdd.setBounds(10, 117, 146, 43);
        this.btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        contentPane.add(this.btnAdd);
        this.btnEdit = new JButton("Edit Product");
        this.btnEdit.setBounds(10, 171, 146, 43);
        this.btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        contentPane.add(this.btnEdit);
        this.btnDelete = new JButton("Delete Product");
        this.btnDelete.setBounds(10, 225, 146, 43);
        this.btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        contentPane.add(this.btnDelete);
        this.btnView = new JButton("View Products");
        this.btnView.setBounds(10, 279, 146, 43);
        this.btnView.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        contentPane.add(this.btnView);
        this.btnGoBack = new JButton("<--");
        this.btnGoBack.setBounds(0, 0, 62, 23);
        this.btnGoBack.setFont(new Font("Times New Roman", Font.BOLD, 14));
        this.btnGoBack.setHorizontalAlignment(2);
        contentPane.add(this.btnGoBack);
        JTextField textFilter = new JTextField();
        this.panel = new JPanel(new BorderLayout());
        this.lbTitle = new JLabel("Title:");
        this.lbTitle.setBounds(170, 0, 50, 20);
        this.lbRating = new JLabel("Rating: ");
        this.lbRating.setBounds(170, 21, 50, 20);
        this.textRating = new JTextField();
        this.textRating.setBounds(223, 21, 62, 20);
        this.lbCalories = new JLabel("Calories:");
        this.lbCalories.setBounds(170, 42, 55, 20);
        this.textCalories = new JTextField();
        this.textCalories.setBounds(223, 42, 62, 20);
        contentPane.add(this.lbTitle);
        contentPane.add(this.lbRating);
        contentPane.add(this.textRating);
        this.textTitle = new JTextField();
        this.textTitle.setBounds(223, 0, 62, 20);
        contentPane.add(this.textTitle);
        contentPane.add(this.lbCalories);
        contentPane.add(this.textCalories);
        this.panel.setBounds(166, 63, 550, 316);
        this.panel.add(new JScrollPane(this.table), "Center");
        JScrollPane scrollPane = new JScrollPane(textFilter);
        this.panel.add(scrollPane, "South");
        JLabel lbSearch = new JLabel("Search: ");
        lbSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
        scrollPane.setRowHeaderView(lbSearch);
        contentPane.add(this.panel);
        this.searchJTable(textFilter, rowSorter);

        btnImportProducts = new JButton("Import Products");
        btnImportProducts.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnImportProducts.setBounds(10, 63, 146, 43);
        contentPane.add(btnImportProducts);

        textSodium = new JTextField();
        textSodium.setBounds(362, 42, 62, 20);
        contentPane.add(textSodium);

        lbSodium = new JLabel("Sodium: ");
        lbSodium.setBounds(312, 42, 55, 20);
        contentPane.add(lbSodium);

        lbFat = new JLabel("Fat: ");
        lbFat.setBounds(312, 21, 50, 20);
        contentPane.add(lbFat);

        textFat = new JTextField();
        textFat.setBounds(362, 21, 62, 20);
        contentPane.add(textFat);

        textProtein = new JTextField();
        textProtein.setBounds(362, 0, 62, 20);
        contentPane.add(textProtein);

        lbProtein = new JLabel("Protein:");
        lbProtein.setBounds(312, 0, 50, 20);
        contentPane.add(lbProtein);

        lbPrice = new JLabel("Price: ");
        lbPrice.setBounds(459, 21, 50, 20);
        contentPane.add(lbPrice);

        textPrice = new JTextField();
        textPrice.setBounds(509, 21, 62, 20);
        contentPane.add(textPrice);

        btnComposeProduct = new JButton("Compose Product");
        btnComposeProduct.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnComposeProduct.setBounds(10, 333, 146, 43);
        contentPane.add(btnComposeProduct);

        comboBoxReport = new JComboBox();
        comboBoxReport.setModel(new DefaultComboBoxModel(new String[] {"Report1", "Report2", "Report3", "Report4"}));
        comboBoxReport.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        comboBoxReport.setToolTipText("");
        comboBoxReport.setBounds(10, 398, 146, 36);
        contentPane.add(comboBoxReport);
        this.table.getSelectionModel().addListSelectionListener((e) -> {
            try {
                int i = Math.abs(this.table.convertRowIndexToModel(this.table.getSelectedRow()));
                this.textTitle.setText(this.model.getValueAt(i, 0).toString());
                this.textRating.setText(this.model.getValueAt(i, 1).toString());
                this.textCalories.setText(this.model.getValueAt(i, 2).toString());
                this.textProtein.setText(this.model.getValueAt(i, 3).toString());
                this.textFat.setText(this.model.getValueAt(i, 4).toString());
                this.textSodium.setText(this.model.getValueAt(i, 5).toString());
                this.textPrice.setText(this.model.getValueAt(i, 6).toString());
            } catch (Exception ignored) {
            }

        });
    }

    public void searchJTable(final JTextField text, final TableRowSorter<TableModel> rowSorter) {
        text.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (text.getText().trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter(text.getText()));
                }

            }

            public void removeUpdate(DocumentEvent e) {
                if (text.getText().trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter(text.getText()));
                }

            }

            public void changedUpdate(DocumentEvent e) {
                if (text.getText().trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter(text.getText()));
                }

            }
        });
    }

    public JTable getTable() {
        return this.table;
    }

    public String getTextStock() {
        return this.textCalories.getText();
    }

    public void setVisibleView(boolean visible) {
        lbCalories.setVisible(visible);
        lbFat.setVisible(visible);
        lbProtein.setVisible(visible);
        lbRating.setVisible(visible);
        lbSodium.setVisible(visible);
        lbTitle.setVisible(visible);
        lbPrice.setVisible(visible);
        textTitle.setVisible(visible);
        textRating.setVisible(visible);
        textCalories.setVisible(visible);
        textProtein.setVisible(visible);
        textFat.setVisible(visible);
        textSodium.setVisible(visible);
        textPrice.setVisible(visible);
        btnAdd.setEnabled(visible);
        btnEdit.setEnabled(visible);
        if (!visible) panel.setBounds(166, 0, 550, 379);
        else panel.setBounds(166, 63, 550, 316);
    }

    public String getTextPrice() {
        return this.textPrice.getText();
    }

    public String getTextTitle() {
        return this.textTitle.getText();
    }

    public String getTextRating() {
        return textRating.getText();
    }

    public String getTextCalories() {
        return textCalories.getText();
    }

    public String getTextSodium() {
        return textSodium.getText();
    }

    public String getTextFat() {
        return textFat.getText();
    }

    public String getTextProtein() {
        return textProtein.getText();
    }

    public DefaultTableModel getModel() {
        return this.model;
    }

    public void btnGoBackListener(ActionListener action) {
        this.btnGoBack.addActionListener(action);
    }

    public void btnAddListener(ActionListener action) {
        this.btnAdd.addActionListener(action);
    }

    public void btnEditListener(ActionListener action) {
        this.btnEdit.addActionListener(action);
    }

    public void btnImportProducts(ActionListener action) {
        this.btnImportProducts.addActionListener(action);
    }

    public void btnDeleteListener(ActionListener action) {
        this.btnDelete.addActionListener(action);
    }

    public void btnViewListener(ActionListener action) {
        this.btnView.addActionListener(action);
    }

    public void btnComposeProduct(ActionListener action) { this.btnComposeProduct.addActionListener(action); }

    public void comboBoxReport(ActionListener action) { this.comboBoxReport.addActionListener(action); }

    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

    public JComboBox getComboBoxReport() {
        return comboBoxReport;
    }

    public void removeAllTableRows(DefaultTableModel myTableModel) {
        if (myTableModel.getRowCount() > 0)
            for (int i = myTableModel.getRowCount() - 1; i > -1; i--)
                myTableModel.removeRow(i);
    }
}