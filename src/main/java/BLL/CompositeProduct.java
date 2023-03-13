package BLL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {

    private List<MenuItem> menuItems;

    public CompositeProduct() {
        super("", 0f, 0, 0,  0, 0, 0);
        menuItems = new ArrayList<>();
    }

    public void addProduct(MenuItem product){
        menuItems.add(product);
    }

    /**
     * This method is used to compute the rating
     */
    @Override
    public void computeRating() {
        int contor = 0;
        for(MenuItem menuItem : menuItems){
            rating += menuItem.getRating();
            contor++;
        }
        rating /= contor;
    }

    /**
     * This method is used to compute the calories
     */
    @Override
    public void computeCalories() {
        for(MenuItem menuItem : menuItems)
            calories += menuItem.getCalories();
    }

    /**
     * This method is used to compute the protein
     */
    @Override
    public void computeProtein() {
        for(MenuItem menuItem : menuItems)
            protein += menuItem.getProtein();
    }

    /**
     * This method is used to compute the fat
     */
    @Override
    public void computeFat() {
        for(MenuItem menuItem : menuItems)
            fat += menuItem.getFat();
    }

    /**
     * This method is used to compute the sodium
     */
    @Override
    public void computeSodium() {
        for(MenuItem menuItem : menuItems)
            sodium += menuItem.getSodium();
    }

    /**
     * This method is used to compute the price
     */
    @Override
    public void computePrice() {
        for(MenuItem menuItem : menuItems)
            price += menuItem.getPrice();
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
