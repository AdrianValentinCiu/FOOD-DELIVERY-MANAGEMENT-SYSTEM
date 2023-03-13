package BLL;

import java.io.Serializable;
import java.util.List;

public class BaseProduct extends MenuItem implements Serializable {
    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    @Override
    public void computeRating() {

    }

    @Override
    public void computeCalories() {

    }

    @Override
    public void computeProtein() {

    }

    @Override
    public void computeFat() {

    }

    @Override
    public void computeSodium() {

    }

    @Override
    public void computePrice() {

    }

    @Override
    public void addProduct(MenuItem product) {

    }

    @Override
    public List<MenuItem> getMenuItems() {
        return null;
    }
}
