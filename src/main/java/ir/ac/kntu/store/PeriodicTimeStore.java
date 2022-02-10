package ir.ac.kntu.store;

import java.util.ArrayList;

public interface PeriodicTimeStore {
    ArrayList<WorkingHours> availablePeriodsToOrder();

    void addAnOrderToAPeriod(WorkingHours period);
}
