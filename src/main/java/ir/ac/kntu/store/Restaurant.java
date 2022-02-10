package ir.ac.kntu.store;

import java.util.ArrayList;
import java.time.LocalTime;
import java.util.Objects;

import ir.ac.kntu.delivery.Delivery;

public class Restaurant extends Store{
    private PriceType priceType;

    private ArrayList<Food> menu;

    private ArrayList<Delivery> deliveries;

    public Restaurant() {
        super();
        menu = new ArrayList<>();
        deliveries = new ArrayList<>();
    }

    public Restaurant(String name, String address, PriceType priceType, WorkingHours workingHours) {
        super(name, address, workingHours);
        this.priceType = priceType;
        menu = new ArrayList<>();
        deliveries = new ArrayList<>();
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }

    public ArrayList<Food> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Food> menu) {
        this.menu = menu;
    }

    public void addADelivery(Delivery delivery) {
        deliveries.add(delivery);
    }

    public void removeADelivery(Delivery delivery) {
        deliveries.remove(delivery);
    }

    public void addAFoodToMenu(String foodName, int price, int cookTime) {
        Food food = new Food(foodName, price, cookTime, this);
        menu.add(food);
    }

    public void removeFoodFromMenu(Food food) {
        menu.remove(food);
    }

    public boolean menuContains(Food food) {
        return menu.contains(food);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Restaurant restaurant = (Restaurant) object;
        return Objects.equals(getName(), restaurant.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return getName() + " restaurant";
    }
}
