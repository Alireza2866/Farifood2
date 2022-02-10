package ir.ac.kntu.main.adminmenu;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.SalaryType;
import ir.ac.kntu.delivery.Vehicle;
import ir.ac.kntu.delivery.WeekDays;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.store.Restaurant;
import ir.ac.kntu.main.*;
import java.util.ArrayList;

public class HandleDeliveryMenu {
    private Sort sort;

    private DataBase dataBase;

    public HandleDeliveryMenu(DataBase dataBase) {
        this.dataBase = dataBase;
        sort = new Sort(dataBase);
    }

    public void deliveryMenu() {
        System.out.println("1-Add a new delivery");
        System.out.println("2-Show a delivery information");
        System.out.println("3-Show comments of delivery");
        System.out.println("4-Show delivered orders of delivery");
        System.out.println("5-Change a delivery information");
        System.out.println("6-remove a delivery");
        System.out.println("0-Back");
        int option = chooseInRange(0, 6);
        deliveryMenuHandler(option);
    }

    private void deliveryMenuHandler(int option) {
        switch (option) {
            case 1:
                addANewDelivery();
                break;
            case 2:
                showADeliveryInformation();
                break;
            case 3:
                showADeliveryComments();
                break;
            case 4:
                showADeliveryOrderHistory();
                break;
            case 5:
                changeADeliveryInformationMenu();
                break;
            case 6:
                removeADelivery();
                break;
            case 0:
                return;
            default:
                break;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Delivery menu***\n");
        deliveryMenu();
    }

    private void addANewDelivery() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Add a new delivery***\n");
        System.out.println("Vehicle: ");
        Vehicle vehicle = chooseVehicle();
        System.out.println("Salary type: ");
        SalaryType salaryType = chooseSalaryType();
        System.out.print("Salary: ");
        int salary = ScannerWrapper.getInstance().nextInt();
        Delivery delivery = new Delivery(vehicle, salaryType, salary);
        System.out.println("Working days: ");
        int option = 1;
        while (option == 1) {
            chooseWorkingDays(delivery);
            System.out.println("Do you want to add another day?");
            System.out.println("1-yes");
            System.out.println("2-No");
            option = chooseInRange(1, 2);
        }
        option = 1;
        while (option == 1) {
            addARestaurantToADelivery(delivery);
            if (delivery.getRestaurants().length == 2) {
                break;
            }
            System.out.println("Do you want to add another restaurant?");
            System.out.println("1-Yes");
            System.out.println("2-No");
            option = chooseInRange(1, 2);
        }
        dataBase.addADelivery(delivery);
        sort.sorting(dataBase.getSortMode());
        System.out.println("***Delivery " + delivery.getId() + " successfully added***\n");
    }

    private void showADeliveryInformation() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Show a delivery information***\n");
        Delivery delivery = dataBase.chooseDelivery();
        if (delivery == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Information of " + delivery.getId() + "***\n");
        System.out.println("ID: " + delivery.getId());
        System.out.println("Vehicle: " + delivery.getVehicle());
        System.out.println("Salary type: " + delivery.getSalaryType());
        System.out.println("Salary: " + delivery.getSalary());
        System.out.println("Score: " + delivery.getScore());
        System.out.println("Number of comments: " + delivery.getComments().size());
        System.out.println("Restaurant(s) that works for:    ");
        for (Restaurant restaurant : delivery.getRestaurants()) {
            System.out.println(restaurant.getName());
        }
        System.out.println("\nWorking days: " + delivery.getWorkingDays());
        if (delivery.doesWorkToday()) {
            System.out.println("this delivery works today.");
        } else {
            System.out.println("this delivery doesn't work today");
        }
    }

    private void showADeliveryComments() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of a delivery***\n");
        Delivery delivery = dataBase.chooseDelivery();
        if (delivery == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of a delivery " + delivery.getId() + "***\n");
        if (delivery.getComments().size() == 0) {
            System.out.println("***This list is empty***\n");
        } else {
            for (Comment comment : delivery.getComments()) {
                System.out.println(comment + "\n----");
            }
        }
    }

    private void showADeliveryOrderHistory() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing delivered orders of a delivery***\n");
        Delivery delivery = dataBase.chooseDelivery();
        if (delivery == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing delivered orders of a delivery " + delivery.getId() + "***\n");
        if (delivery.getOrderHistory().size() == 0) {
            System.out.println("***This list is empty***\n");
        } else {
            System.out.println("***Order history of delivery " + delivery.getId() + "***\n");
            for (Order order : delivery.getOrderHistory()) {
                System.out.println(order + "\n----");
            }
        }
    }

    private void changeADeliveryInformationMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change a delivery information***\n");
        Delivery delivery = dataBase.chooseDelivery();
        if (delivery == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***changing information of delivery " + delivery.getId() + "***\n");
        System.out.println("1-Change vehicle");
        System.out.println("2-Change salary type");
        System.out.println("3-Change salary");
        System.out.println("4-Change working days");
        System.out.println("5-Change restaurants that this delivery works for");
        System.out.println("0-Back");
        int option = chooseInRange(0, 5);
        changeADeliveryInformationMenuHandler(option, delivery);
    }

    private void changeADeliveryInformationMenuHandler(int option, Delivery delivery) {
        switch (option) {
            case 1:
                changeADeliveryVehicle(delivery);
                break;
            case 2:
                changeADeliverySalaryType(delivery);
                break;
            case 3:
                changeADeliverySalary(delivery);
                break;
            case 4:
                changeADeliveryWorkingDays(delivery);
                break;
            case 5:
                changeADeliveryRestaurants(delivery);
            default:
                return;
        }
    }

    public void changeADeliveryVehicle(Delivery delivery) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***change delivery " + delivery.getId() + " vehicle***\n");
        System.out.println("New vehicle:");
        delivery.setVehicle(chooseVehicle());
        System.out.println("***Vehicle changed successfully***\n");
    }

    public void changeADeliverySalaryType(Delivery delivery) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***change delivery " + delivery.getId() + " salary type***\n");
        delivery.setSalaryType(chooseSalaryType());
        System.out.println("***Salary type changed successfully***\n");
    }

    public void changeADeliverySalary(Delivery delivery) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***change delivery " + delivery.getId() + " salary***\n");
        System.out.print("Enter new salary: ");
        delivery.setSalary(ScannerWrapper.getInstance().nextInt());
        System.out.println("***Salary changed successfully***\n");
    }

    private void changeADeliveryWorkingDays(Delivery delivery) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***change delivery " + delivery.getId() + " working days***\n");
        System.out.println("Working days: " + delivery.getWorkingDays());
        System.out.println("1-Add a working day");
        System.out.println("2-remove a working day");
        System.out.println("0-Back");
        int option = chooseInRange(0, 2);
        System.out.println("\n--------------------------------------------");
        switch (option) {
            case 1:
                System.out.println("***Adding a working day to delivery " + delivery.getId() + "***\n");
                if (delivery.getWorkingDays().size() == 7) {
                    System.out.println("***this delivery works in whole days of the week***\n");
                } else {
                    chooseWorkingDays(delivery);
                }
                break;
            case 2:
                System.out.println("***removing a working day from delivery " + delivery.getId() + "***\n");
                if (delivery.getWorkingDays().size() == 0) {
                    System.out.println("***this delivery doesn't work in any days of the week***\n");
                } else {
                    removeAWorkingDayFromADelivery(delivery);
                }
                break;
            default:
                return;
        }
    }

    private void removeAWorkingDayFromADelivery(Delivery delivery) {
        ArrayList<WeekDays> workingDays = delivery.getWorkingDays();
        for (int i = 0; i < workingDays.size(); i++) {
            System.out.println((i + 1) + "-" + workingDays.get(i));
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, workingDays.size()) - 1;
        if (option + 1 == 0) {
            return;
        }
        WeekDays workingDay = workingDays.get(option);
        if(makeSure(workingDay.toString())) {
            workingDays.remove(workingDay);
            System.out.println("***Working day removed successfully***");
        }
    }

    private void changeADeliveryRestaurants(Delivery delivery) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***change delivery " + delivery.getId() + " restaurants***\n");
        System.out.println("Restaurant(s) that works for:    ");
        for (Restaurant restaurant : delivery.getRestaurants()) {
            System.out.println(restaurant.getName());
        }
        System.out.println("1-Add a restaurant");
        System.out.println("2-remove a restaurant");
        System.out.println("0-Back");
        int option = chooseInRange(0, 2);
        System.out.println("\n--------------------------------------------");
        switch (option) {
            case 1:
                System.out.println("***Adding a restaurant to delivery " + delivery.getId() + "***\n");
                if (delivery.getRestaurants().length == 2) {
                    System.out.println("***this delivery already works for 2 restaurants***\n");
                } else {
                    addARestaurantToADelivery(delivery);
                }
                break;
            case 2:
                System.out.println("***removing a restaurant from delivery " + delivery.getId() + "***\n");
                if (delivery.getWorkingDays().size() == 0) {
                    System.out.println("***this delivery doesn't work for any restaurants***\n");
                } else {
                    removeARestaurantFromADelivery(delivery);
                }
                break;
            default:
                return;
        }
    }

    private void removeARestaurantFromADelivery(Delivery delivery) {
        System.out.println(delivery.getRestaurants());
        for (int i = 0; i < delivery.getRestaurants().length; i++) {
            System.out.println((i + 1) + "-" + delivery.getRestaurants()[i]);
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, delivery.getRestaurants().length) - 1;
        if (option + 1 == 0) {
            return;
        }
        Restaurant restaurant = delivery.getRestaurants()[option];
        if(makeSure(restaurant.getName())) {
            delivery.removeARestaurant(restaurant);
            System.out.println("***Restaurant removed successfully***");
        }
    }

    private void removeADelivery() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Remove a delivery***\n");
        Delivery delivery = dataBase.chooseDelivery();
        if (delivery == null) {
            return;
        }
        String deliveryId = "delivery " + Integer.toString(delivery.getId());
        if (makeSure(deliveryId)) {
            dataBase.removeADelivery(delivery);
            System.out.println("***Delivery " + deliveryId + "***\n");
        }
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

    private Restaurant chooseRestaurant() {
        if (dataBase.getRestaurants().size() == 0) {
            System.out.println("***This list is empty***\n");
            return null;
        }
        for (int i=0; i< dataBase.getRestaurants().size(); i++) {
            System.out.println((i+1) + "-" + dataBase.getRestaurants().get(i));
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, dataBase.getRestaurants().size()) - 1;
        if (option + 1 == 0) {
            return null;
        }
        return dataBase.getRestaurants().get(option);
    }

    private Vehicle chooseVehicle() {
        System.out.println("1-Car");
        System.out.println("2-Motorcycle");
        System.out.println("3-Bicycle");
        Vehicle vehicle = null;
        int option = chooseInRange(1, 3);
        switch (option) {
            case 1:
                vehicle = Vehicle.CAR;
                break;
            case 2:
                vehicle = Vehicle.MOTORCYCLE;
                break;
            case 3:
                vehicle = Vehicle.BICYCLE;
                break;
            default:
                break;
        }
        return vehicle;
    }

    private SalaryType chooseSalaryType() {
        System.out.println("1-Hourly");
        System.out.println("2-Per each delivery");
        SalaryType salaryType = null;
        int option = chooseInRange(1, 2);
        switch (option) {
            case 1:
                salaryType = SalaryType.HOURLY;
                break;
            case 2:
                salaryType = SalaryType.PER_EACH_DELIVERY;
                break;
            default:
                break;
        }
        return salaryType;
    }

    private void chooseWorkingDays(Delivery delivery) {
        ArrayList<WeekDays> workingDays = delivery.getWorkingDays();
        System.out.println("1-Saturday");
        System.out.println("2-Sunday");
        System.out.println("3-Monday");
        System.out.println("4-Tuesday");
        System.out.println("5-Wednesday");
        System.out.println("6-Thursday");
        System.out.println("7-Friday");
        WeekDays weekDay = null;
        int option = chooseInRange(1, 7);
        switch (option) {
            case 1:
                weekDay = WeekDays.SUNDAY;
                break;
            case 2:
                weekDay = WeekDays.MONDAY;
                break;
            case 3:
                weekDay = WeekDays.TUESDAY;
                break;
            case 4:
                weekDay = WeekDays.WEDNESDAY;
                break;
            case 5:
                weekDay = WeekDays.THURSDAY;
                break;
            case 6:
                weekDay = WeekDays.FRIDAY;
                break;
            case 7:
                weekDay = WeekDays.SATURDAY;
                break;
            default:
                break;
        }
        if (workingDays.contains(weekDay)) {
            System.out.println("***This day already exist in working days of this delivery***\n");
        } else {
            workingDays.add(weekDay);
            System.out.println("***working day added successfully***\n");
        }
    }

    private void addARestaurantToADelivery(Delivery delivery) {
        System.out.println("***Adding a restaurant to delivery " + delivery.getId() + "***\n");
        if (delivery.getRestaurants().length == 2) {
            System.out.println("***this delivery already works for 2 restaurants***\n");
            return;
        }
        Restaurant restaurant = chooseRestaurant();
        if (restaurant == null) {
            return;
        }
        if(delivery.containRestaurant(restaurant)) {
            System.out.println("***This delivery already works for this restaurant***\n");
        } else {
            delivery.addARestaurant(restaurant);
            System.out.println("***Restaurant added to delivery successfully***\n");
        }
    }

    private boolean makeSure(String name) {
        System.out.println("Are you sure you want to remove " + name + " from this list?");
        System.out.println("1-Yes");
        System.out.println("2-No");
        int option = chooseInRange(1, 2);
        if (option == 1) {
            return true;
        }
        return false;
    }
}
