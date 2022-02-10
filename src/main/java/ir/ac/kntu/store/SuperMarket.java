package ir.ac.kntu.store;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class SuperMarket extends Store implements PeriodicTimeStore{
    private int deliveriesNumber;

    private int deliveryPrice;

    private int premiumSubscriptionPrice;

    private ArrayList<Stuff> stuffs;

    private HashMap<Stuff, Integer> stuffsNumber;

    private HashMap<WorkingHours, Integer> ordersInEachPeriod;

    public SuperMarket(String name, String address, WorkingHours workingHours, int deliveryPrice, int premiumSubscriptionPrice) {
        super(name, address, workingHours);
        this.deliveryPrice = deliveryPrice;
        this.premiumSubscriptionPrice = premiumSubscriptionPrice;
        deliveriesNumber = 2;
        stuffs = new ArrayList<>();
        stuffsNumber = new HashMap<>();
        ordersInEachPeriod = new HashMap<>();
        if (workingHours.getStartTime().getMinute() != 0 || workingHours.getCloseTime().getMinute() != 0) {
            LocalTime startTime = LocalTime.of(workingHours.getStartTime().getHour(), 0);
            LocalTime closeTime = LocalTime.of(workingHours.getCloseTime().getHour(), 0);
            setWorkingHours(new WorkingHours(startTime, closeTime));
        }
        for (int hour = workingHours.getStartTime().getHour(); hour < workingHours.getCloseTime().getHour(); hour++) {
            LocalTime time1 = LocalTime.of(hour, 0);
            LocalTime time2 = LocalTime.of(hour + 1, 0);
            WorkingHours period = new WorkingHours(time1, time2);
            ordersInEachPeriod.put(period, 0);
        }
    }

    public int getDeliveriesNumber() {
        return deliveriesNumber;
    }

    public void setDeliveriesNumber(int deliveriesNumber) {
        this.deliveriesNumber = deliveriesNumber;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public int getPremiumSubscriptionPrice() {
        return premiumSubscriptionPrice;
    }

    public void setPremiumSubscriptionPrice(int premiumSubscriptionPrice) {
        this.premiumSubscriptionPrice = premiumSubscriptionPrice;
    }

    public ArrayList<Stuff> getStuffs() {
        return stuffs;
    }

    public ArrayList<Stuff> getRemainedStuffs() {
        ArrayList<Stuff> remainedStuffs = new ArrayList<>();
        for (Stuff stuff : stuffs) {
            if (stuffsNumber.get(stuff) > 0) {
                remainedStuffs.add(stuff);
            }
        }
        return remainedStuffs;
    }

    public ArrayList<Stuff> outOfStock() {
        ArrayList<Stuff> outOfStock = new ArrayList<>();
        for (Stuff stuff : stuffs) {
            if (!getRemainedStuffs().contains(stuff)) {
                outOfStock.add(stuff);
            }
        }
        return outOfStock;
    }

    @Override
    public ArrayList<WorkingHours> availablePeriodsToOrder() {
        ArrayList<WorkingHours> availablePeriods = new ArrayList<>();
        for (int hour = getWorkingHours().getStartTime().getHour(); hour < getWorkingHours().getCloseTime().getHour(); hour++) {
            LocalTime time1 = LocalTime.of(hour, 0);
            LocalTime time2 = LocalTime.of(hour + 1, 0);
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

    public void addAStuff(Stuff stuff, int number) {
        stuffs.add(stuff);
        stuffsNumber.put(stuff, number);
    }

    public int getAStuffNumber(Stuff stuff) {
        return stuffsNumber.get(stuff);
    }

    public void changeAStuffNumber(Stuff stuff, int number) {
        if (stuffs.contains(stuff)) {
            stuffsNumber.put(stuff, number);
        }
    }

    public double getDeliveryPrice(WorkingHours workingHours) {
        if (ordersInEachPeriod.get(workingHours) > deliveriesNumber / 2) {
            return deliveryPrice * 1.5;
        }
        return deliveryPrice;
    }

    @Override
    public String toString() {
        return getName() + " super market";
    }
}
