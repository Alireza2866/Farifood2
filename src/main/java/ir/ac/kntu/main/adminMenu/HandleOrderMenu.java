package ir.ac.kntu.main.adminmenu;

import ir.ac.kntu.user.Customer;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.store.*;
import ir.ac.kntu.order.*;
import ir.ac.kntu.main.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class HandleOrderMenu {
    private DataBase dataBase;

    public HandleOrderMenu(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void orderMenu() {
        System.out.println("1-Confirm a new order");
        System.out.println("2-Show 5 best restaurant for a food");
        System.out.println("3-Show orders based on their status");
        System.out.println("4-Change an order status");
        System.out.println("0-Back");
        int option = chooseInRange(0, 4);
        orderMenuHandler(option);
    }

    private void orderMenuHandler(int option) {
        switch (option) {
            case 1:
                confirmANewOrder();
                break;
            case 2:
                show5BestRestaurantForAFood();
                break;
            case 3:
                showOrdersBasedOnTheirStatus();
                break;
            case 4:
                changeAnOrderStatus();
                break;
            case 0:
                return;
            default:
                break;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Order menu***\n");
        orderMenu();
    }

    private void confirmANewOrder() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Confirm a new order***\n\n***Select the customer***");
        Customer customer = dataBase.chooseCustomer();
        if(customer == null) {
            return;
        }
        Order order = new Order(customer);
        System.out.println("Order from:\n1-Restaurant\n2-Super market\n3-Fruit shop");
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
        order.setDestination(getAddressFromUser(customer));
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

    private String getAddressFromUser(Customer customer) {
        System.out.println("Delivery address:");
        String address ="";
        System.out.println("1-Select this customer address");
        System.out.println("2-Input another address");
        int option = chooseInRange(1, 2);
        if (option == 1) {
            return customer.getAddress();
        }
        System.out.println("Input the address: ");
        return ScannerWrapper.getInstance().nextLine();
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
        return chooseAPeriodForOrder(superMarket, order.getCustomer());
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
        return chooseAPeriodForOrder(fruitShop, order.getCustomer());
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

    private WorkingHours chooseAPeriodForOrder(PeriodicTimeStore periodicTimeStore, Customer customer) {
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

    private void show5BestRestaurantForAFood() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Show 5 best restaurants for a food***\n");
        System.out.print("Enter food name: ");
        String foodName = ScannerWrapper.getInstance().nextLine().trim();
        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        for (Restaurant restaurant : dataBase.getRestaurants()) {
            for (Food food : restaurant.getMenu()) {
                if (food.getName().equals(foodName)) {
                    foods.add(food);
                    restaurants.add(restaurant);
                    break;
                }
            }
        }
        ArrayList<Restaurant> bestRestaurants = new ArrayList<>();
        ArrayList<Double> scores = new ArrayList<>();
        Food maxScore = foods.get(0);
        int numberOfFoods = foods.size();
        for (int i = 0; i < numberOfFoods; i++) {
            for (Food food : foods) {
                if (food.getScore() > maxScore.getScore()) {
                    maxScore = food;
                    break;
                }
            }
            Restaurant restaurant = restaurants.get(foods.indexOf(maxScore));
            bestRestaurants.add(restaurant);
            scores.add(maxScore.getScore());
            foods.remove(maxScore);
            restaurants.remove(restaurant);
        }
        int restaurantsNumber = bestRestaurants.size();
        if (restaurantsNumber > 5) {
            restaurantsNumber = 5;
        }
        for (int i=0; i<restaurantsNumber; i++) {
            System.out.println((i+1) + "-" + bestRestaurants.get(i) + ";  score of this food: " + scores.get(i));
        }
        if (restaurantsNumber < 5) {
            System.out.println("***only " + restaurantsNumber + " restaurant(s) have this food***\n");
        }
    }

    private void showOrdersBasedOnTheirStatus() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Show orders based on their status***\n");
        System.out.println("1-Processing orders");
        System.out.println("2-Delivering orders");
        System.out.println("3-Delivered Orders");
        System.out.println("0-Back");
        int option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                showProcessingOrders();
                break;
            case 2:
                showDeliveringOrders();
                break;
            case 3:
                showDeliveredOrders();
                break;
            default:
                return;
        }
    }

    private void showProcessingOrders() {
        if (dataBase.getProcessingOrders().size() == 0) {
            System.out.println("***This list is empty***\n");
            return;
        }
        for (int i = 0; i < dataBase.getProcessingOrders().size(); i++) {
            System.out.println((i + 1) + "-" + dataBase.getProcessingOrders().get(i));
        }
    }

    private void showDeliveringOrders() {
        if (dataBase.getDeliveringOrders().size() == 0) {
            System.out.println("***This list is empty***\n");
            return;
        }
        for (int i=0; i<dataBase.getDeliveringOrders().size(); i++) {
            System.out.println((i+1) + "-" + dataBase.getDeliveringOrders().get(i));
        }
    }

    private void showDeliveredOrders() {
        if (dataBase.getDeliveredOrders().size() == 0) {
            System.out.println("***This list is empty***\n");
            return;
        }
        for (int i=0; i<dataBase.getDeliveredOrders().size(); i++) {
            System.out.println((i+1) + "-" + dataBase.getDeliveredOrders().get(i));
        }
    }

    private void changeAnOrderStatus() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change an order status***\n");
        System.out.println("1-Change status of processing orders to delivering");
        System.out.println("2-Change status of Delivering orders to delivered");
        System.out.println("0-Back");
        int option = chooseInRange(0, 2);
        switch (option) {
            case 1:
                changeProcessingToDelivering();
                break;
            case 2:
                changeDeliveringToDelivered();
                break;
            default:
                return;
        }
    }

    private void changeProcessingToDelivering() {
        if (dataBase.getProcessingOrders().size() == 0) {
            System.out.println("***There is no order in processing status***\n");
            return;
        }
        for (int i=0; i<dataBase.getProcessingOrders().size(); i++) {
            System.out.println((i+1) + "-" + dataBase.getProcessingOrders().get(i));
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, dataBase.getProcessingOrders().size()) - 1;
        if (option + 1 == 0) {
            return;
        }
        Order order = dataBase.getProcessingOrders().get(option);
        if (order.getStore() instanceof Restaurant) {
            Restaurant restaurant = (Restaurant) order.getStore();
            boolean isDeliveryAvailable = false;
            for (Delivery delivery : restaurant.getDeliveries()) {
                if (delivery.isAvailableNow()) {
                    order.setDelivery(delivery);
                    delivery.setDelivering(true);
                    isDeliveryAvailable = true;
                    break;
                }
            }
            if (!isDeliveryAvailable) {
                System.out.println("***There is no available delivery for this order***\n");
                return;
            }
        }
        order.setOrderStatus(OrderStatus.DELIVERING);
        dataBase.removeAProcessingOrder(order);
        dataBase.addADeliveringOrder(order);
        System.out.println("***Delivery status successfully changed***\n");
    }

    private void changeDeliveringToDelivered() {
        if (dataBase.getDeliveringOrders().size() == 0) {
            System.out.println("***There is no order in delivering status***\n");
            return;
        }
        for (int i=0; i<dataBase.getDeliveringOrders().size(); i++) {
            System.out.println((i+1) + "-" + dataBase.getDeliveringOrders().get(i));
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, dataBase.getDeliveringOrders().size()) - 1;
        if (option + 1 == 0) {
            return;
        }
        Order order = dataBase.getDeliveringOrders().get(option);
        order.setDeliveryTime(LocalTime.now());
        order.setOrderStatus(OrderStatus.DELIVERED);
        if (order.getStore() instanceof Restaurant) {
            order.getDelivery().setDelivering(false);
            order.getDelivery().addAnOrder(order);
        }
        dataBase.removeADeliveringOrder(order);
        dataBase.addADeliveredOrder(order);
        System.out.println("***Delivery status successfully changed***\n");
    }

    private int chooseInRange(int from, int to) {
        System.out.print("->");
        int option = ScannerWrapper.getInstance().nextInt();
        while (!(from <= option && option <= to)) {
            System.out.print("***Invalid input***\n\n->");
            option = ScannerWrapper.getInstance().nextInt();
        }
        return option;
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
}