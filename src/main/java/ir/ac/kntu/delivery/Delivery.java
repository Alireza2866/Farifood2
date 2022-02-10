package ir.ac.kntu.delivery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.comment.Commentable;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.store.Restaurant;

public class Delivery implements Commentable {
    private int id;

    private int salary;

    private SalaryType salaryType;

    private Vehicle vehicle;

    private double score;

    private boolean isDelivering;

    private ArrayList<WeekDays> workingDays;

    private ArrayList<Comment> comments;

    private ArrayList<Order> orderHistory;

    private Restaurant[] restaurants = {null, null};

    public Delivery(Vehicle vehicle, SalaryType salaryType, int salary) {
        this.vehicle = vehicle;
        this.salaryType = salaryType;
        this.salary = salary;
        id = DeliveryIdGenerator.getNewId();
        score = 5.0;
        isDelivering = false;
        workingDays = new ArrayList<>();
        comments = new ArrayList<>();
        orderHistory = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public SalaryType getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getScore() {
        return score;
    }

    public void setDelivering(boolean delivering) {
        isDelivering = delivering;
    }

    public Restaurant[] getRestaurants(){
        if (restaurants[0] == null && restaurants[1] == null) {
            return new Restaurant[]{};
        } else if (restaurants[0] == null) {
            return new Restaurant[] {restaurants[1]};
        } else if (restaurants[1] == null) {
            return new Restaurant[] {restaurants[0]};
        }
        return restaurants;
    }

    public ArrayList<WeekDays> getWorkingDays() {
        return workingDays;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addAWorkingDay(WeekDays workingDay) {
        switch (workingDay) {
            case SATURDAY:
                workingDays.add(WeekDays.SATURDAY);
                break;
            case SUNDAY:
                workingDays.add(WeekDays.SUNDAY);
                break;
            case MONDAY:
                workingDays.add(WeekDays.MONDAY);
                break;
            case TUESDAY:
                workingDays.add(WeekDays.TUESDAY);
                break;
            case WEDNESDAY:
                workingDays.add(WeekDays.WEDNESDAY);
                break;
            case THURSDAY:
                workingDays.add(WeekDays.THURSDAY);
                break;
            case FRIDAY:
                workingDays.add(WeekDays.FRIDAY);
                break;
            default:
                break;
        }
    }

    public void addAnOrder(Order order) {
        orderHistory.add(order);
    }

    @Override
    public String getName() {
        return "Delivery " + getId();
    }

    @Override
    public ArrayList<Comment> getComments() {
        return comments;
    }

    @Override
    public void addAComment(Comment comment) {
        if (comments.size() == 0) {
            score = comment.getScore();
        } else {
            score = ((score * comments.size()) + comment.getScore()) / (comments.size() + 1);
        }
        comments.add(comment);
    }

    public boolean addARestaurant(Restaurant restaurant) {
        if (restaurants[0] == null) {
            restaurants[0] = restaurant;
            restaurant.addADelivery(this);
            return true;
        } else if (restaurants[1] == null) {
            restaurants[1] = restaurant;
            restaurant.addADelivery(this);
            return true;
        }
        return false;
    }

    public boolean removeARestaurant(Restaurant restaurant) {
        if (restaurants[0].equals(restaurant)) {
            restaurants[0] = null;
            restaurant.removeADelivery(this);
            return true;
        } else if (restaurants[1].equals(restaurant)) {
            restaurants[1] = null;
            restaurant.removeADelivery(this);
            return true;
        }
        return false;
    }

    public boolean containRestaurant(Restaurant restaurant) {
        return (restaurant == restaurants[0] || restaurant == restaurants[1]);
    }

    public boolean doesWorkToday() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int numberOfWeekDay = calendar.get(Calendar.DAY_OF_WEEK);
        WeekDays weekDay = null;
        switch (numberOfWeekDay) {
            case 1:
                weekDay = WeekDays.SUNDAY;
                break;
            case 2:
                weekDay = WeekDays.MONDAY;
                break;
            case 3:
                weekDay = WeekDays.TUESDAY;
                break;
            case 4:
                weekDay = WeekDays.WEDNESDAY;
                break;
            case 5:
                weekDay = WeekDays.THURSDAY;
                break;
            case 6:
                weekDay = WeekDays.FRIDAY;
                break;
            case 7:
                weekDay = WeekDays.SATURDAY;
                break;
            default:
                break;
        }
        return workingDays.contains(weekDay);
    }

    public boolean isAvailableNow() {
        return doesWorkToday() && !isDelivering;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Delivery delivery = (Delivery) object;
        return getId() == delivery.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Delivery" + id + " - works for:" + getRestaurants().length + " restaurant(s)" +
                " - vehicle: " + vehicle;
    }
}
