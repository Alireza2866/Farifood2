package ir.ac.kntu.main.adminmenu;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.main.*;
import ir.ac.kntu.store.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class HandleRestaurantMenu {
    private Sort sort;

    private DataBase dataBase;

    public HandleRestaurantMenu(DataBase dataBase) {
        this.dataBase = dataBase;
        sort = new Sort(dataBase);
    }

    public void restaurantMenu() {
        System.out.println("1-Add a new restaurant");
        System.out.println("2-Show a restaurant information");
        System.out.println("3-Show comments of a restaurant");
        System.out.println("4-Show all comments of a food in all restaurants");
        System.out.println("5-Change a restaurant information");
        System.out.println("6-Remove a restaurant");
        System.out.println("0-Back");
        int option = chooseInRange(0, 6);
        restaurantMenuHandler(option);
    }

    private void restaurantMenuHandler(int option) {
        switch (option) {
            case 1:
                addANewRestaurant();
                break;
            case 2:
                showARestaurantInformation();
                break;
            case 3:
                showARestaurantComments();
                break;
            case 4:
                showAllCommentsOfAFood();
                break;
            case 5:
                changeARestaurantInformationMenu();
                break;
            case 6:
                removeARestaurant();
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

    private void addANewRestaurant() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Add a new restaurant***\n");
        System.out.print("Name: ");
        String name = ScannerWrapper.getInstance().nextLine();
        System.out.print("Address: ");
        String address = ScannerWrapper.getInstance().nextLine();
        System.out.println("Price type: ");
        System.out.println("1-Luxury");
        System.out.println("2-Intermediate");
        System.out.println("3-Economic");
        PriceType priceType = null;
        int option = chooseInRange(1, 3);
        switch (option) {
            case 1:
                priceType = PriceType.LUXURY;
                break;
            case 2:
                priceType = PriceType.INTERMEDIATE;
                break;
            case 3:
                priceType = PriceType.ECONOMIC;
                break;
            default:
                break;
        }
        WorkingHours workingHours = getWorkingHours();
        Restaurant restaurant = new Restaurant(name, address, priceType, workingHours);
        dataBase.addARestaurant(restaurant);
        System.out.println("***Restaurant added successfully***\n");
        addFoodToMenu(restaurant);
        sort.sorting(dataBase.getSortMode());
    }

    private void showARestaurantInformation() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Show a restaurant information***\n");
        Restaurant restaurant = dataBase.chooseRestaurant();
        if (restaurant == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Information of " + restaurant + "***\n");
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

    private void showARestaurantComments() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of a restaurant***\n");
        Restaurant restaurant = dataBase.chooseRestaurant();
        if (restaurant == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of " + restaurant + "***\n");
        if(restaurant.getComments().size() == 0) {
            System.out.println("***This list is empty***\n");
        } else {
            for (Comment comment : restaurant.getComments()) {
                System.out.println(comment + "\n----");
            }
        }
    }

    private void showAllCommentsOfAFood() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Show all comments of a food***\n");
        System.out.print("Enter food name: ");
        String foodName = ScannerWrapper.getInstance().nextLine().trim();
        boolean check = false;
        for (Restaurant restaurant : dataBase.getRestaurants()) {
            for (Food food : restaurant.getMenu()) {
                if (food.getName().equals(foodName)) {
                    for (Comment comment : food.getComments()) {
                        System.out.println(restaurant + ":\n" + comment + "\n-------------");
                        check = true;
                    }
                }
            }
        }
        if(!check) {
            System.out.println("***This food doesn't have any comments or doesn't exist in restaurants menus***\n");
        }
    }

    private void changeARestaurantInformationMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change a restaurant information***\n");
        Restaurant restaurant = dataBase.chooseRestaurant();
        if (restaurant == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing information of " + restaurant + "***\n");
        System.out.println("1-Change name");
        System.out.println("2-Change address");
        System.out.println("3-Change price type");
        System.out.println("4-Change Working hours");
        System.out.println("5-Change food menu");
        System.out.println("6-Add a delivery to this restaurant");
        System.out.println("7-Remove a delivery from this restaurant");
        System.out.println("0-Back");
        int option = chooseInRange(0, 7);
        changeARestaurantInformationMenuHandler(option, restaurant);
    }

    private void changeARestaurantInformationMenuHandler(int option, Restaurant restaurant) {
        switch (option) {
            case 1:
                changeRestaurantName(restaurant);
                break;
            case 2:
                changeRestaurantAddress(restaurant);
                break;
            case 3:
                changeRestaurantPriceType(restaurant);
                break;
            case 4:
                changeRestaurantWorkingHours(restaurant);
                break;
            case 5:
                changeRestaurantFoodMenu(restaurant);
                break;
            case 6:
                addADeliveryToARestaurant(restaurant);
                break;
            case 7:
                removeADeliveryFromRestaurant(restaurant);
                break;
            default:
                return;
        }
    }

    private void changeRestaurantName(Restaurant restaurant) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + restaurant.getName() + " name***\n");
        System.out.print("Enter new name: ");
        restaurant.setName(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Name changed successfully***\n");
    }

    private void changeRestaurantAddress(Restaurant restaurant) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + restaurant.getName() + " address***\n");
        System.out.print("Enter new address: ");
        restaurant.setAddress(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Address changed successfully***\n");
    }

    private void changeRestaurantPriceType(Restaurant restaurant) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + restaurant.getName() + " price type***\n");
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

    private void changeRestaurantWorkingHours(Restaurant restaurant) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + restaurant.getName() + " working hours***\n");
        System.out.println("New working hour:\n");
        restaurant.setWorkingHours(getWorkingHours());
        System.out.println("***Working hours changed successfully***\n");
    }

    private void changeRestaurantFoodMenu(Restaurant restaurant) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + restaurant.getName() + " food menu***\n");
        System.out.println("1-Add a food");
        System.out.println("2-Remove a food");
        System.out.println("3-Change a food information");
        System.out.println("0-Back");
        int option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                addFoodToMenu(restaurant);
                break;
            case 2:
                removeAFoodFromMenu(restaurant);
                break;
            case 3:
                changeAFoodInformation(restaurant);
                break;
            default:
                return;
        }
    }

    private void addFoodToMenu(Restaurant restaurant) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Adding foods to the menu of " + restaurant + "***\n");
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

    private void removeAFoodFromMenu(Restaurant restaurant) {
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

    private void changeAFoodInformation(Restaurant restaurant) {
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

    private void addADeliveryToARestaurant(Restaurant restaurant) {
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

    private void removeADeliveryFromRestaurant(Restaurant restaurant) {
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

    private void removeARestaurant() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Remove a restaurant***\n");
        Restaurant restaurant = dataBase.chooseRestaurant();
        if (restaurant == null) {
            return;
        }
        String restaurantName = restaurant.getName();
        if (makeSure(restaurantName)) {
            dataBase.removeARestaurant(restaurant);
            System.out.println("***" + restaurantName + " successfully removed***");
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
