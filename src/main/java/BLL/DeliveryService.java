package BLL;

import DataAccess.CSVReader;
import DataAccess.FileWriter;
import DataAccess.Serializator;
import Presentation.EmployeeGUI;

import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @invariant isWellFormed()
 */

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    private LinkedList<User> clients;
    private LinkedList<User> employees;
    private final LinkedList<User> administrator;
    private ArrayList<MenuItem> menuItems;
    private HashMap<Order, ArrayList<MenuItem>> orders;
    private final FileWriter fileWriter;
    List<Observer> observerList;

    public DeliveryService() {
        observerList = new ArrayList<>();
        orders = new HashMap<>();
        menuItems = new ArrayList<>();
        clients = new LinkedList<>();
        administrator = new LinkedList<>();
        fileWriter = new FileWriter();
    }

    /**
     * This method extracts the data from a csv file
     * @param path - the full path to the csv file
     * @return - the list with all the menu items form the csv file
     * @pre path != null
     * @post @nochange
     */
    @Override
    public List<MenuItem> importCSVData(String path) {
        assert path != null;
        CSVReader csvReader = new CSVReader(path);
        List<MenuItem> baseProducts = null;
        try {
            baseProducts = csvReader.readCSV();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Serializator serializator = new Serializator();
        serializator.serialize("MenuData.txt", baseProducts);
        return baseProducts;
    }

    /**
     * This method is used by administrator to log in
     * @param userName - the username of the administrator
     * @param password - the password of the administrator
     * @return - true if the user exists
     * @pre userName != null && password !=null
     * @post @nochange
     */
    @Override
    public boolean logInAdministrator(String userName, String password) {
        assert userName != null;
        assert password != null;
        LogInRegister logInRegister = new LogInRegister("administrators.txt");
        return logInRegister.checkUser(userName, password);
    }

    /**
     * This method is used to register a new administrator
     * @param userName - the username of the administrator
     * @param password - the password of the administrator
     * @pre userName != null && password != null
     * @post @nochange
     */
    @Override
    public void registerAdministrator(String userName, String password) {
        assert userName != null;
        assert password != null;
        LogInRegister logInRegister = new LogInRegister("administrators.txt");
        logInRegister.registerUser(userName, password, administrator);
    }

    /**
     * This method is used to register a new client
     * @param userName - the username of the client
     * @param password - the password of the client
     * @pre userName != null && password != null
     * @post @nochange
     */
    @Override
    public void registerClient(String userName, String password) {
        assert userName != null;
        assert password != null;
        LogInRegister logInRegister = new LogInRegister("clients.txt");
        logInRegister.registerUser(userName, password, clients);
    }

    /**
     * This method is used to register a new employee
     * @param userName - the username of the employee
     * @param password - the password of the employee
     * @pre userName != null && password != null
     * @post @nochange
     */
    @Override
    public void registerEmployee(String userName, String password) {
        assert userName != null;
        assert password != null;
        LogInRegister logInRegister = new LogInRegister("employees.txt");
        logInRegister.registerUser(userName, password, employees);
    }

    /**
     * This method is used to log in a client
     * @param userName - the username of the client
     * @param password - the password of the client
     * @return - true if the client exists
     * @pre userName != null && password != null
     * @post @nochange
     */
    @Override
    public boolean logInClient(String userName, String password) {
        assert userName != null;
        assert password != null;
        LogInRegister logInRegister = new LogInRegister("clients.txt");
        return logInRegister.checkUser(userName, password);
    }

    /**
     * This method is used to log in employee
     * @param userName - the username of the employee
     * @param password - the password of the employee
     * @return - true if the client exists
     * @pre userName != null && password != null
     * @post @nochange
     */
    @Override
    public boolean logInEmployee(String userName, String password) {
        assert userName != null;
        assert password != null;
        LogInRegister logInRegister = new LogInRegister("employees.txt");
        registerObserver(new EmployeeGUI());
        return logInRegister.checkUser(userName, password);
    }

    /**
     * This method is used te serialize all the data
     * @pre @nochange
     * @posts @nochange
     */
    @Override
    public void serializeData() {
        Serializator serializator = new Serializator();
        serializator.serialize("clients.txt", clients);
        serializator.serialize("MenuData.txt", menuItems);
        serializator.serialize("orders.txt", orders);
        serializator.serialize("employees.txt", employees);
    }

    /**
     * This method is used te deserialize all the data
     * @pre @nochange
     * @posts @nochange
     */
    @Override
    public void deserializeData() {
        Serializator serializator = new Serializator();
        clients = (LinkedList<User>) serializator.deserialize("clients.txt");
        if (clients == null)
            User.curId = 0;
        else
            User.curId = clients.size();
        Object object = serializator.deserialize("MenuData.txt");
        if (object != null)
            menuItems = (ArrayList<MenuItem>) object;
        object = serializator.deserialize("orders.txt");
        if (object != null)
            orders = (HashMap<Order, ArrayList<MenuItem>>) object;
        if (orders == null)
            Order.curId = 0;
        else
            Order.curId = orders.size();
        employees = new LinkedList<>();
        object = serializator.deserialize("employees.txt");
        if (object != null)
            employees = (LinkedList<User>) object;
    }

    /**
     * This method is used to add a new order
     * @param menuItemsSelected - the items selected by the client to make a new order
     * @pre menuItemsSelected != null
     * @posts @nochange
     */
    @Override
    public void createOrder(MenuItem menuItemsSelected) {
        assert menuItemsSelected != null;
        Order order = new Order(Order.curId, LogInRegister.userId);
        orders.put(order, (ArrayList<MenuItem>) menuItemsSelected.getMenuItems());
        notifyObservers((ArrayList<MenuItem>)menuItemsSelected.getMenuItems());
        serializeData();
        FileWriter fileWriter = new FileWriter();
        fileWriter.billPDF(order, menuItemsSelected);
    }

    /**
     * This method is used to search by the title of the menu item
     * @param menuItems - the list of the menu items
     * @param title - the title of the wanted title
     * @return the array list with the found menu items
     * @pre menuItems != null && title != null
     * @post @nochange
     */
    @Override
    public ArrayList<MenuItem> searchTitle(ArrayList<MenuItem> menuItems, String title) {
        assert isWellFormed();
        List<MenuItem> found = menuItems.stream().filter(menuItem -> (menuItem.getTitle()).equals(title)).collect(Collectors.toList());
        return (ArrayList<MenuItem>) found;
    }

    /**
     * This method is used to search by the title of the menu item
     * @param menuItems - the list of the menu items
     * @param rating - the wanted rating
     * @return the array list with the found menu items
     * @pre menuItems != null
     * @post @nochange
     */
    @Override
    public ArrayList<MenuItem> searchRating(ArrayList<MenuItem> menuItems, double rating) {
        assert isWellFormed();
        List<MenuItem> found = menuItems.stream().filter(menuItem -> (Math.abs(menuItem.getRating() - rating) < 1e-3)).collect(Collectors.toList());
        return (ArrayList<MenuItem>) found;
    }

    /**
     * This method is used to search by the title of the menu item
     * @param menuItems - the list of the menu items
     * @param calories - the wanted calories
     * @return the array list with the found menu items
     * @pre menuItems != null
     * @post @nochange
     */
    @Override
    public ArrayList<MenuItem> searchCalories(ArrayList<MenuItem> menuItems, int calories) {
        assert isWellFormed();
        List<MenuItem> found = menuItems.stream().filter(menuItem -> menuItem.getCalories() == calories).collect(Collectors.toList());
        return (ArrayList<MenuItem>) found;
    }

    /**
     * This method is used to search by the title of the menu item
     * @param menuItems - the list of the menu items
     * @param protein - the wanted protein
     * @return the array list with the found menu items
     * @post @nochange
     */
    @Override
    public ArrayList<MenuItem> searchProtein(ArrayList<MenuItem> menuItems, int protein) {
        assert isWellFormed();
        List<MenuItem> found = menuItems.stream().filter(menuItem -> menuItem.getProtein() == protein).collect(Collectors.toList());
        return (ArrayList<MenuItem>) found;
    }

    /**
     * This method is used to search by the title of the menu item
     * @param menuItems - the list of the menu items
     * @param fat - the wanted fat
     * @return the array list with the found menu items
     * @pre menuItems != null
     * @post @nochange
     */
    @Override
    public ArrayList<MenuItem> searchFat(ArrayList<MenuItem> menuItems, int fat) {
        assert isWellFormed();
        List<MenuItem> found = menuItems.stream().filter(menuItem -> menuItem.getFat() == fat).collect(Collectors.toList());
        return (ArrayList<MenuItem>) found;
    }

    /**
     * This method is used to search by the title of the menu item
     * @param menuItems - the list of the menu items
     * @param sodium - the wanted sodium
     * @return the array list with the found menu items
     * @pre menuItems != null
     * @post @nochange
     */
    @Override
    public ArrayList<MenuItem> searchSodium(ArrayList<MenuItem> menuItems, int sodium) {
        assert isWellFormed();
        List<MenuItem> found = menuItems.stream().filter(menuItem -> menuItem.getSodium() == sodium).collect(Collectors.toList());
        return (ArrayList<MenuItem>) found;
    }

    /**
     * This method is used to search by the title of the menu item
     * @param menuItems - the list of the menu items
     * @param price - the wanted price
     * @return the array list with the found menu items
     * @pre menuItems != null
     * @post @nochange
     */
    @Override
    public ArrayList<MenuItem> searchPrice(ArrayList<MenuItem> menuItems, int price) {
        assert isWellFormed();
        List<MenuItem> found = menuItems.stream().filter(menuItem -> menuItem.getPrice() == price).collect(Collectors.toList());
        return (ArrayList<MenuItem>) found;
    }

    /**
     * This method is used to generate the first report
     * @param startH - the start time
     * @param finishH = the finish time
     * @pre startH >= 0 && finishH > = 0
     * @post @nochange
     */
    @Override
    public void report1(int startH, int finishH) {
        assert startH >= 0;
        assert finishH >= 0;
        StringBuilder report = new StringBuilder(" ");
        List<Order> foundOrders = orders.keySet().stream().filter(myOrder -> myOrder.getOrderDate().getHours() >= startH).filter(myOrder -> myOrder.getOrderDate().getHours() <= finishH).collect(Collectors.toList());
        for (Order writeOrder : foundOrders)
            report.append(writeOrder.getOrderId()).append(" ").append(writeOrder.getClientId()).append(" ").append(writeOrder.getOrderDate()).append("\n");
        fileWriter.reportPDF(report.toString(), 1);
    }

    public static <T> Predicate<T> distinct(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * This method is used to generate the second report
     * @param nrOfTimes - the number of times a product was ordered
     * @pre nrOfTimes >= 0
     * @post @nochange
     */
    @Override
    public void report2(int nrOfTimes) {
        assert nrOfTimes >= 0;
        StringBuilder report = new StringBuilder(" ");
        List<MenuItem> boughtProducts = orders.values().stream().flatMap(List::stream).collect(Collectors.toList());
        ArrayList<MenuItem> foundProducts = (ArrayList<MenuItem>) boughtProducts.stream().filter(myProduct -> Collections.frequency(boughtProducts, myProduct) >= nrOfTimes).filter(distinct(MenuItem::getTitle)).collect(Collectors.toList());
        for (MenuItem menuItem : foundProducts)
            report.append(menuItem.getTitle()).append("\n");
        fileWriter.reportPDF(report.toString(), 2);
    }

    /**
     * This method is used to generate the third report
     * @param nrOfTimes - the number of times a client has ordered
     * @param amount - the amount the order must be above
     * @pre nrOfTimes >= 0 && amount >= 0
     * @post @nochange
     */
    @Override
    public void report3(int nrOfTimes, int amount) {
        assert nrOfTimes >= 0;
        assert amount >= 0;
        StringBuilder report = new StringBuilder(" ");
        ArrayList<User> foundClients = (ArrayList<User>) clients.stream()
                .filter(myClient -> (orders.keySet().stream()
                                .filter(myOrder -> myOrder.getClientId() == clients.indexOf(myClient))
                                .count() >= nrOfTimes)
                )
                .filter(myClient -> (orders.keySet().stream()
                        .filter(myOrder -> myOrder.getClientId() == clients.indexOf(myClient))
                        .count() == orders.keySet().stream()
                                                .filter(order -> order.getClientId() == clients.indexOf(myClient) && orders.get(order).stream().mapToInt(menuItem -> menuItem.getPrice()).sum() > amount)
                                                .count()))
                .collect(Collectors.toList());
        for(User clients : foundClients)
            report.append(clients).append("\n");
        fileWriter.reportPDF(report.toString(), 3);
    }


    /**
     * This method is used to generate the fourth report
     * @param day - the day of the week
     * @pre day >= 0 && nrOfTimes >= 0
     * @post @nochange
     */
    @Override
    public void report4(int day) {
        assert  day >= 0;
        StringBuilder report = new StringBuilder(" ");
        List<MenuItem> boughtProducts = new ArrayList<>();
        orders.keySet().stream().filter(myOrder -> myOrder.getOrderDate().getDate() == day).map(order -> orders.get(order)).forEach(boughtProducts::addAll);
        Map<MenuItem, Long> foundProducts = boughtProducts.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        report.append("Day: ").append(day).append("\n");
        for(Map.Entry<MenuItem,Long> eachProduct : foundProducts.entrySet()){
            report.append("Product: ");
            report.append(eachProduct.getKey().toString());
            report.append(" Number of times: ");
            report.append(eachProduct.getValue());
            report.append("\n");
        }
        fileWriter.reportPDF(report.toString(), 4);
    }

    /**
     * This method is used to add a new observer
     * @param o - the new observer
     */
    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    /**
     * This method is used notify all the observers
     * @param menuItem - the ordered item from a client
     */
    @Override
    public void notifyObservers(ArrayList<MenuItem> menuItem) {
        for (Observer obs : observerList) {
            obs.update(menuItem);
        }
    }

    /**
     * The class invariant
     * @return true if the data is well-formed
     */
    public boolean isWellFormed() {
        return menuItems != null;
    }

    public LinkedList<User> getClients() {
        return clients;
    }

    public LinkedList<User> getAdministrator() {
        return administrator;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public HashMap<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public LinkedList<User> getEmployees() {
        return employees;
    }
}