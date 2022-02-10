package ir.ac.kntu.main;

import ir.ac.kntu.store.FruitShop;
import ir.ac.kntu.store.SuperMarket;
import ir.ac.kntu.store.WorkingHours;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.store.Restaurant;
import ir.ac.kntu.user.Responsible;
import ir.ac.kntu.main.SortMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DataBase {
    private ArrayList<Restaurant> restaurants;

    private ArrayList<SuperMarket> superMarkets;

    private ArrayList<FruitShop> fruitShops;

    private ArrayList<Delivery> deliveries;

    private ArrayList<Customer> customers;

    private ArrayList<Admin> admins;

    private ArrayList<Responsible> responsibles;

    private ArrayList<Order> processingOrders;

    private ArrayList<Order> deliveringOrders;

    private ArrayList<Order> deliveredOrders;

    private ArrayList<PurchasedPeriodsFromAFruitShop> purchasedPeriodsFromAFruitShopForCustomers;

    private SortMode sortMode;

    public DataBase() {
        restaurants = new ArrayList<>();
        superMarkets = new ArrayList<>();
        fruitShops = new ArrayList<>();
        deliveries = new ArrayList<>();
        customers = new ArrayList<>();
        processingOrders = new ArrayList<>();
        deliveringOrders = new ArrayList<>();
        deliveredOrders = new ArrayList<>();
        admins = new ArrayList<>();
        responsibles = new ArrayList<>();
        purchasedPeriodsFromAFruitShopForCustomers = new ArrayList<>();
    }

    public SortMode getSortMode() {
        return sortMode;
    }

    public void setSortMode(SortMode sortMode) {
        this.sortMode = sortMode;
    }

    public void addARestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void removeARestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant);
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addASuperMarket(SuperMarket superMarket) {
        superMarkets.add(superMarket);
    }

    public void removeASuperMarket(SuperMarket superMarket) {
        superMarkets.remove(superMarket);
    }

    public ArrayList<SuperMarket> getSuperMarkets() {
        return superMarkets;
    }

    public void setSuperMarkets(ArrayList<SuperMarket> superMarkets) {
        this.superMarkets = superMarkets;
    }

    public void addAFruitShop(FruitShop fruitShop) {
        fruitShops.add(fruitShop);
    }

    public void removeAFruitShop(FruitShop fruitShop) {
        fruitShops.remove(fruitShop);
    }

    public ArrayList<FruitShop> getFruitShops() {
        return fruitShops;
    }

    public void setFruitShops(ArrayList<FruitShop> fruitShops) {
        this.fruitShops = fruitShops;
    }

    public void addADelivery(Delivery delivery) {
        deliveries.add(delivery);
    }

    public void removeADelivery(Delivery delivery) {
        deliveries.remove(delivery);
    }

    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(ArrayList<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void addACustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeACustomer(Customer customer) {
        customers.remove(customer);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void addAnAdmin(Admin admin) {
        admins.add(admin);
    }

    public void removeAnAdmin(Admin admin) {
        admins.remove(admin);
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void addAResponsible(Responsible responsible) {
        responsibles.add(responsible);
    }

    public void removeAResponsible(Responsible responsible) {
        responsibles.remove(responsible);
    }

    public ArrayList<Responsible> getResponsibles() {
        return responsibles;
    }

    public void addAProcessingOrder(Order order) {
        processingOrders.add(order);
    }

    public void removeAProcessingOrder(Order order) {
        processingOrders.remove(order);
    }

    public ArrayList<Order> getProcessingOrders() {
        return processingOrders;
    }

    public void addADeliveringOrder(Order order) {
        deliveringOrders.add(order);
    }

    public void removeADeliveringOrder(Order order) {
        deliveringOrders.remove(order);
    }

    public ArrayList<Order> getDeliveringOrders() {
        return deliveringOrders;
    }

    public void addADeliveredOrder(Order order) {
        deliveredOrders.add(order);
    }

    public ArrayList<Order> getDeliveredOrders() {
        return deliveredOrders;
    }

    public Customer chooseCustomer() {
        if (getCustomers().size() == 0) {
            System.out.println("***This list is empty***\n");
            return null;
        }
        for (int i=0; i< getCustomers().size(); i++) {
            System.out.println((i+1) + "-" + getCustomers().get(i));
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, getCustomers().size()) - 1;
        if (option + 1 == 0) {
            return null;
        }
        return getCustomers().get(option);
    }

    public Restaurant chooseRestaurant() {
        if (getRestaurants().size() == 0) {
            System.out.println("***This list is empty***\n");
            return null;
        }
        for (int i=0; i< getRestaurants().size(); i++) {
            System.out.println((i+1) + "-" + getRestaurants().get(i));
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, getRestaurants().size()) - 1;
        if (option + 1 == 0) {
            return null;
        }
        return getRestaurants().get(option);
    }

    public Delivery chooseDelivery() {
        if (getDeliveries().size() == 0) {
            System.out.println("***This list is empty***\n");
            return null;
        }
        for (int i=0; i< getDeliveries().size(); i++) {
            System.out.println((i+1) + "-" + getDeliveries().get(i));
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, getDeliveries().size()) - 1;
        if (option + 1 == 0) {
            return null;
        }
        return getDeliveries().get(option);
    }

    public SuperMarket chooseSuperMarket() {
        if (getSuperMarkets().size() == 0) {
            System.out.println("***This list is empty***\n");
            return null;
        }
        for (int i=0; i< getSuperMarkets().size(); i++) {
            System.out.println((i+1) + "-" + getSuperMarkets().get(i));
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, getSuperMarkets().size()) - 1;
        if (option + 1 == 0) {
            return null;
        }
        return getSuperMarkets().get(option);
    }

    public FruitShop chooseFruitShop() {
        if (getFruitShops().size() == 0) {
            System.out.println("***This list is empty***\n");
            return null;
        }
        for (int i=0; i< getFruitShops().size(); i++) {
            System.out.println((i+1) + "-" + getFruitShops().get(i));
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, getFruitShops().size()) - 1;
        if (option + 1 == 0) {
            return null;
        }
        return getFruitShops().get(option);
    }

    private int chooseInRange(int from, int to) {
        System.out.print("->");
        int option = ScannerWrapper.getInstance().nextInt();
        while (!(from <= option && option <= to)) {
            System.out.println("***Invalid input***\n");
            System.out.print("->");
            option = ScannerWrapper.getInstance().nextInt();
        }
        return option;
    }

    public void addAPurchasedPeriodFromAFruitShopToACustomer(Customer customer, FruitShop fruitShop, WorkingHours period) {
        PurchasedPeriodsFromAFruitShop purchasedPeriodsFromAFruitShop1 = new PurchasedPeriodsFromAFruitShop(customer, fruitShop);
        for (PurchasedPeriodsFromAFruitShop purchasedPeriodsFromAFruitShop2 : purchasedPeriodsFromAFruitShopForCustomers) {
            if (purchasedPeriodsFromAFruitShop1.equals(purchasedPeriodsFromAFruitShop2)) {
                purchasedPeriodsFromAFruitShop2.addAPeriod(period);
                return;
            }
        }
        purchasedPeriodsFromAFruitShop1.addAPeriod(period);
        purchasedPeriodsFromAFruitShopForCustomers.add(purchasedPeriodsFromAFruitShop1);
    }

    public boolean canCustomerPurchaseFromAFruitShopAtAPeriod(Customer customer, FruitShop fruitShop, WorkingHours period) {
        PurchasedPeriodsFromAFruitShop purchasedPeriodsFromAFruitShop1 = new PurchasedPeriodsFromAFruitShop(customer, fruitShop);
        boolean check = false;
        for (PurchasedPeriodsFromAFruitShop purchasedPeriodsFromAFruitShop2 : purchasedPeriodsFromAFruitShopForCustomers) {
            if (purchasedPeriodsFromAFruitShop1.equals(purchasedPeriodsFromAFruitShop2)) {
                if (purchasedPeriodsFromAFruitShop2.containPeriod(period)) {
                    return false;
                }
            }
        }
        return true;
    }

    public class PurchasedPeriodsFromAFruitShop {
        private Customer customer;

        private FruitShop fruitShop;

        private ArrayList<WorkingHours> purchasedPeriods;

        public PurchasedPeriodsFromAFruitShop(Customer customer, FruitShop fruitShop) {
            this.customer = customer;
            this.fruitShop = fruitShop;
            purchasedPeriods = new ArrayList<>();
        }

        public void addAPeriod(WorkingHours period) {
            purchasedPeriods.add(period);
        }

        public boolean containPeriod(WorkingHours period) {
            return purchasedPeriods.contains(period);
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()){
                return false;
            }
            PurchasedPeriodsFromAFruitShop that = (PurchasedPeriodsFromAFruitShop) object;
            return Objects.equals(customer, that.customer) && Objects.equals(fruitShop, that.fruitShop);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customer, fruitShop);
        }
    }
}
