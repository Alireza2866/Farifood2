package ir.ac.kntu.main.adminmenu;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.main.*;
import ir.ac.kntu.store.*;
import java.time.LocalTime;
import java.util.ArrayList;


public class HandleFruitShopMenu {
    private Sort sort;

    private DataBase dataBase;

    public HandleFruitShopMenu(DataBase dataBase) {
        this.dataBase = dataBase;
        sort = new Sort(dataBase);
    }

    public void fruitShopMenu() {
        System.out.println("1-Add a new fruit shop");
        System.out.println("2-Show a fruit shop information");
        System.out.println("3-Show comments of a fruit shop");
        System.out.println("4-Change a fruit shop information");
        System.out.println("5-Remove a fruit shop");
        System.out.println("0-Back");
        int option = chooseInRange(0, 5);
        fruitShopMenuHandler(option);
    }

    private void fruitShopMenuHandler(int option) {
        switch (option) {
            case 1:
                addANewFruitShop();
                break;
            case 2:
                showAFruitShopInformation();
                break;
            case 3:
                showAFruitShopComments();
                break;
            case 4:
                changeAFruitShopInformationMenu();
                break;
            case 5:
                removeAFruitShop();
                break;
            case 0:
                return;
            default:
                break;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Fruit shop menu***\n");
        fruitShopMenu();
    }

    private void addANewFruitShop() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Add a new fruitShop***\n");
        System.out.print("Name: ");
        String name = ScannerWrapper.getInstance().nextLine();
        System.out.print("Address: ");
        String address = ScannerWrapper.getInstance().nextLine();
        WorkingHours workingHours = getWorkingHours();
        System.out.print("Purchasable fruit in each period of time: ");
        int purchasableFruitInEachPeriod = ScannerWrapper.getInstance().nextInt();
        FruitShop fruitShop = new FruitShop(name, address, workingHours, purchasableFruitInEachPeriod);
        dataBase.addAFruitShop(fruitShop);
        System.out.println("***Fruit shop added successfully***\n");
        addFruit(fruitShop);
        sort.sorting(dataBase.getSortMode());
    }

    private void showAFruitShopInformation() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Show a fruit shop information***\n");
        FruitShop fruitShop = dataBase.chooseFruitShop();
        if (fruitShop == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Information of " + fruitShop + "***\n");
        System.out.println("Name: " + fruitShop.getName());
        System.out.println("Address: " + fruitShop.getAddress());
        System.out.println("Score: " + fruitShop.getScore());
        System.out.println("Number of comments: " + fruitShop.getComments().size());
        System.out.println("Number of deliveries in each period of time: " + fruitShop.getDeliveriesNumber());
        System.out.println("Purchasable fruit in each period of time: " + fruitShop.getPurchasableFruitInEachPeriod());
        System.out.println("Working Hours: " + fruitShop.getWorkingHours());
        if(fruitShop.isOpen()) {
            System.out.println("This fruit shop is open now");
        } else {
            System.out.println("This fruit shop is closed now");
        }
    }

    private void showAFruitShopComments() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of a fruit shop***\n");
        FruitShop fruitShop = dataBase.chooseFruitShop();
        if (fruitShop == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of " + fruitShop + "***\n");
        if(fruitShop.getComments().size() == 0) {
            System.out.println("***This list is empty***\n");
        } else {
            for (Comment comment : fruitShop.getComments()) {
                System.out.println(comment + "\n----");
            }
        }
    }

    private void changeAFruitShopInformationMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change a fruit shop information***\n");
        FruitShop fruitShop = dataBase.chooseFruitShop();
        if (fruitShop == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing information of " + fruitShop + "***\n");
        System.out.println("1-Change name");
        System.out.println("2-Change address");
        System.out.println("3-Change deliveries number");
        System.out.println("4-Change purchasable fruit in each period of time");
        System.out.println("5-Change fruits list");
        System.out.println("0-Back");
        int option = chooseInRange(0, 5);
        changeAFruitShopInformationMenuHandler(option, fruitShop);
    }

    private void changeAFruitShopInformationMenuHandler(int option, FruitShop fruitShop) {
        switch (option) {
            case 1:
                changeFruitShopName(fruitShop);
                break;
            case 2:
                changeFruitShopAddress(fruitShop);
                break;
            case 3:
                changeFruitShopDeliveriesNumber(fruitShop);
                break;
            case 4:
                changeFruitShopPurchasableFruitInEachPeriod(fruitShop);
                break;
            case 5:
                changeFruitShopFruitsList(fruitShop);
                break;
            default:
                return;
        }
    }

    private void changeFruitShopName(FruitShop fruitShop) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + fruitShop.getName() + " name***\n");
        System.out.print("Enter new name: ");
        fruitShop.setName(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Name changed successfully***\n");
    }

    private void changeFruitShopAddress(FruitShop fruitShop) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + fruitShop.getName() + " address***\n");
        System.out.print("Enter new address: ");
        fruitShop.setAddress(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Address changed successfully***\n");
    }

    private void changeFruitShopDeliveriesNumber(FruitShop fruitShop) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + fruitShop.getName() + " deliveries number***\n");
        System.out.print("Enter new number of deliveries: ");
        int deliveriesNumber = ScannerWrapper.getInstance().nextInt();
        while (deliveriesNumber < 2) {
            System.out.println("***Deliveries number should be greater than 1***\n");
            deliveriesNumber = ScannerWrapper.getInstance().nextInt();
        }
        fruitShop.setDeliveriesNumber(deliveriesNumber);
        System.out.println("***Deliveries number changed successfully***\n");
    }

    private void changeFruitShopPurchasableFruitInEachPeriod(FruitShop fruitShop) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + fruitShop.getName() + " purchasable fruit in each period of time***\n");
        System.out.print("Enter new purchasable fruit in each period of time: ");
        double purchasableFruitInEachPeriod= ScannerWrapper.getInstance().nextDouble();
        while (purchasableFruitInEachPeriod < 0) {
            System.out.println("***Invalid input***\n");
            purchasableFruitInEachPeriod = ScannerWrapper.getInstance().nextDouble();
        }
        fruitShop.setPurchasableFruitInEachPeriod(purchasableFruitInEachPeriod);
        System.out.println("***purchasable fruit in each period changed successfully***\n");
    }

    private void changeFruitShopFruitsList(FruitShop fruitShop) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + fruitShop.getName() + " fruits list***\n");
        System.out.println("1-Add a fruit");
        System.out.println("2-Remove a fruit");
        System.out.println("3-Change a fruit information");
        System.out.println("0-Back");
        int option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                addFruit(fruitShop);
                break;
            case 2:
                removeAFruit(fruitShop);
                break;
            case 3:
                changeAFruitInformation(fruitShop);
                break;
            default:
                return;
        }
    }

    private void addFruit(FruitShop fruitShop) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Adding fruits to the menu of " + fruitShop + "***\n");
        int option = 1;
        while (option == 1) {
            System.out.print("Fruit name: ");
            String fruitName = ScannerWrapper.getInstance().nextLine();
            System.out.print("Fruit price: ");
            int fruitPrice = ScannerWrapper.getInstance().nextInt();
            System.out.println("Fruit amount(kg): ");
            double fruitAmount = ScannerWrapper.getInstance().nextDouble();
            Fruit fruit = new Fruit(fruitName, fruitPrice,fruitShop, fruitAmount);
            System.out.println("\n***Fruit added to the list of fruit shop successfully***\n");
            System.out.println("Do you want to add another fruit?");
            System.out.println("1-yes");
            System.out.println("2-No");
            option = chooseInRange(1, 2);
        }
    }

    private void removeAFruit(FruitShop fruitShop) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***removing a fruit***\n");
        ArrayList<Fruit> fruits = fruitShop.getFruits();
        if(fruits.size() == 0) {
            System.out.println("***this list is empty***\n");
            return;
        }
        for (int i=0; i<fruits.size(); i++) {
            System.out.println((i+1) + "-" + fruits.get(i).getName());
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, fruits.size()) - 1;
        if (option + 1 == 0) {
            return;
        }
        String name = fruits.get(option).getName();
        if(makeSure(name)) {
            fruitShop.getFruits().remove(fruits.get(option));
            System.out.println("***" + name + " successfully removed from this fruit shop menu***\n");
        }
    }

    private void changeAFruitInformation(FruitShop fruitShop) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***changing a fruit information***\n");
        ArrayList<Fruit> fruits = fruitShop.getFruits();
        for (int i=0; i<fruits.size(); i++) {
            System.out.println((i+1) + "-" + fruits.get(i).getName());
        }
        System.out.println("0-Back");
        if(fruits.size() == 0) {
            System.out.println("***This lis is empty***\n");
            return;
        }
        int option = chooseInRange(0, fruits.size()) - 1;
        if (option + 1 == 0) {
            return;
        }
        Fruit fruit = fruits.get(option);
        System.out.println("\n--------------------------------------------");
        System.out.println("***changing information of " + fruit.getName() + "***\n");
        System.out.println("1-Change name");
        System.out.println("2-Change price");
        System.out.println("3-Change amount");
        System.out.println("0-Back");
        option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                changeFruitName(fruit);
                break;
            case 2:
                changeFruitPrice(fruit);
                break;
            case 3:
                changeFruitAmount(fruit);
                break;
            default:
                return;
        }
    }

    private void changeFruitName(Fruit fruit) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing " + fruit.getName() + " name***\n");
        System.out.print("Enter new name: ");
        fruit.setName(ScannerWrapper.getInstance().nextLine());
        System.out.println("***Fruit name changed successfully***\n");
    }

    private void changeFruitPrice(Fruit fruit) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing " + fruit.getName() + " price***\n");
        System.out.print("Enter new price: ");
        fruit.setPrice(ScannerWrapper.getInstance().nextInt());
        System.out.println("***Fruit price changed successfully***\n");
    }

    private void changeFruitAmount(Fruit fruit) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing " + fruit.getName() + " amount***\n");
        System.out.print("Enter new amount: ");
        double amount = ScannerWrapper.getInstance().nextDouble();
        while (amount < 0.1) {
            System.out.println("**Invalid input***\n");
            System.out.print("->");
            amount = ScannerWrapper.getInstance().nextDouble();
        }
        fruit.setWeight(amount);
        System.out.println("***Fruit amount changed successfully***\n");
    }

    private void removeAFruitShop() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Remove a fruit shop***\n");
        FruitShop fruitShop = dataBase.chooseFruitShop();
        if (fruitShop == null) {
            return;
        }
        String fruitShopName = fruitShop.getName();
        if (makeSure(fruitShopName)) {
            dataBase.removeAFruitShop(fruitShop);
            System.out.println("***" + fruitShopName + " successfully removed***");
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
        while (!stringStartTime.matches("^([0-1][0-9]|[2][0-4]):00$")) {
            System.out.print("format of input is wrong, try again.(correct format: XX:00) : ");
            stringStartTime = ScannerWrapper.getInstance().nextLine().replaceAll("\\s", "");
        }
        System.out.print("Time of end of working:");
        String stringCloseTime = ScannerWrapper.getInstance().nextLine().replaceAll("\\s", "");
        while (!stringCloseTime.matches("^([0-1][0-9]|[2][0-4]):00$")) {
            System.out.print("format of input is wrong, try again.(correct format: XX:00) : ");
            stringCloseTime = ScannerWrapper.getInstance().nextLine().replaceAll("\\s", "");
        }
        int hour = Integer.parseInt(stringStartTime.substring(0, 2));
        int minute = Integer.parseInt(stringStartTime.substring(3, 5));
        LocalTime startTime = LocalTime.of(hour, minute);
        hour = Integer.parseInt(stringCloseTime.substring(0, 2));
        minute = Integer.parseInt(stringCloseTime.substring(3, 5));
        LocalTime closeTime = LocalTime.of(hour, minute);
        if (closeTime.getHour() - startTime.getHour() % 2 != 0) {
            System.out.println("***The difference between the start hour and close hour must be a multiple of 2. Try again***\n");
            return getWorkingHours();
        }
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
