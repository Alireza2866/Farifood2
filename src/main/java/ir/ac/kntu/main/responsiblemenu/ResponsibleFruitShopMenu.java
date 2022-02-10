package ir.ac.kntu.main.responsiblemenu;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.main.*;
import ir.ac.kntu.store.*;
import java.time.LocalTime;
import java.util.ArrayList;


public class ResponsibleFruitShopMenu {
    private DataBase dataBase;

    private FruitShop fruitShop;

    public ResponsibleFruitShopMenu(DataBase dataBase, FruitShop fruitShop) {
        this.dataBase = dataBase;
        this.fruitShop = fruitShop;
    }

    public void fruitShopMenu() {
        System.out.println("1-Show a fruit shop information");
        System.out.println("2-Show comments of a fruit shop");
        System.out.println("3-Change a fruit shop information");
        System.out.println("0-Log out");
        int option = chooseInRange(0, 3);
        fruitShopMenuHandler(option);
    }

    private void fruitShopMenuHandler(int option) {
        switch (option) {
            case 1:
                showFruitShopInformation();
                break;
            case 2:
                showFruitShopComments();
                break;
            case 3:
                changeFruitShopInformationMenu();
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

    private void showFruitShopInformation() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Show fruit shop information***\n");
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

    private void showFruitShopComments() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of fruit shop***\n");
        if(fruitShop.getComments().size() == 0) {
            System.out.println("***This list is empty***\n");
        } else {
            for (Comment comment : fruitShop.getComments()) {
                System.out.println(comment + "\n----");
            }
        }
    }

    private void changeFruitShopInformationMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change a fruit shop information***\n");
        System.out.println("1-Change name");
        System.out.println("2-Change address");
        System.out.println("3-Change deliveries number");
        System.out.println("4-Change purchasable fruit in each period of time");
        System.out.println("5-Change fruits list");
        System.out.println("0-Back");
        int option = chooseInRange(0, 5);
        changeAFruitShopInformationMenuHandler(option);
    }

    private void changeAFruitShopInformationMenuHandler(int option) {
        switch (option) {
            case 1:
                changeFruitShopName();
                break;
            case 2:
                changeFruitShopAddress();
                break;
            case 3:
                changeFruitShopDeliveriesNumber();
                break;
            case 4:
                changeFruitShopPurchasableFruitInEachPeriod();
                break;
            case 5:
                changeFruitShopFruitsList();
                break;
            default:
                return;
        }
    }

    private void changeFruitShopName() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change fruit shop name***\n");
        System.out.print("Enter new name: ");
        fruitShop.setName(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Name changed successfully***\n");
    }

    private void changeFruitShopAddress() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change fruit shop address***\n");
        System.out.print("Enter new address: ");
        fruitShop.setAddress(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Address changed successfully***\n");
    }

    private void changeFruitShopDeliveriesNumber() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change fruit shop deliveries number***\n");
        System.out.print("Enter new number of deliveries: ");
        int deliveriesNumber = ScannerWrapper.getInstance().nextInt();
        while (deliveriesNumber < 2) {
            System.out.println("***Deliveries number should be greater than 1***\n");
            deliveriesNumber = ScannerWrapper.getInstance().nextInt();
        }
        fruitShop.setDeliveriesNumber(deliveriesNumber);
        System.out.println("***Deliveries number changed successfully***\n");
    }

    private void changeFruitShopPurchasableFruitInEachPeriod() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change fruit shop purchasable fruit in each period of time***\n");
        System.out.print("Enter new purchasable fruit in each period of time: ");
        double purchasableFruitInEachPeriod= ScannerWrapper.getInstance().nextDouble();
        while (purchasableFruitInEachPeriod < 0) {
            System.out.println("***Invalid input***\n");
            purchasableFruitInEachPeriod = ScannerWrapper.getInstance().nextDouble();
        }
        fruitShop.setPurchasableFruitInEachPeriod(purchasableFruitInEachPeriod);
        System.out.println("***purchasable fruit in each period changed successfully***\n");
    }

    private void changeFruitShopFruitsList() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change fruit shop fruits list***\n");
        System.out.println("1-Add a fruit");
        System.out.println("2-Remove a fruit");
        System.out.println("3-Change a fruit information");
        System.out.println("0-Back");
        int option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                addFruit();
                break;
            case 2:
                removeAFruit();
                break;
            case 3:
                changeAFruitInformation();
                break;
            default:
                return;
        }
    }

    private void addFruit() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Adding fruits to the menu of fruit shop***\n");
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

    private void removeAFruit() {
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

    private void changeAFruitInformation() {
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
