package ir.ac.kntu.main.responsiblemenu;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.main.*;
import ir.ac.kntu.store.*;
import java.time.LocalTime;
import java.util.ArrayList;


public class  ResponsibleSuperMarketMenu {
    private DataBase dataBase;

    private SuperMarket superMarket;

    public  ResponsibleSuperMarketMenu(DataBase dataBase, SuperMarket superMarket) {
        this.dataBase = dataBase;
        this.superMarket = superMarket;
    }

    public void superMarketMenu() {
        System.out.println("1-Show super market information");
        System.out.println("2-Show comments of super market");
        System.out.println("3-Change super market information");
        System.out.println("0-Log out");
        int option = chooseInRange(0, 3);
        superMarketMenuHandler(option);
    }

    private void superMarketMenuHandler(int option) {
        switch (option) {
            case 1:
                showSuperMarketInformation();
                break;
            case 2:
                showSuperMarketComments();
                break;
            case 3:
                changeSuperMarketInformationMenu();
                break;
            case 0:
                return;
            default:
                break;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Super market menu***\n");
        superMarketMenu();
    }

    private void showSuperMarketInformation() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Show super market information***\n");
        System.out.println("Name: " + superMarket.getName());
        System.out.println("Address: " + superMarket.getAddress());
        System.out.println("Score: " + superMarket.getScore());
        System.out.println("Number of comments: " + superMarket.getComments().size());
        System.out.println("Number of deliveries in each period of time: " + superMarket.getDeliveriesNumber());
        System.out.println("Delivery price: " + superMarket.getDeliveryPrice());
        System.out.println("Premium subscription price: " + superMarket.getPremiumSubscriptionPrice());
        System.out.println("Working Hours: " + superMarket.getWorkingHours());
        if(superMarket.isOpen()) {
            System.out.println("This super market is open now");
        } else {
            System.out.println("This super market is closed now");
        }
    }

    private void showSuperMarketComments() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of super market***\n");
        if(superMarket.getComments().size() == 0) {
            System.out.println("***This list is empty***\n");
        } else {
            for (Comment comment : superMarket.getComments()) {
                System.out.println(comment + "\n----");
            }
        }
    }

    private void changeSuperMarketInformationMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change a super market information***\n");
        System.out.println("1-Change name");
        System.out.println("2-Change address");
        System.out.println("3-Change deliveries number");
        System.out.println("4-Change delivery price");
        System.out.println("5-Change premium subscription price");
        System.out.println("6-Change stuffs list");
        System.out.println("0-Back");
        int option = chooseInRange(0, 6);
        changeSuperMarketInformationMenuHandler(option);
    }

    private void changeSuperMarketInformationMenuHandler(int option) {
        switch (option) {
            case 1:
                changeSuperMarketName();
                break;
            case 2:
                changeSuperMarketAddress();
                break;
            case 3:
                changeSuperMarketDeliveriesNumber();
                break;
            case 4:
                changeSuperMarketDeliveryPrice();
                break;
            case 5:
                changeSuperMarketPremiumSubscriptionPrice();
                break;
            case 6:
                changeSuperMarketStuffsList();
                break;
            default:
                return;
        }
    }

    private void changeSuperMarketName() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change super market name***\n");
        System.out.print("Enter new name: ");
        superMarket.setName(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Name changed successfully***\n");
    }

    private void changeSuperMarketAddress() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change super market address***\n");
        System.out.print("Enter new address: ");
        superMarket.setAddress(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Address changed successfully***\n");
    }

    private void changeSuperMarketDeliveriesNumber() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change super market deliveries number***\n");
        System.out.print("Enter new number of deliveries: ");
        int deliveriesNumber = ScannerWrapper.getInstance().nextInt();
        while (deliveriesNumber < 2) {
            System.out.println("***Deliveries number should be greater than 1***\n");
            deliveriesNumber = ScannerWrapper.getInstance().nextInt();
        }
        superMarket.setDeliveriesNumber(deliveriesNumber);
        System.out.println("***Deliveries number changed successfully***\n");
    }

    private void changeSuperMarketDeliveryPrice() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change super market delivery price***\n");
        System.out.print("Enter new delivery price: ");
        int deliveryPrice = ScannerWrapper.getInstance().nextInt();
        while (deliveryPrice <= 0) {
            System.out.println("***Invalid input***\n");
            deliveryPrice = ScannerWrapper.getInstance().nextInt();
        }
        superMarket.setDeliveryPrice(deliveryPrice);
        System.out.println("***Delivery price changed successfully***\n");
    }

    private void changeSuperMarketPremiumSubscriptionPrice() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change super market premium subscription price***\n");
        System.out.print("Enter new premium subscription price: ");
        int premiumSubscriptionPrice = ScannerWrapper.getInstance().nextInt();
        while (premiumSubscriptionPrice <= 0) {
            System.out.println("***Invalid input***\n");
            premiumSubscriptionPrice = ScannerWrapper.getInstance().nextInt();
        }
        superMarket.setPremiumSubscriptionPrice(premiumSubscriptionPrice);
        System.out.println("***Premium subscription price changed successfully***\n");
    }

    private void changeSuperMarketStuffsList() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change super market stuffs list***\n");
        System.out.println("1-Add a stuff");
        System.out.println("2-Remove a stuff");
        System.out.println("3-Change a stuff information");
        System.out.println("0-Back");
        int option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                addAStuff();
                break;
            case 2:
                removeAStuff();
                break;
            case 3:
                changeAStuffInformation();
                break;
            default:
                return;
        }
    }

    private void addAStuff() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Adding stuffs to the menu of super market***\n");
        int option = 1;
        while (option == 1) {
            System.out.print("Stuff name: ");
            String stuffName = ScannerWrapper.getInstance().nextLine();
            System.out.print("Stuff price: ");
            int stuffPrice = ScannerWrapper.getInstance().nextInt();
            Stuff stuff = new Stuff(stuffName, stuffPrice, superMarket);
            System.out.println("Stuff number: ");
            int stuffNumber = ScannerWrapper.getInstance().nextInt();
            superMarket.addAStuff(stuff, stuffNumber);
            System.out.println("\n***Stuff added to the list of super market successfully***\n");
            System.out.println("Do you want to add another stuff?");
            System.out.println("1-yes");
            System.out.println("2-No");
            option = chooseInRange(1, 2);
        }
    }

    private void removeAStuff() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***removing a stuff***\n");
        ArrayList<Stuff> stuffs = superMarket.getStuffs();
        if(stuffs.size() == 0) {
            System.out.println("***this list is empty***\n");
            return;
        }
        for (int i=0; i<stuffs.size(); i++) {
            System.out.println((i+1) + "-" + stuffs.get(i).getName());
        }
        System.out.println("0-Back");
        int option = chooseInRange(0, stuffs.size()) - 1;
        if (option + 1 == 0) {
            return;
        }
        String name = stuffs.get(option).getName();
        if(makeSure(name)) {
            superMarket.getStuffs().remove(stuffs.get(option));
            System.out.println("***" + name + " successfully removed from this super market menu***\n");
        }
    }

    private void changeAStuffInformation() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***changing a stuff information***\n");
        ArrayList<Stuff> stuffs = superMarket.getStuffs();
        for (int i=0; i<stuffs.size(); i++) {
            System.out.println((i+1) + "-" + stuffs.get(i).getName());
        }
        System.out.println("0-Back");
        if(stuffs.size() == 0) {
            System.out.println("***This lis is empty***\n");
            return;
        }
        int option = chooseInRange(0, stuffs.size()) - 1;
        if (option + 1 == 0) {
            return;
        }
        Stuff stuff = stuffs.get(option);
        System.out.println("\n--------------------------------------------");
        System.out.println("***changing information of " + stuff.getName() + "***\n");
        System.out.println("1-Change name");
        System.out.println("2-Change price");
        System.out.println("3-Change amount");
        System.out.println("0-Back");
        option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                changeStuffName(stuff);
                break;
            case 2:
                changeStuffPrice(stuff);
                break;
            case 3:
                changeStuffAmount(stuff);
                break;
            default:
                return;
        }
    }

    private void changeStuffName(Stuff stuff) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing " + stuff.getName() + " name***\n");
        System.out.print("Enter new name: ");
        stuff.setName(ScannerWrapper.getInstance().nextLine());
        System.out.println("***Stuff name changed successfully***\n");
    }

    private void changeStuffPrice(Stuff stuff) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing " + stuff.getName() + " price***\n");
        System.out.print("Enter new price: ");
        stuff.setPrice(ScannerWrapper.getInstance().nextInt());
        System.out.println("***Stuff price changed successfully***\n");
    }

    private void changeStuffAmount(Stuff stuff) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing " + stuff.getName() + " amount***\n");
        System.out.print("Enter new amount: ");
        int amount = ScannerWrapper.getInstance().nextInt();
        while (amount < 0) {
            System.out.println("***Invalid input***\n");
        }
        superMarket.changeAStuffNumber(stuff, amount);
        System.out.println("***Stuff amount changed successfully***\n");
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
