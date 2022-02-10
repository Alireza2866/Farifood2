package ir.ac.kntu.store;

import ir.ac.kntu.comment.Comment;

import java.util.ArrayList;

public class Stuff {
    private String name;

    private int price;

    private Store store;

    public Stuff(String name, int price, Store store) {
        name = name.toLowerCase().trim();
        this.name = name;
        this.price = price;
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.toLowerCase().trim();
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return name + " | price : " + price;
    }
}
