package ir.ac.kntu.delivery;

public class DeliveryIdGenerator {
    private static int currentId = 10000;

    public static int getNewId() {
        return currentId++;
    }

    public static int getCurrentId() {
        return currentId;
    }
}
