package Presentation;

import BLL.*;
import Presentation.JModel.JTableModelProducts;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum User {
    Administrator,
    Client,
    Employee
}

public class Controller {
    private boolean state = true;
    private final EmployeeGUI employeeGUI;
    private final SelectLogInGUI selectLogInGUI;
    private final LogInGUI logInGUI;
    private final ClientGUI clientGUI;
    private final ClientOrderGUI clientOrderGUI;
    private final AdministratorGUI administratorGUI;
    private final DefaultTableModel modelComposedItems = (new JTableModelProducts()).modelData(null);
    private final DefaultTableModel modelClientGUI = (new JTableModelProducts()).modelData(null);
    private final DefaultTableModel modelClientOrderGUI = (new JTableModelProducts()).modelData(null);
    private User user;
    private final ComposeProductGUI composeProductGUI = new ComposeProductGUI(modelComposedItems);
    private MenuItem menuItem;

    private final Report1GUI report1GUI;
    private final Report2GUI report2GUI;
    private final Report3GUI report3GUI;
    private final Report4GUI report4GUI;

    DeliveryService deliveryService = new DeliveryService();

    public Controller() {
        employeeGUI = new EmployeeGUI();
        report1GUI = new Report1GUI();
        report2GUI = new Report2GUI();
        report3GUI = new Report3GUI();
        report4GUI = new Report4GUI();

        selectLogInGUI = new SelectLogInGUI();
        selectLogInGUI.setVisible(true);
        logInGUI = new LogInGUI();
        DefaultTableModel modelProducts = (new JTableModelProducts()).modelData(null);
        administratorGUI = new AdministratorGUI(modelProducts);
        clientGUI = new ClientGUI(modelClientGUI);
        clientOrderGUI = new ClientOrderGUI(modelClientOrderGUI);
        deliveryService.deserializeData();
        menuItem = new CompositeProduct();

        selectLogInGUI.btnAdministrator(e -> {
            selectLogInGUI.setVisible(false);
            logInGUI.setVisible(true);
            logInGUI.getBtnRegister().setVisible(true);
            user = User.Administrator;
        });

        selectLogInGUI.btnClient(e -> {
            selectLogInGUI.setVisible(false);
            logInGUI.setVisible(true);
            logInGUI.getBtnRegister().setVisible(true);
            user = User.Client;
        });

        selectLogInGUI.btnEmployee(e -> {
            selectLogInGUI.setVisible(false);
            logInGUI.setVisible(true);
            logInGUI.getBtnRegister().setVisible(true);
            user = User.Employee;
        });

        logInGUI.btnGoBack(e -> {
            selectLogInGUI.setVisible(true);
            logInGUI.setVisible(false);
        });

        logInGUI.btnLogIn(e -> {
            String path = "";
            initJTable();
            if (user == User.Administrator) {
                if (deliveryService.logInAdministrator(logInGUI.getTextUserName(), logInGUI.getTextUserPassword())) {
                    administratorGUI.setVisible(true);
                    composeProductGUI.setVisible(true);
                    logInGUI.setVisible(false);
                } else logInGUI.showMsg("Not Found!");
            } else if (user == User.Client) {
                if (deliveryService.logInClient(logInGUI.getTextUserName(), logInGUI.getTextUserPassword())) {
                    administratorGUI.setVisible(false);
                    composeProductGUI.setVisible(false);
                    clientGUI.setVisible(true);
                    clientOrderGUI.setVisible(true);
                    logInGUI.setVisible(false);
                } else logInGUI.showMsg("Client Not Found!");
            } else if (user == User.Employee){
                if (deliveryService.logInEmployee(logInGUI.getTextUserName(), logInGUI.getTextUserPassword())) {
                    logInGUI.setVisible(false);
                    employeeGUI.setVisible(true);
                } else logInGUI.showMsg("Employee Not Found!");
            }
        });

        logInGUI.btnRegister(e -> {
            if (user == User.Administrator) {
                deliveryService.registerAdministrator(logInGUI.getTextUserName(), logInGUI.getTextUserPassword());
                logInGUI.showMsg("User Registered!");
            }
            else if (user == User.Client) {
                deliveryService.registerClient(logInGUI.getTextUserName(), logInGUI.getTextUserPassword());
                logInGUI.showMsg("User Registered!");
            }
            else if (user == User.Employee) {
                deliveryService.registerEmployee(logInGUI.getTextUserName(), logInGUI.getTextUserPassword());
                logInGUI.showMsg("User Registered!");
            }
        });

        administratorGUI.comboBoxReport(e -> {
            String result = Objects.requireNonNull(administratorGUI.getComboBoxReport().getSelectedItem()).toString();
            switch (result) {
                case "Report1":
                    report1GUI.setVisible(true);
                    report2GUI.setVisible(false);
                    report3GUI.setVisible(false);
                    report4GUI.setVisible(false);
                    break;
                case "Report2":
                    report1GUI.setVisible(false);
                    report2GUI.setVisible(true);
                    report3GUI.setVisible(false);
                    report4GUI.setVisible(false);
                    break;
                case "Report3":
                    report1GUI.setVisible(false);
                    report2GUI.setVisible(false);
                    report3GUI.setVisible(true);
                    report4GUI.setVisible(false);
                    break;
                case "Report4":
                    report1GUI.setVisible(false);
                    report2GUI.setVisible(false);
                    report3GUI.setVisible(false);
                    report4GUI.setVisible(true);
                    break;
            }
        });

        administratorGUI.btnGoBackListener(e -> selectLogInGUI.setVisible(true));

        administratorGUI.btnAddListener(e -> {
            try {
                BaseProduct baseProduct = new BaseProduct(administratorGUI.getTextTitle(), Double.parseDouble(administratorGUI.getTextRating()), Integer.parseInt(administratorGUI.getTextCalories()), Integer.parseInt(administratorGUI.getTextProtein()), Integer.parseInt(administratorGUI.getTextFat()), Integer.parseInt(administratorGUI.getTextSodium()), Integer.parseInt(administratorGUI.getTextPrice()));
                deliveryService.getMenuItems().add(baseProduct);
                deliveryService.serializeData();
                administratorGUI.getModel().addRow(new Object[]{administratorGUI.getTextTitle(), Double.parseDouble(administratorGUI.getTextRating()), Integer.parseInt(administratorGUI.getTextCalories()), Integer.parseInt(administratorGUI.getTextProtein()), Integer.parseInt(administratorGUI.getTextFat()), Integer.parseInt(administratorGUI.getTextSodium()), Integer.parseInt(administratorGUI.getTextPrice())});
            } catch (NumberFormatException ex) {
                administratorGUI.showError("Invalid Input");
            }
        });

        administratorGUI.btnEditListener(e -> {
            if (administratorGUI.getTable().getSelectedRow() == -1)
                administratorGUI.showError("No row selected!");
            else {
                try {
                    int i = administratorGUI.getTable().getSelectedRow();
                    if(Double.parseDouble(administratorGUI.getTextRating()) < 1e-4 || Integer.parseInt(administratorGUI.getTextCalories()) < 0 || Integer.parseInt(administratorGUI.getTextProtein()) < 0 || Integer.parseInt(administratorGUI.getTextFat()) < 0 || Integer.parseInt(administratorGUI.getTextSodium()) < 0 || Integer.parseInt(administratorGUI.getTextPrice()) < 0)
                        throw new Exception();
                    administratorGUI.getModel().setValueAt(administratorGUI.getTextTitle(), i, 0);
                    administratorGUI.getModel().setValueAt(administratorGUI.getTextRating(), i, 1);
                    administratorGUI.getModel().setValueAt(administratorGUI.getTextCalories(), i, 2);
                    administratorGUI.getModel().setValueAt(administratorGUI.getTextProtein(), i, 3);
                    administratorGUI.getModel().setValueAt(administratorGUI.getTextFat(), i, 4);
                    administratorGUI.getModel().setValueAt(administratorGUI.getTextSodium(), i, 5);
                    administratorGUI.getModel().setValueAt(administratorGUI.getTextPrice(), i, 6);

                    deliveryService.getMenuItems().get(i).setTitle(administratorGUI.getTextTitle());
                    deliveryService.getMenuItems().get(i).setRating(Double.parseDouble(administratorGUI.getTextRating()));
                    deliveryService.getMenuItems().get(i).setCalories(Integer.parseInt(administratorGUI.getTextCalories()));
                    deliveryService.getMenuItems().get(i).setProtein(Integer.parseInt(administratorGUI.getTextProtein()));
                    deliveryService.getMenuItems().get(i).setFat(Integer.parseInt(administratorGUI.getTextFat()));
                    deliveryService.getMenuItems().get(i).setSodium(Integer.parseInt(administratorGUI.getTextSodium()));
                    deliveryService.getMenuItems().get(i).setPrice(Integer.parseInt(administratorGUI.getTextPrice()));
                    deliveryService.serializeData();
                } catch (Exception ex) {
                    administratorGUI.showError("Invalid Input!");
                }
            }
        });

        administratorGUI.btnDeleteListener(e -> {
            if (administratorGUI.getTable().getSelectedRow() == -1)
                administratorGUI.showError("No row selected!");
            else {
                deliveryService.getMenuItems().remove(administratorGUI.getTable().getSelectedRow());
                administratorGUI.getModel().removeRow(administratorGUI.getTable().getSelectedRow());
                deliveryService.serializeData();
            }
        });

        administratorGUI.btnImportProducts(e -> {
            List<MenuItem> baseProducts = deliveryService.importCSVData("D:\\ANUL2\\TP\\LAB\\Assigment4\\pt2022_30227_adrian_ciu_assignment_4\\products.csv");
            deliveryService.setMenuItems((ArrayList<MenuItem>) baseProducts);
            this.administratorGUI.removeAllTableRows(administratorGUI.getModel());
            for (MenuItem product : baseProducts)
                this.administratorGUI.getModel().addRow(new Object[]{product.getTitle(), product.getRating(), product.getCalories(), product.getProtein(), product.getFat(), product.getSodium(), product.getPrice()});
            deliveryService.serializeData();
        });

        administratorGUI.btnViewListener(e -> {
            this.state = !this.state;
            this.administratorGUI.setVisibleView(this.state);
        });

        administratorGUI.btnComposeProduct(e -> {
            BaseProduct baseProduct = new BaseProduct(administratorGUI.getTextTitle(), Double.parseDouble(administratorGUI.getTextRating()), Integer.parseInt(administratorGUI.getTextCalories()), Integer.parseInt(administratorGUI.getTextProtein()), Integer.parseInt(administratorGUI.getTextFat()), Integer.parseInt(administratorGUI.getTextSodium()), Integer.parseInt(administratorGUI.getTextPrice()));
            menuItem.addProduct(baseProduct);
            composeProductGUI.getModel().addRow(new Object[]{administratorGUI.getTextTitle(), Double.parseDouble(administratorGUI.getTextRating()), Integer.parseInt(administratorGUI.getTextCalories()), Integer.parseInt(administratorGUI.getTextProtein()), Integer.parseInt(administratorGUI.getTextFat()), Integer.parseInt(administratorGUI.getTextSodium()), Integer.parseInt(administratorGUI.getTextPrice())});
        });

        composeProductGUI.btnCompose(e -> {
            menuItem.setTitle(composeProductGUI.getTextTitle());
            menuItem.computeCalories();
            menuItem.computeFat();
            menuItem.computePrice();
            menuItem.computeRating();
            menuItem.computeSodium();
            menuItem.computeProtein();
            deliveryService.getMenuItems().add(menuItem);
            deliveryService.serializeData();
            administratorGUI.getModel().addRow(new Object[]{menuItem.getTitle(), menuItem.getRating(), menuItem.getCalories(), menuItem.getProtein(), menuItem.getFat(), menuItem.getSodium(), menuItem.getPrice()});
            menuItem = new CompositeProduct();
            administratorGUI.removeAllTableRows(modelComposedItems);
        });

        clientGUI.btnGoBackListener(e -> selectLogInGUI.setVisible(true));

        employeeGUI.btnGoBackListener(e -> selectLogInGUI.setVisible(true));

        clientGUI.btnSearch(e -> {
            ArrayList<MenuItem> filteredItems = new ArrayList<>();
            try {
                filteredItems = deliveryService.searchTitle(deliveryService.getMenuItems(), clientGUI.getTextTitle());
            } catch (Exception ignore) {
            }
            try {
                if (!clientGUI.getTextRating().equals("") && filteredItems.size() != 0)
                    filteredItems = deliveryService.searchRating(filteredItems, Double.parseDouble(clientGUI.getTextRating()));
                else
                    filteredItems = deliveryService.searchRating(deliveryService.getMenuItems(), Double.parseDouble(clientGUI.getTextRating()));
            } catch (Exception ignore) {
            }
            try {
                if (!clientGUI.getTextCalories().equals("") && filteredItems.size() != 0)
                    filteredItems = deliveryService.searchCalories(filteredItems,Integer.parseInt(clientGUI.getTextCalories()));
                else
                    filteredItems = deliveryService.searchCalories(deliveryService.getMenuItems(), Integer.parseInt(clientGUI.getTextCalories()));
            } catch (Exception ignore) {
            }
            try {
                if (!clientGUI.getTextProtein().equals("") && filteredItems.size() != 0)
                    filteredItems = deliveryService.searchProtein(filteredItems, Integer.parseInt(clientGUI.getTextProtein()));
                else
                    filteredItems = deliveryService.searchProtein(deliveryService.getMenuItems(), Integer.parseInt(clientGUI.getTextProtein()));
            } catch (Exception ignore) {
            }
            try {
                if (!clientGUI.getTextFat().equals("") && filteredItems.size() != 0)
                    filteredItems = deliveryService.searchFat(filteredItems, Integer.parseInt(clientGUI.getTextFat()));
                else
                    filteredItems = deliveryService.searchFat(deliveryService.getMenuItems(), Integer.parseInt(clientGUI.getTextFat()));
            } catch (Exception ignore) {
            }
            try {
                if (!clientGUI.getTextSodium().equals("") && filteredItems.size() != 0)
                    filteredItems = deliveryService.searchSodium(filteredItems, Integer.parseInt(clientGUI.getTextSodium()));
                else
                    filteredItems = deliveryService.searchSodium(deliveryService.getMenuItems(), Integer.parseInt(clientGUI.getTextSodium()));
            } catch (Exception ignore) {
            }
            try {
                if (!clientGUI.getTextPrice().equals("") && filteredItems.size() != 0)
                    filteredItems = deliveryService.searchPrice(filteredItems, Integer.parseInt(clientGUI.getTextPrice()));
                else
                    filteredItems = deliveryService.searchPrice(deliveryService.getMenuItems(), Integer.parseInt(clientGUI.getTextPrice()));
            } catch (Exception ignore) {
            }
            clientGUI.removeAllTableRows(modelClientGUI);
            if (filteredItems != null)
                for (MenuItem baseProduct : filteredItems)
                    clientGUI.getModel().addRow(new Object[]{baseProduct.getTitle(), baseProduct.getRating(), baseProduct.getCalories(), baseProduct.getProtein(), baseProduct.getFat(), baseProduct.getSodium(), baseProduct.getPrice()});
            if (clientGUI.getTextTitle().equals("") && clientGUI.getTextRating().equals("") && clientGUI.getTextCalories().equals("") && clientGUI.getTextProtein().equals("") && clientGUI.getTextFat().equals("") && clientGUI.getTextSodium().equals("") && clientGUI.getTextPrice().equals("")) {
                for (MenuItem baseProduct : deliveryService.getMenuItems())
                    clientGUI.getModel().addRow(new Object[]{baseProduct.getTitle(), baseProduct.getRating(), baseProduct.getCalories(), baseProduct.getProtein(), baseProduct.getFat(), baseProduct.getSodium(), baseProduct.getPrice()});
            }
        });

        clientGUI.btnAddItem(e -> {
            try {
                int i = Math.abs(clientGUI.getTable().convertRowIndexToModel(clientGUI.getTable().getSelectedRow()));
                BaseProduct baseProduct = new BaseProduct(clientGUI.getModel().getValueAt(i, 0).toString(), Double.parseDouble(clientGUI.getModel().getValueAt(i, 1).toString()), Integer.parseInt(clientGUI.getModel().getValueAt(i, 2).toString()), Integer.parseInt(clientGUI.getModel().getValueAt(i, 3).toString()), Integer.parseInt(clientGUI.getModel().getValueAt(i, 4).toString()), Integer.parseInt(clientGUI.getModel().getValueAt(i, 5).toString()), Integer.parseInt(clientGUI.getModel().getValueAt(i, 6).toString()));
                menuItem.addProduct(baseProduct);
                clientOrderGUI.getModel().addRow(new Object[]{clientGUI.getModel().getValueAt(i, 0).toString(), Double.parseDouble(clientGUI.getModel().getValueAt(i, 1).toString()), Integer.parseInt(clientGUI.getModel().getValueAt(i, 2).toString()), Integer.parseInt(clientGUI.getModel().getValueAt(i, 3).toString()), Integer.parseInt(clientGUI.getModel().getValueAt(i, 4).toString()), Integer.parseInt(clientGUI.getModel().getValueAt(i, 5).toString()), Integer.parseInt(clientGUI.getModel().getValueAt(i, 6).toString())});
            } catch (Exception ex) {
                clientGUI.showError("Error");
            }

        });

        clientOrderGUI.btnMakeOrder(e -> {
            Order.curId++;
            deliveryService.createOrder(menuItem);
            menuItem = new CompositeProduct();
            clientOrderGUI.removeAllTableRows(modelClientOrderGUI);
        });

        report1GUI.btnGenerateReport(e -> deliveryService.report1(Integer.parseInt(report1GUI.getTextStartHour()), Integer.parseInt(report1GUI.getTextFinishHour())));

        report2GUI.btnGenerateReport(e -> deliveryService.report2(Integer.parseInt(report2GUI.getTextNumberofTimes())));

        report3GUI.btnGenerateReport(e -> deliveryService.report3(Integer.parseInt(report3GUI.getTextNrofTimes()), Integer.parseInt(report3GUI.getTextAmount())));

        report4GUI.btnGenerateReport(e -> deliveryService.report4(Integer.parseInt(report4GUI.getTextDay())));

    }

    private void initJTable() {
        for (MenuItem baseProduct : deliveryService.getMenuItems()) {
            administratorGUI.getModel().addRow(new Object[]{baseProduct.getTitle(), baseProduct.getRating(), baseProduct.getCalories(), baseProduct.getProtein(), baseProduct.getFat(), baseProduct.getSodium(), baseProduct.getPrice()});
            clientGUI.getModel().addRow(new Object[]{baseProduct.getTitle(), baseProduct.getRating(), baseProduct.getCalories(), baseProduct.getProtein(), baseProduct.getFat(), baseProduct.getSodium(), baseProduct.getPrice()});
        }
    }

    public void newOrder(String msg){
        employeeGUI.showOrderedMenu(msg);
    }
}
