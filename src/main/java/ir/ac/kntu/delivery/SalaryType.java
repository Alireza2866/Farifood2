package ir.ac.kntu.delivery;

public enum SalaryType {
    HOURLY, PER_EACH_DELIVERY;

    @Override
    public String toString() {
        switch (this) {
            case HOURLY:
                return "Hourly";
            case PER_EACH_DELIVERY:
                return "Per each delivery";
            default:
                return "";
        }
    }
}
