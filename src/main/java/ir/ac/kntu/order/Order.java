package ir.ac.kntu.order;

import ir.ac.kntu.store.*;
import ir.ac.kntu.user.Customer;
import ir.ac.kntu.delivery.Delivery;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private Customer customer;

    private ArrayList<Stuff> stuffs;

    private HashMap<Stuff, Double> amountOfStuff;

    private Store store;

    private Delivery delivery;

    private String destination;

    private OrderStatus orderStatus;

    private boolean commented;

    private LocalTime deliveryTime;

    public Order(Customer customer) {
        this.customer = customer;
        orderStatus = OrderStatus.PROCESSING;
        stuffs = new ArrayList<>();
        amountOfStuff = new HashMap<>();
        delivery = null;
        commented = false;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public ArrayList<Stuff> getStuffs() {
        return stuffs;
    }

    public Delivery getDelivery() {
        if (store instanceof Restaurant) {
            return delivery;
        }
        return null;
    }

    public void setDelivery(Delivery delivery) {
        if (store instanceof Restaurant) {
            this.delivery = delivery;
        }
    }

    public boolean isCommented() {
        return commented;
    }

    public void setCommented(boolean commented) {
        this.commented = commented;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setDeliveryTime(LocalTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void addAStuff(Stuff stuff, double stuffNumber) {
        if (stuffs.contains(stuff)) {
            amountOfStuff.put(stuff, amountOfStuff.get(stuff) + stuffNumber);
        } else {
            stuffs.add(stuff);
            amountOfStuff.put(stuff, stuffNumber);
        }
    }

    private int totalPrice() {
        int totalPrice = 0;
        for (Stuff stuff : stuffs) {
            totalPrice += stuff.getPrice() * amountOfStuff.get(stuff);
        }
        if (store instanceof SuperMarket) {
            SuperMarket superMarket = (SuperMarket) store;
            if (!customer.havePremiumSubscription(superMarket)) {
                totalPrice += superMarket.getDeliveryPrice();
            }
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        String ordered = "";
        int counter = 1;
        for (Stuff stuff : stuffs) {
            ordered += counter + "-" + stuff.getName() + " | Price : " + stuff.getPrice() + " | Amount : " +
                    amountOfStuff.get(stuff) + "\n";
            counter++;
        }
        String deliveryPrice = "";
        if (store instanceof SuperMarket) {
            deliveryPrice = "\nDelivery price : 0";
            SuperMarket superMarket = (SuperMarket) store;
            if (!customer.havePremiumSubscription(superMarket)) {
                deliveryPrice = "\nDelivery price : " + superMarket.getDeliveryPrice();
            }
        }
        if (orderStatus == OrderStatus.DELIVERED) {
            return "Customer : " + customer.getUserName() + "\nStore : " + store + "\nOrders :\n" + ordered +
                    deliveryPrice + "\n***Total price : " + totalPrice() + "\nOrder status: " + orderStatus +
                    "\nDelivered at : " + deliveryTime + "\n";
        }
        return "Customer : " + customer.getUserName() + "\nStore : " + store + "\nOrders :\n" + ordered +
                deliveryPrice + "\n***Total price : " + totalPrice() + "\nOrder status: " + orderStatus;
    }
}
