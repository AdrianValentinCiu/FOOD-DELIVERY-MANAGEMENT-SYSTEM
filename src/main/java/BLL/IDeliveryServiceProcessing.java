package BLL;

import java.util.ArrayList;
import java.util.List;

public interface IDeliveryServiceProcessing {
    //Administrator
    List<MenuItem> importCSVData(String path);
    boolean logInAdministrator(String userName, String password);
    void registerAdministrator(String userName, String password);
    //Client
    void registerClient(String userName, String password);
    void registerEmployee(String userName, String password);
    boolean logInClient(String userName, String password);
    boolean logInEmployee(String userName, String password);

    void serializeData();
    void deserializeData();
    void createOrder(MenuItem menuItemsSelected);

    ArrayList<MenuItem> searchTitle(ArrayList<MenuItem> menuItems, String title);
    ArrayList<MenuItem> searchRating(ArrayList<MenuItem> menuItems, double rating);
    ArrayList<MenuItem> searchCalories(ArrayList<MenuItem> menuItems, int calories);
    ArrayList<MenuItem> searchProtein(ArrayList<MenuItem> menuItems, int protein);
    ArrayList<MenuItem> searchFat(ArrayList<MenuItem> menuItems, int fat);
    ArrayList<MenuItem> searchSodium(ArrayList<MenuItem> menuItems, int sodium);
    ArrayList<MenuItem> searchPrice(ArrayList<MenuItem> menuItems, int price);

    void report1(int startH, int finishH);
    void report2(int nrOfTimes);
    void report3(int nrOfTimes, int amount);
    void report4(int day);
}
