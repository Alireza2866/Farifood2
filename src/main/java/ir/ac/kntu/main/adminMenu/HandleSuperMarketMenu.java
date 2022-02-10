package ir.ac.kntu.main.adminmenu;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.main.*;
import ir.ac.kntu.store.*;
import java.time.LocalTime;
import java.util.ArrayList;


public class HandleSuperMarketMenu {
    private Sort sort;

    private DataBase dataBase;

    public HandleSuperMarketMenu(DataBase dataBase) {
        this.dataBase = dataBase;
        sort = new Sort(dataBase);
    }

    public void superMarketMenu() {
        System.out.println("1-Add a new super market");
        System.out.println("2-Show a super market information");
        System.out.println("3-Show comments of a super market");
        System.out.println("4-Change a super market information");
        System.out.println("5-Remove a super market");
        System.out.println("0-Back");
        int option = chooseInRange(0, 5);
        superMarketMenuHandler(option);
    }

    private void superMarketMenuHandler(int option) {
        switch (option) {
            case 1:
                addANewSuperMarket();
                break;
            case 2:
                showASuperMarketInformation();
                break;
            case 3:
                showASuperMarketComments();
                break;
            case 4:
                changeASuperMarketInformationMenu();
                break;
            case 5:
                removeASuperMarket();
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

    private void addANewSuperMarket() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Add a new superMarket***\n");
        System.out.print("Name: ");
        String name = ScannerWrapper.getInstance().nextLine();
        System.out.print("Address: ");
        String address = ScannerWrapper.getInstance().nextLine();
        WorkingHours workingHours = getWorkingHours();
        System.out.print("Delivery price: ");
        int deliveryPrice = ScannerWrapper.getInstance().nextInt();
        System.out.println("Premium subscription price: ");
        int premiumSubscriptionPrice = ScannerWrapper.getInstance().nextInt();
        SuperMarket superMarket = new SuperMarket(name, address, workingHours, deliveryPrice, premiumSubscriptionPrice);
        dataBase.addASuperMarket(superMarket);
        System.out.println("***Super market added successfully***\n");
        addAStuff(superMarket);
        sort.sorting(dataBase.getSortMode());
    }

    private void showASuperMarketInformation() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Show a super market information***\n");
        SuperMarket superMarket = dataBase.chooseSuperMarket();
        if (superMarket == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Information of " + superMarket + "***\n");
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

    private void showASuperMarketComments() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of a super market***\n");
        SuperMarket superMarket = dataBase.chooseSuperMarket();
        if (superMarket == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Showing comments of " + superMarket + "***\n");
        if(superMarket.getComments().size() == 0) {
            System.out.println("***This list is empty***\n");
        } else {
            for (Comment comment : superMarket.getComments()) {
                System.out.println(comment + "\n----");
            }
        }
    }

    private void changeASuperMarketInformationMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change a super market information***\n");
        SuperMarket superMarket = dataBase.chooseSuperMarket();
        if (superMarket == null) {
            return;
        }
        System.out.println("\n--------------------------------------------");
        System.out.println("***Changing information of " + superMarket + "***\n");
        System.out.println("1-Change name");
        System.out.println("2-Change address");
        System.out.println("3-Change deliveries number");
        System.out.println("4-Change delivery price");
        System.out.println("5-Change premium subscription price");
        System.out.println("6-Change stuffs list");
        System.out.println("0-Back");
        int option = chooseInRange(0, 6);
        changeASuperMarketInformationMenuHandler(option, superMarket);
    }

    private void changeASuperMarketInformationMenuHandler(int option, SuperMarket superMarket) {
        switch (option) {
            case 1:
                changeSuperMarketName(superMarket);
                break;
            case 2:
                changeSuperMarketAddress(superMarket);
                break;
            case 3:
                changeSuperMarketDeliveriesNumber(superMarket);
                break;
            case 4:
                changeSuperMarketDeliveryPrice(superMarket);
                break;
            case 5:
                changeSuperMarketPremiumSubscriptionPrice(superMarket);
                break;
            case 6:
                changeSuperMarketStuffsList(superMarket);
                break;
            default:
                return;
        }
    }

    private void changeSuperMarketName(SuperMarket superMarket) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + superMarket.getName() + " name***\n");
        System.out.print("Enter new name: ");
        superMarket.setName(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Name changed successfully***\n");
    }

    private void changeSuperMarketAddress(SuperMarket superMarket) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + superMarket.getName() + " address***\n");
        System.out.print("Enter new address: ");
        superMarket.setAddress(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("***Address changed successfully***\n");
    }

    private void changeSuperMarketDeliveriesNumber(SuperMarket superMarket) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + superMarket.getName() + " deliveries number***\n");
        System.out.print("Enter new number of deliveries: ");
        int deliveriesNumber = ScannerWrapper.getInstance().nextInt();
        while (deliveriesNumber < 2) {
            System.out.println("***Deliveries number should be greater than 1***\n");
            deliveriesNumber = ScannerWrapper.getInstance().nextInt();
        }
        superMarket.setDeliveriesNumber(deliveriesNumber);
        System.out.println("***Deliveries number changed successfully***\n");
    }

    private void changeSuperMarketDeliveryPrice(SuperMarket superMarket) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + superMarket.getName() + " delivery price***\n");
        System.out.print("Enter new delivery price: ");
        int deliveryPrice = ScannerWrapper.getInstance().nextInt();
        while (deliveryPrice <= 0) {
            System.out.println("***Invalid input***\n");
            deliveryPrice = ScannerWrapper.getInstance().nextInt();
        }
        superMarket.setDeliveryPrice(deliveryPrice);
        System.out.println("***Delivery price changed successfully***\n");
    }

    private void changeSuperMarketPremiumSubscriptionPrice(SuperMarket superMarket) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + superMarket.getName() + " premium subscription price***\n");
        System.out.print("Enter new premium subscription price: ");
        int premiumSubscriptionPrice = ScannerWrapper.getInstance().nextInt();
        while (premiumSubscriptionPrice <= 0) {
            System.out.println("***Invalid input***\n");
            premiumSubscriptionPrice = ScannerWrapper.getInstance().nextInt();
        }
        superMarket.setPremiumSubscriptionPrice(premiumSubscriptionPrice);
        System.out.println("***Premium subscription price changed successfully***\n");
    }

    private void changeSuperMarketStuffsList(SuperMarket superMarket) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Change " + superMarket.getName() + " stuffs list***\n");
        System.out.println("1-Add a stuff");
        System.out.println("2-Remove a stuff");
        System.out.println("3-Change a stuff information");
        System.out.println("0-Back");
        int option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                addAStuff(superMarket);
                break;
            case 2:
                removeAStuff(superMarket);
                break;
            case 3:
                changeAStuffInformation(superMarket);
                break;
            default:
                return;
        }
    }

    private void addAStuff(SuperMarket superMarket) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Adding stuffs to the menu of " + superMarket + "***\n");
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

    private void removeAStuff(SuperMarket superMarket) {
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

    private void changeAStuffInformation(SuperMarket superMarket) {
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
                changeStuffAmount(stuff, superMarket);
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

    private void changeStuffAmount(Stuff stuff, SuperMarket superMarket) {
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

    private void removeASuperMarket() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Remove a super market***\n");
        SuperMarket superMarket = dataBase.chooseSuperMarket();
        if (superMarket == null) {
            return;
        }
        String superMarketName = superMarket.getName();
        if (makeSure(superMarketName)) {
            dataBase.removeASuperMarket(superMarket);
            System.out.println("***" + superMarketName + " successfully removed***");
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
