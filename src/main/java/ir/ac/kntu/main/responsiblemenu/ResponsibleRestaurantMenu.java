package ir.ac.kntu.main.responsiblemenu;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.main.*;
import ir.ac.kntu.store.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class  ResponsibleRestaurantMenu {
    private Sort sort;

    private DataBase dataBase;

    private Restaurant restaurant;

    public  ResponsibleRestaurantMenu(DataBase dataBase, Restaurant restaurant) {
        this.dataBase = dataBase;
        this.restaurant = restaurant;
        sort = new Sort(dataBase);
    }

    public void restaurantMenu() {

        System.out.println("1-Show restaurant information");
        System.out.println("2-Show comments of restaurant");
        System.out.println("3-Change restaurant information");
        System.out.println("0-Log out");
        int option = chooseInRange(0, 3);
        restaurantMenuHandler(option);
    }

    private void restaurantMenuHandler(int option) {
        switch (option) {
            case 1:
                showRestaurantInformation();
                break;
            case 2:
                showRestaurantComments();
                break;
            case 3:
                changeRestaurantInformationMenu();
                break;
            case 0:
                return;
            default:
                break;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Restaurant menu***\n");
        restaurantMenu();
    }

    private void showRestaurantInformation() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Show restaurant information***\n");
        System.out.println("Name: " + restaurant.getName());
        System.out.println("Address: " + restaurant.getAddress());
        System.out.println("Price type: " + restaurant.getPriceType());
        System.out.println("Score: " + restaurant.getScore());
        System.out.println("Number of comments: " + restaurant.getComments().size());
        System.out.println("Number of deliveries: " + restaurant.getDeliveries().size());
        System.out.println("Working Hours: " + restaurant.getWorkingHours());
        if(restaurant.isOpen()) {
            System.out.println("This restaurant is open now");
        } else {
            System.out.println("This restaurant is closed now");
        }
    }

    private void showRestaurantComments() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of restaurant***\n");
        if(restaurant.getComments().size() == 0) {
            System.out.println("***This list is empty***\n");
        } else {
            for (Comment comment : restaurant.getComments()) {
                System.out.println(comment + "\n----");
            }
        }
    }

    private void changeRestaurantInformationMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change restaurant information***\n");
        System.out.println("1-Change name");
        System.out.println("2-Change address");
        System.out.println("3-Change price type");
        System.out.println("4-Change Working hours");
        System.out.println("5-Change food menu");
        System.out.println("6-Add a delivery to this restaurant");
        System.out.println("7-Remove a delivery from this restaurant");
        System.out.println("0-Back");
        int option = chooseInRange(0, 7);
        changeARestaurantInformationMenuHandler(option);
    }

    private void changeARestaurantInformationMenuHandler(int option) {
        switch (option) {
            case 1:
                changeRestaurantName();
                break;
            case 2:
                changeRestaurantAddress();
                break;
            case 3:
                changeRestaurantPriceType();
                break;
            case 4:
                changeRestaurantWorkingHours();
                break;
            case 5:
                changeRestaurantFoodMenu();
                break;
            case 6:
                addADeliveryToARestaurant();
                break;
            case 7:
                removeADeliveryFromRestaurant();
                break;
            default:
                return;
        }
    }

    private void changeRestaurantName() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change restaurant name***\n");
        System.out.print("Enter new name: ");
        restaurant.setName(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Name changed successfully***\n");
    }

    private void changeRestaurantAddress() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change restaurant address***\n");
        System.out.print("Enter new address: ");
        restaurant.setAddress(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Address changed successfully***\n");
    }

    private void changeRestaurantPriceType() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change restaurant price type***\n");
        System.out.println("New price type: ");
        System.out.println("1-Luxury");
        System.out.println("2-Intermediate");
        System.out.println("3-Economic");
        int option = chooseInRange(1, 3);
        switch (option) {
            case 1:
                restaurant.setPriceType(PriceType.LUXURY);
                break;
            case 2:
                restaurant.setPriceType(PriceType.INTERMEDIATE);
                break;
            case 3:
                restaurant.setPriceType(PriceType.ECONOMIC);
                break;
            default:
                break;
        }
        System.out.println("***Price type changed successfully***\n");
    }

    private void changeRestaurantWorkingHours() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change restaurant working hours***\n");
        System.out.println("New working hour:\n");
        restaurant.setWorkingHours(getWorkingHours());
        System.out.println("***Working hours changed successfully***\n");
    }

    private void changeRestaurantFoodMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change restaurant food menu***\n");
        System.out.println("1-Add a food");
        System.out.println("2-Remove a food");
        System.out.println("3-Change a food information");
        System.out.println("0-Back");
        int option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                addFoodToMenu();
                break;
            case 2:
                removeAFoodFromMenu();
                break;
            case 3:
                changeAFoodInformation();
                break;
            default:
                return;
        }
    }

    private void addFoodToMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Adding foods to the menu of restaurant***\n");
        int option = 1;
        while (option == 1) {
            System.out.print("Food name: ");
            String foodName = ScannerWrapper.getInstance().nextLine();
            System.out.print("food price: ");
            int foodPrice = ScannerWrapper.getInstance().nextInt();
            System.out.print("Cook time: ");
            int cookTime = ScannerWrapper.getInstance().nextInt();
            restaurant.addAFoodToMenu(foodName, foodPrice, cookTime);
            System.out.println("\n***food added to the menu of restaurant successfully***\n");
            System.out.println("Do you want to add another food to the menu?");
            System.out.println("1-yes");
            System.out.println("2-No");
            option = chooseInRange(1, 2);
        }
        sort.sorting(dataBase.getSortMode());
    }

    private void removeAFoodFromMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***removing a food***\n");
        ArrayList<Food> menu = restaurant.getMenu();
        if(menu.size() == 0) {
            System.out.println("***this list is empty***\n");
            return;
        }
        for (int i=0; i<menu.size(); i++) {
            System.out.println((i+1) + "-" + menu.get(i).getName());
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, menu.size()) - 1;
        if (option + 1 == 0) {
            return;
        }
        String foodName = menu.get(option).getName();
        if(makeSure(foodName)) {
            restaurant.removeFoodFromMenu(menu.get(option));
            System.out.println("***" + foodName + " successfully removed from this restaurant menu***\n");
        }
    }

    private void changeAFoodInformation() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***changing a food information***\n");
        ArrayList<Food> menu = restaurant.getMenu();
        for (int i=0; i<menu.size(); i++) {
            System.out.println((i+1) + "-" + menu.get(i).getName());
        }
        System.out.println("0-Back");
        if(menu.size() == 0) {
            System.out.println("***This lis is empty***\n");
            return;
        }
        int option = chooseInRange(0, menu.size()) - 1;
        if (option + 1 == 0) {
            return;
        }
        Food food = menu.get(option);
        System.out.println("\n--------------------------------------------");
        System.out.println("***changing information of " + food.getName() + "***\n");
        System.out.println("1-Change food name");
        System.out.println("2-Change food price");
        System.out.println("3-Change cook time");
        System.out.println("0-Back");
        option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                changeFoodName(food);
                break;
            case 2:
                changeFoodPrice(food);
                break;
            case 3:
                changeFoodCookTime(food);
                break;
            default:
                return;
        }
    }

    private void changeFoodName(Food food) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing " + food.getName() + " name***\n");
        System.out.print("Enter new name: ");
        food.setName(ScannerWrapper.getInstance().nextLine());
        System.out.println("***Food name changed successfully***\n");
    }

    private void changeFoodPrice(Food food) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing " + food.getName() + " price***\n");
        System.out.print("Enter new price: ");
        food.setPrice(ScannerWrapper.getInstance().nextInt());
        System.out.println("***Food price changed successfully***\n");
    }

    private void changeFoodCookTime(Food food) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing " + food.getName() + " cook time***\n");
        System.out.print("Enter new cook time: ");
        food.setCookTime(ScannerWrapper.getInstance().nextInt());
        System.out.println("***Food cook time changed successfully***\n");
    }

    private void addADeliveryToARestaurant() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Add a delivery to " + restaurant.getName() + " restaurant***\n");
        ArrayList<Delivery> availableDeliveries = new ArrayList<>();
        for (Delivery delivery : dataBase.getDeliveries()) {
            if (delivery.getRestaurants().length != 2) {
                availableDeliveries.add(delivery);
            }
        }
        if (availableDeliveries.size() == 0) {
            System.out.println("***There is no available delivery***\n");
        } else {
            for (int i = 0; i < availableDeliveries.size(); i++) {
                System.out.println((i + 1) + "-" + availableDeliveries.get(i));
            }
            System.out.println("0-Back");
            int option = chooseInRange(0, availableDeliveries.size()) - 1;
            if (option + 1 == 0) {
                return;
            }
            Delivery delivery = availableDeliveries.get(option);
            delivery.addARestaurant(restaurant);
            System.out.println("***Delivery " + delivery.getId() + " successfully added to " + restaurant.getName() +
                    " restaurant***\n");
        }
    }

    private void removeADeliveryFromRestaurant() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Remove a delivery from " + restaurant.getName() + " restaurant***\n");
        ArrayList<Delivery> deliveries = restaurant.getDeliveries();
        if (deliveries.size() == 0) {
            System.out.println("***This list is empty***\n");
            return;
        }
        for (int i=0; i< deliveries.size(); i++) {
            System.out.println((i+1) + "-" + deliveries.get(i));
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, deliveries.size()) - 1;
        if (option + 1 == 0) {
            return;
        }
        Delivery delivery = deliveries.get(option);
        String deliveryId = "delivery " + Integer.toString(delivery.getId());
        if(makeSure(deliveryId)) {
            delivery.removeARestaurant(restaurant);
            System.out.println("***Delivery " + delivery.getId() + " successfully removed from " + restaurant + "***");
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

    private WorkingHours getWorkingHours() {
        System.out.print("Time of start of working:");
        String stringStartTime = ScannerWrapper.getInstance().nextLine().replaceAll("\\s", "");
        while (!stringStartTime.matches("^([0-1][0-9]|[2][0-4]):[0-5][0-9]$")) {
            System.out.print("format of input is wrong, try again.(correct format: XX:XX) : ");
            stringStartTime = ScannerWrapper.getInstance().nextLine().replaceAll("\\s", "");
        }
        System.out.print("Time of end of working:");
        String stringCloseTime = ScannerWrapper.getInstance().nextLine().replaceAll("\\s", "");
        while (!stringCloseTime.matches("^([0-1][0-9]|[2][0-4]):[0-5][0-9]$")) {
            System.out.print("format of input is wrong, try again.(correct format: XX:XX) : ");
            stringCloseTime = ScannerWrapper.getInstance().nextLine().replaceAll("\\s", "");
        }
        int hour = Integer.parseInt(stringStartTime.substring(0, 2));
        int minute = Integer.parseInt(stringStartTime.substring(3, 5));
        LocalTime startTime = LocalTime.of(hour, minute);
        hour = Integer.parseInt(stringCloseTime.substring(0, 2));
        minute = Integer.parseInt(stringCloseTime.substring(3, 5));
        LocalTime closeTime = LocalTime.of(hour, minute);
        if(startTime.compareTo(closeTime) >= 0) {
            System.out.println("***Close time should be greater than start time. Try again***\n");
            return getWorkingHours();
        }
        WorkingHours workingHours = new WorkingHours(startTime, closeTime);
        return workingHours;
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
