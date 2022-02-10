package ir.ac.kntu.delivery;

public enum WeekDays {
    SATURDAY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;

    @Override
    public String toString() {
        switch (this) {
            case SATURDAY:
                return "Saturday";
            case SUNDAY:
                return "Sunday";
            case MONDAY:
                return "Monday";
            case TUESDAY:
                return "Tuesday";
            case WEDNESDAY:
                return "Wednesday";
            case THURSDAY:
                return "Thursday";
            case FRIDAY:
                return "Friday";
            default:
                return "";
        }
    }
}
