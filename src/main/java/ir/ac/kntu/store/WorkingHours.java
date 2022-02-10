package ir.ac.kntu.store;

import java.time.LocalTime;

public class WorkingHours {
    private LocalTime startTime = null;

    private LocalTime closeTime = null;

    public WorkingHours() {
    }

    public WorkingHours(LocalTime startTime, LocalTime closeTime) {
        this.startTime = startTime;
        this.closeTime = closeTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        startTime = startTime.replaceAll("\\s", "");
        if (startTime.matches("^([0-1][0-9]|[2][0-4]):[0-5][0-9]$")) {
            int hour = Integer.parseInt(startTime.substring(0, 2));
            int minute = Integer.parseInt(startTime.substring(3, 5));
            this.startTime = LocalTime.of(hour, minute);
        }
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        closeTime = closeTime.replaceAll("\\s", "");
        if (closeTime.matches("^([0-1][0-9]|[2][0-4]):[0-5][0-9]$")) {
            int hour = Integer.parseInt(closeTime.substring(0, 2));
            int minute = Integer.parseInt(closeTime.substring(3, 5));
            this.closeTime = LocalTime.of(hour, minute);
        }
    }

    @Override
    public String toString() {
        return startTime + " to " + closeTime;
    }
}
