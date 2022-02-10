package ir.ac.kntu.store;

import ir.ac.kntu.comment.Commentable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class FruitShop extends Store implements Commentable, PeriodicTimeStore {
    private int deliveriesNumber;

    private double purchasableFruitInEachPeriod;

    private ArrayList<Fruit> fruits;

    private HashMap<WorkingHours, Integer> ordersInEachPeriod;

    public FruitShop(String name, String address, WorkingHours workingHours, double purchasableFruitInEachPeriod) {
        super(name, address, workingHours);
        deliveriesNumber = 2;
        fruits = new ArrayList<>();
        ordersInEachPeriod = new HashMap<>();
        if (purchasableFruitInEachPeriod > 0) {
            this.purchasableFruitInEachPeriod = purchasableFruitInEachPeriod;
        } else {
            this.purchasableFruitInEachPeriod = 1;
        }
        if (workingHours.getStartTime().getMinute() != 0 || workingHours.getCloseTime().getMinute() != 0) {
            LocalTime startTime = LocalTime.of(workingHours.getStartTime().getHour(), 0);
            LocalTime closeTime = LocalTime.of(workingHours.getCloseTime().getHour(), 0);
            setWorkingHours(new WorkingHours(startTime, closeTime));
        }
        for (int hour = workingHours.getStartTime().getHour(); hour < workingHours.getCloseTime().getHour(); hour += 2) {
            LocalTime time1 = LocalTime.of(hour, 0);
            LocalTime time2 = LocalTime.of(hour + 2, 0);
            WorkingHours period = new WorkingHours(time1, time2);
            ordersInEachPeriod.put(period, 0);
        }
    }

    public int getDeliveriesNumber() {
        return deliveriesNumber;
    }

    public void setDeliveriesNumber(int deliveriesNumber) {
        if (deliveriesNumber >= 2) {
            this.deliveriesNumber = deliveriesNumber;
        }
    }

    public double getPurchasableFruitInEachPeriod() {
        return purchasableFruitInEachPeriod;
    }

    public void setPurchasableFruitInEachPeriod(double purchasableFruitInEachPeriod) {
        this.purchasableFruitInEachPeriod = purchasableFruitInEachPeriod;
    }

    public boolean canConfirmOrder() {
        return availablePeriodsToOrder().size() > 0;
    }

    @Override
    public ArrayList<WorkingHours> availablePeriodsToOrder() {
        ArrayList<WorkingHours> availablePeriods = new ArrayList<>();
        for (int hour = getWorkingHours().getStartTime().getHour(); hour < getWorkingHours().getCloseTime().getHour(); hour += 2) {
            LocalTime time1 = LocalTime.of(hour, 0);
            LocalTime time2 = LocalTime.of(hour + 2, 0);
            WorkingHours period = new WorkingHours(time1, time2);
            if(ordersInEachPeriod.get(period) < deliveriesNumber) {
                availablePeriods.add(period);
            }
        }
        return availablePeriods;
    }

    @Override
    public void addAnOrderToAPeriod(WorkingHours period) {
        ordersInEachPeriod.put(period, ordersInEachPeriod.get(period) + 1);
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public ArrayList<Fruit> getRemainedFruits() {
        ArrayList<Fruit> remainedFruits = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (fruit.getWeight() > 0.1) {
                remainedFruits.add(fruit);
            }
        }
        return remainedFruits;
    }

    public ArrayList<Fruit> outOfStock() {
        ArrayList<Fruit> outOfStock = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (!getRemainedFruits().contains(fruit)) {
                outOfStock.add(fruit);
            }
        }
        return outOfStock;
    }

    public HashMap<WorkingHours, Integer> getOrdersInEachPeriod() {
        return ordersInEachPeriod;
    }

    @Override
    public String toString() {
        return getName() + " fruit shop";
    }
}
