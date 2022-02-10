package ir.ac.kntu.main.customermenu;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.comment.Commentable;
import ir.ac.kntu.main.Sort;
import ir.ac.kntu.order.OrderStatus;
import ir.ac.kntu.store.*;
import ir.ac.kntu.user.Customer;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.main.DataBase;
import ir.ac.kntu.main.ScannerWrapper;

import java.util.ArrayList;

public class CustomerMenu {
    private Sort sort;

    private DataBase dataBase;

    private Customer customer;

    public CustomerMenu(DataBase dataBase, Customer customer) {
        this.dataBase = dataBase;
        this.customer = customer;
        sort = new Sort(dataBase);
    }

    private void getComments() {
        for (Order order : customer.getOrderHistory()) {
            if (order.getOrderStatus() == OrderStatus.DELIVERED && !order.isCommented()) {
                System.out.println("This order is delivered. Do you wanna leave comment for that? \n" + order);
                System.out.println("1-yes\n2-No");
                int option = chooseInRange(1, 2);
                if (option == 2) {
                    order.setCommented(true);
                    continue;
                }
                if (order.getStore() instanceof Restaurant) {
                    getComment(order.getStore());
                    getComment(order.getDelivery());
                    for (Stuff stuff : order.getStuffs()) {
                        getComment((Food) stuff);
                    }
                } else {
                    getComment(order.getStore());
                }
                order.setCommented(true);
                sort.sorting(dataBase.getSortMode());
            }
        }
    }

    private void getComment(Commentable commentable) {
        System.out.println("Score for " + commentable.getName());
        int score = chooseInRange(1, 5);
        System.out.println("Comment for " + commentable);
        String commentText = ScannerWrapper.getInstance().nextLine();
        Comment comment = new Comment(score, commentText, customer, commentable);
        commentable.addAComment(comment);
        customer.addAComment(comment);
    }

    public void customerMenu() {
        getComments();
        System.out.println("1-Confirm a new order");
        System.out.println("2-Show customer information");
        System.out.println("3-Show orders of customer");
        System.out.println("4-Show comments of customer");
        System.out.println("5-Change customer information");
        System.out.println("6-Buy a restaurant premium subscription");
        System.out.println("0-Log out");
        int option = chooseInRange(0, 6);
        customerMenuHandler(option);
    }

    private void customerMenuHandler(int option) {
        switch (option) {
            case 1:
                confirmANewOrder();
                break;
            case 2:
                showCustomerInformation();
                break;
            case 3:
                showOrdersOfCustomer();
                break;
            case 4:
                showCommentsOfCustomer();
                break;
            case 5:
                changeCustomerInformation();
                break;
            case 6:
                buyASuperMarketPremiumSubscription();
            case 0:
                return;
            default:
                break;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Customer menu***\n");
        customerMenu();
    }

    private void confirmANewOrder() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Confirm a new order***\n");
        Order order = new Order(customer);
        System.out.println("Order from: ");
        System.out.println("1-Restaurant");
        System.out.println("2-Super market");
        System.out.println("3-Fruit shop");
        int option = chooseInRange(1, 3);
        boolean check = true;
        WorkingHours period = new WorkingHours();
        switch (option) {
            case 1:
                check = orderFromARestaurant(order);
                break;
            case 2:
                period = orderFromASuperMarket(order);
                break;
            case 3:
                period = orderFromAFruitShop(order);
                break;
            default:
                break;
        }
        if (!check || period == null) {
            return;
        }
        System.out.println("Delivery address:");
        String address ="";
        System.out.println("1-Select customer address");
        System.out.println("2-Input another address");
        option = chooseInRange(1, 2);
        if (option == 1) {
            address = customer.getAddress();
        }else {
            System.out.println("Input the address: ");
            address = ScannerWrapper.getInstance().nextLine();
        }
        customer.addAnOrder(order);
        dataBase.addAProcessingOrder(order);
        if (order.getStore() instanceof FruitShop) {
            dataBase.addAPurchasedPeriodFromAFruitShopToACustomer(customer,(FruitShop) order.getStore(), period);
        }
        if (order.getStore() instanceof PeriodicTimeStore) {
            PeriodicTimeStore periodicTimeStore = (PeriodicTimeStore) order.getStore();
            periodicTimeStore.addAnOrderToAPeriod(period);
        }
        System.out.println("***Order confirmed successfully***\n");
    }

    private boolean orderFromARestaurant(Order order) {
        System.out.println("***Select the restaurant***");
        Restaurant restaurant = filterRestaurantsList();
        if (restaurant == null) {
            return false;
        }
        order.setStore(restaurant);
        int option = 1;
        while (option == 1) {
            System.out.println("***Select the food***");
            for (int i = 0; i < restaurant.getMenu().size(); i++) {
                System.out.print((i + 1) + "-" + restaurant.getMenu().get(i));
            }
            System.out.println("0-Back");
            option = chooseInRange(0, restaurant.getMenu().size()) - 1;
            if (option + 1 == 0) {
                return false;
            }
            Food food = restaurant.getMenu().get(option);
            System.out.print("Amount of food: ");
            int foodNumber = chooseInRange(1, 100);
            order.addAStuff(food, foodNumber);
            System.out.println("Do you want anything else?");
            System.out.println("1-yes\n2-No");
            option = chooseInRange(1, 2);
        }
        return true;
    }

    private Restaurant filterRestaurantsList() {
        System.out.println("Do filter restaurants list based on price type?");
        System.out.println("1-Yes");
        System.out.println("2-No");
        int option = chooseInRange(1, 2);
        if (option == 2) {
            return chooseAnOpenRestaurant(dataBase.getRestaurants());
        }
        System.out.println("choose price type");
        System.out.println("1-Luxury");
        System.out.println("2-Intermediate");
        System.out.println("3-Economic");
        ArrayList<Restaurant> filteredRestaurants = new ArrayList<>();
        option = chooseInRange(1, 3);
        switch (option) {
            case 1:
                System.out.println("\n***Luxury restaurants***\n");
                for (Restaurant restaurant : dataBase.getRestaurants()) {
                    if (restaurant.getPriceType() == PriceType.LUXURY) {
                        filteredRestaurants.add(restaurant);
                    }
                }
                break;
            case 2:
                System.out.println("\n***Intermediate restaurants***\n");
                for (Restaurant restaurant : dataBase.getRestaurants()) {
                    if (restaurant.getPriceType() == PriceType.INTERMEDIATE) {
                        filteredRestaurants.add(restaurant);
                    }
                }
                break;
            case 3:
                System.out.println("\n***Economic restaurants***\n");
                for (Restaurant restaurant : dataBase.getRestaurants()) {
                    if (restaurant.getPriceType() == PriceType.ECONOMIC) {
                        filteredRestaurants.add(restaurant);
                    }
                }
                break;
            default:
                break;
        }
        return chooseAnOpenRestaurant(filteredRestaurants);
    }

    private Restaurant chooseAnOpenRestaurant(ArrayList<Restaurant> restaurants) {
        ArrayList<Restaurant> openRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.isOpen()) {
                openRestaurants.add(restaurant);
            }
        }
        if (openRestaurants.size() == 0) {
            System.out.println("***This list is empty***\n");
            return null;
        }
        for (int i=0; i< openRestaurants.size(); i++) {
            System.out.println((i+1) + "-" + openRestaurants.get(i) + "    Score: " + openRestaurants.get(i).getScore() +
                    "    Comments number: " + openRestaurants.get(i).getComments().size());
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, openRestaurants.size()) - 1;
        if (option + 1 == 0) {
            return null;
        }
        return openRestaurants.get(option);
    }

    private WorkingHours orderFromASuperMarket(Order order) {
        System.out.println("***Select the super market***");
        SuperMarket superMarket = dataBase.chooseSuperMarket();
        if (superMarket == null) {
            return null;
        }
        order.setStore(superMarket);
        int option = 1;
        while (option == 1) {
            for (int i = 0; i < superMarket.getRemainedStuffs().size(); i++) {
                Stuff stuff = superMarket.getRemainedStuffs().get(i);
                System.out.print((i + 1) + "-" + stuff + " | remained : " + superMarket.getAStuffNumber(stuff));
            }
            System.out.println("0-Back");
            System.out.println("***\nOut of stock stuffs***\n");
            for (Stuff stuff : superMarket.outOfStock()) {
                System.out.println(stuff.getName());
            }
            option = chooseInRange(0, superMarket.getRemainedStuffs().size()) - 1;
            if (option + 1 == 0) {
                return null;
            }
            Stuff stuff = superMarket.getRemainedStuffs().get(option);
            System.out.print("Amount of stuff: ");
            int stuffNumber = chooseInRange(1, superMarket.getAStuffNumber(stuff));
            superMarket.changeAStuffNumber(stuff, superMarket.getAStuffNumber(stuff) - stuffNumber);
            order.addAStuff(stuff, stuffNumber);
            System.out.println("Do you want anything else?");
            System.out.println("1-yes\n2-No");
            option = chooseInRange(1, 2);
        }
        return chooseAPeriodForOrder(superMarket);
    }

    private WorkingHours orderFromAFruitShop(Order order) {
        System.out.println("***Select the fruit shop***");
        FruitShop fruitShop = dataBase.chooseFruitShop();
        if (fruitShop == null) {
            return null;
        }
        order.setStore(fruitShop);
        int option = 1;
        double purchasableWeight = fruitShop.getPurchasableFruitInEachPeriod();
        while (option == 1 && purchasableWeight > 0.1) {
            for (int i = 0; i < fruitShop.getRemainedFruits().size(); i++) {
                Fruit fruit = fruitShop.getRemainedFruits().get(i);
                System.out.print((i + 1) + "-" + fruit + " | remained : " + fruit.getWeight() + "kg");
            }
            System.out.println("0-Back");
            System.out.println("***\nOut of stock fruits***\n");
            for (Fruit fruit : fruitShop.outOfStock()) {
                System.out.println(fruit.getName());
            }
            option = chooseInRange(0, fruitShop.getRemainedFruits().size()) - 1;
            if (option + 1 == 0) {
                return null;
            }
            Fruit fruit = fruitShop.getRemainedFruits().get(option);
            System.out.println("Amount of fruit: ");
            double amount = chooseFruitAmount(fruit.getWeight(), purchasableWeight);
            fruit.setWeight(fruit.getWeight() - amount);
            order.addAStuff(fruit, amount);
            purchasableWeight =- amount;
            if (purchasableWeight > 0.1) {
                System.out.println("Do you want anything else?");
                System.out.println("1-yes\n2-No");
                option = chooseInRange(1, 2);
            }
        }
        return chooseAPeriodForOrder(fruitShop);
    }

    private double chooseFruitAmount( double remainedFruitWeight, double purchasableWeight) {
        System.out.println("(You can only purchase " + purchasableWeight + "kg now)");
        System.out.print("->");
        double option = ScannerWrapper.getInstance().nextDouble();
        double weight = (remainedFruitWeight < purchasableWeight) ? remainedFruitWeight : purchasableWeight;
        while (!(0.1 <= option && option <= weight)) {
            System.out.println("***Invalid input***\n");
            System.out.print("->");
            option = ScannerWrapper.getInstance().nextDouble();
        }
        return option;
    }

    private WorkingHours chooseAPeriodForOrder(PeriodicTimeStore periodicTimeStore) {
        if (periodicTimeStore.availablePeriodsToOrder().size() == 0) {
            System.out.println("***This store delivery list is full for today***\n");
            return null;
        }
        System.out.println("***Choose a time for order delivery***\n");
        for (int i = 0; i < periodicTimeStore.availablePeriodsToOrder().size(); i++) {
            System.out.print((i + 1) + " -" + periodicTimeStore.availablePeriodsToOrder().get(i));
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, periodicTimeStore.availablePeriodsToOrder().size()) - 1;
        if (option + 1 == 0) {
            return null;
        }
        WorkingHours period = periodicTimeStore.availablePeriodsToOrder().get(option);
        if (periodicTimeStore instanceof FruitShop) {
            FruitShop fruitShop = (FruitShop) periodicTimeStore;
            if(dataBase.canCustomerPurchaseFromAFruitShopAtAPeriod(customer, fruitShop, period)) {
                System.out.println("***You have purchased from this shop At this period previously.");
                return null;
            }
        }
        return period;
    }

    private void showCustomerInformation() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Show customer information***\n");
        System.out.println("User name: " + customer.getUserName());
        System.out.println("Phone number: " + customer.getPhoneNumber());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Number of orders: " + customer.getOrderHistory().size());
        System.out.println("Number of comments: " + customer.getComments().size());
    }

    private void showOrdersOfCustomer() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing orders of customer***\n");
        if (customer.getOrderHistory().size() == 0) {
            System.out.println("***This list is empty***\n");
        } else {
            for (Order order : customer.getOrderHistory()) {
                System.out.println(order + "\n----");
            }
        }
    }

    private void showCommentsOfCustomer() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of customer***\n");
        if (customer.getComments().size() == 0) {
            System.out.println("***This list is empty***\n");
        } else {
            for (Comment comment : customer.getComments()) {
                System.out.println("Comment on: " + comment.getCommentable().getName() + "\n" + comment + "\n----");
            }
        }
    }

    private void changeCustomerInformation() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change customer information***\n");
        System.out.println("1-Change user name");
        System.out.println("2-Change password");
        System.out.println("3-Change phone number");
        System.out.println("4-Change address");
        System.out.println("0-Back");
        int option = chooseInRange(0, 4);
        switch (option) {
            case 1:
                changeCustomerUserName();
                break;
            case 2:
                changeCustomerPassword();
                break;
            case 3:
                changeCustomerPhoneNumber();
                break;
            case 4:
                changeCustomerAddress();
                break;
            default:
                return;
        }
    }

    private void changeCustomerUserName() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change customer user name***\n");
        System.out.print("Enter new user name: ");
        String newUserName = ScannerWrapper.getInstance().nextLine().trim();
        customer.setUserName(newUserName);
        System.out.println("***User name changed successfully***\n");
    }

    private void changeCustomerPassword() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change customer password***\n");
        System.out.print("Enter new password: ");
        String newPassword = ScannerWrapper.getInstance().nextLine().trim();
        while (newPassword.length() < 3) {
            System.out.println("Password is too short. Try again: ");
            newPassword = ScannerWrapper.getInstance().nextLine().trim();
        }
        customer.setPassword(newPassword);
        System.out.println("***Password changed successfully***\n");
    }

    private void changeCustomerPhoneNumber() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change customer phone number***\n");
        System.out.print("Enter new phone number: ");
        String newPhoneNumber = ScannerWrapper.getInstance().nextLine().trim();
        while (!newPhoneNumber.matches("^09[0-9]{9}$")) {
            System.out.println("Format of input is wrong.(Right format: 09XXXXXXXXX)");
            System.out.print("Try again: ");
            newPhoneNumber = ScannerWrapper.getInstance().nextLine().trim();
        }
        customer.setPhoneNumber(newPhoneNumber);
        System.out.println("***Phone number changed successfully***\n");
    }

    private void changeCustomerAddress() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change customer address***\n");
        System.out.print("Enter new address: ");
        String newAddress = ScannerWrapper.getInstance().nextLine().trim();
        customer.setAddress(newAddress);
        System.out.println("***Address changed successfully***\n");
    }

    private void buyASuperMarketPremiumSubscription() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Buy a super market premium subscription***\n");
        System.out.println("***Select the super market***");
        for (int i = 0; i < dataBase.getSuperMarkets().size(); i++) {
            SuperMarket superMarket = dataBase.getSuperMarkets().get(i);
            System.out.println((i+1) + "-" + superMarket + " | premium subscription price: " + superMarket.getPremiumSubscriptionPrice());
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, dataBase.getSuperMarkets().size()) - 1;
        if (option + 1 == 0) {
            return;
        }
        SuperMarket superMarket = dataBase.getSuperMarkets().get(option);
        if (customer.havePremiumSubscription(superMarket)) {
            System.out.println("***You have this super market premium subscription***\n");
            return;
        }
        customer.addAPremiumSubscription(superMarket);
        System.out.println("***Premium subscription purchased successfully***\n");
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
}
