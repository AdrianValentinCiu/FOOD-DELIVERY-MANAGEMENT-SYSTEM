package BLL;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class MenuItem implements Serializable {
    protected String title;
    protected double rating;
    protected int calories;
    protected int protein;
    protected int fat;
    protected int sodium;
    protected int price;

    public MenuItem(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public abstract void computeRating();
    public abstract void computeCalories();
    public abstract void computeProtein();
    public abstract void computeFat();
    public abstract void computeSodium();
    public abstract void computePrice();
    public abstract void addProduct(MenuItem product);
    public abstract List<MenuItem> getMenuItems();

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public int getPrice() {
        return price;
    }

    public String toString(){
        return title + " " + rating + " " + calories + " " + protein + " " + fat + " " + sodium + " " + price + " ";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o)
    {
        MenuItem s;
        if(!(o instanceof MenuItem))
        {
            return false;
        }
        else
        {
            s=(MenuItem)o;
            if(this.title.equals(s.getTitle()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rating, calories, protein, fat, sodium, price);
    }
}
