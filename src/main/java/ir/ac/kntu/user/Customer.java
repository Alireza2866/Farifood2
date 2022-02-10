package ir.ac.kntu.user;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.store.SuperMarket;

import java.util.ArrayList;
import java.util.Objects;

public class Customer extends User{
    private String phoneNumber;

    private String address;

    private ArrayList<Order> orderHistory;

    private ArrayList<SuperMarket> premiumSubscription;

    private ArrayList<Comment> comments;

    public Customer(String userName, String password, String phoneNumber, String address) {
        super(userName, password);
        if (phoneNumber.matches("^09[0-9]{9}$")) {
            this.phoneNumber = phoneNumber;
        }
        this.address = address;
        orderHistory = new ArrayList<>();
        premiumSubscription = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^09[0-9]{9}$")) {
            this.phoneNumber = phoneNumber;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addAnOrder(Order order) {
        orderHistory.add(order);
    }

    public void addAPremiumSubscription(SuperMarket superMarket) {
        premiumSubscription.add(superMarket);
    }

    public boolean havePremiumSubscription(SuperMarket superMarket) {
        return premiumSubscription.contains(superMarket);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addAComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Customer customer = (Customer) object;
        return Objects.equals(getUserName(), customer.getUserName()) &&
                Objects.equals(getPhoneNumber(), customer.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return getUserName() + " - phoneNumber: " + getPhoneNumber();
    }
}
