package ir.ac.kntu.delivery;

public enum Vehicle {
    MOTORCYCLE, BICYCLE, CAR;

    @Override
    public String toString() {
        switch (this) {
            case CAR:
                return "Car";
            case MOTORCYCLE:
                return "Motorcycle";
            case BICYCLE:
                return "Bicycle";
            default:
                return "";
        }
    }
}
