package ir.ac.kntu.store;

public class Fruit extends Stuff{
    private double weight;

    public Fruit(String name, int price, FruitShop fruitShop, double weight) {
        //price of a fruit is for 1 kg
        super(name, price, fruitShop);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return getName() + " | price per kg : " + getPrice();
    }
}
