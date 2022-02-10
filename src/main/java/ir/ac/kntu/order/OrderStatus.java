package ir.ac.kntu.order;

public enum OrderStatus {
    PROCESSING, DELIVERING, DELIVERED;

    @Override
    public String toString() {
        switch (this) {
            case PROCESSING:
                return "Processing";
            case DELIVERING:
                return "Delivering";
            case DELIVERED:
                return "Delivered";
            default:
                return "";
        }
    }
}
