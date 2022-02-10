package ir.ac.kntu.main;

import ir.ac.kntu.main.responsiblemenu.ResponsibleFruitShopMenu;
import ir.ac.kntu.main.responsiblemenu.ResponsibleRestaurantMenu;
import ir.ac.kntu.main.responsiblemenu.ResponsibleSuperMarketMenu;
import ir.ac.kntu.store.FruitShop;
import ir.ac.kntu.store.Restaurant;
import ir.ac.kntu.store.Store;
import ir.ac.kntu.store.SuperMarket;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;
import ir.ac.kntu.user.Responsible;
import ir.ac.kntu.main.adminmenu.*;
import ir.ac.kntu.main.customermenu.CustomerMenu;

public class HandleMenu {
    private DataBase dataBase;

    public HandleMenu(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void enterMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Main menu***\n");
        System.out.println("1-Log in");
        System.out.println("2-sign up");
        System.out.println("0-Exit");
        int option = chooseInRange(0, 2);
        switch (option) {
            case 1:
                userLogInMenu();
                break;
            case 2:
                userSignUpMenu();
                break;
            default:
                return;
        }
        enterMenu();
    }

    private void userLogInMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("*** Log in as: ***\n");
        System.out.println("1-Admin");
        System.out.println("2-Customer");
        System.out.println("3-Responsible");
        System.out.println("0-Back");
        int option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                adminLogIn();
                break;
            case 2:
                customerLogIn();
                break;
            case 3:
                responsibleLogIn();
                break;
            case 0:
                return;
            default:
                break;
        }
    }

    private void userSignUpMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("*** Sign up as: ***\n");
        System.out.println("1-Admin");
        System.out.println("2-Customer");
        System.out.println("3-Responsible");
        System.out.println("0-Exit");
        int option = chooseInRange(0, 3);
        switch (option) {
            case 1:
                adminSignUp();
                break;
            case 2:
                customerSignUp();
                break;
            case 3:
                responsibleSignUp();
                break;
            case 0:
                return;
            default:
                break;
        }
    }

    private void adminSignUp() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Admin sign up***\n");
        String userName = "";
        boolean acceptableName = true;
        while (true) {
            System.out.print("Username: ");
            userName = ScannerWrapper.getInstance().nextLine().trim();
            for (Admin admin : dataBase.getAdmins()) {
                if (admin.getUserName().equals(userName)) {
                    System.out.println("***This user name is not available, choose another one***");
                    acceptableName = false;
                    break;
                }
            }
            if (acceptableName) {
                break;
            }
        }
        System.out.print("Password: ");
        String password = ScannerWrapper.getInstance().nextLine().trim();
        Admin admin = new Admin(userName, password);
        dataBase.addAnAdmin(admin);
        System.out.println("***Admin signed up successfully***\n");
        adminMenu();
    }

    private void customerSignUp() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Customer sign up***\n");
        String userName = "";
        boolean acceptableName = true;
        while (true) {
            System.out.print("Username: ");
            userName = ScannerWrapper.getInstance().nextLine().trim();
            for (Customer customer : dataBase.getCustomers()) {
                if (customer.getUserName().equals(userName)) {
                    System.out.println("***This user name is not available, choose another one***");
                    acceptableName = false;
                    break;
                }
            }
            if (acceptableName) {
                break;
            }
        }
        System.out.print("Enter password: ");
        String password = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Phone number: ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine().trim();
        while (!phoneNumber.matches("^09[0-9]{9}$")) {
            System.out.println("Format of input is wrong.(Right format: 09XXXXXXXXX)");
            System.out.print("Try again: ");
            phoneNumber = ScannerWrapper.getInstance().nextLine().trim();
        }
        System.out.print("Address: ");
        String address = ScannerWrapper.getInstance().nextLine();
        Customer customer = new Customer(userName, password, phoneNumber, address);
        dataBase.addACustomer(customer);
        System.out.println("***Customer signed in successfully***\n");
        customerMenu(customer);
    }

    private void responsibleSignUp() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Responsible sign up***\n");
        Store store = null;
        System.out.println("Responsible for a:");
        System.out.println("1-restaurant");
        System.out.println("2-super market");
        System.out.println("3-fruit shop");
        int option = chooseInRange(1, 3);
        switch (option) {
            case 1:
                store = dataBase.chooseRestaurant();
                break;
            case 2:
                store = dataBase.chooseSuperMarket();
                break;
            case 3:
                store = dataBase.chooseFruitShop();
                break;
            default:
                break;
        }
        if(store == null) {
            return;
        }
        String userName = "";
        boolean acceptableName = true;
        while (true) {
            System.out.print("Username: ");
            userName = ScannerWrapper.getInstance().nextLine().trim();
            for (Responsible responsible : dataBase.getResponsibles()) {
                if (responsible.getUserName().equals(userName)) {
                    System.out.println("***This user name is not available, choose another one***");
                    acceptableName = false;
                    break;
                }
            }
            if (acceptableName) {
                break;
            }
        }
        System.out.print("Password: ");
        String password = ScannerWrapper.getInstance().nextLine().trim();
        Responsible responsible = new Responsible(userName, password, store);
        dataBase.addAResponsible(responsible);
        System.out.println("***Responsible signed in successfully***\n");
        responsibleMenu(responsible);
    }

    private void adminLogIn() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Admin log in***\n");
        System.out.print("Username: ");
        String userName = ScannerWrapper.getInstance().nextLine();
        System.out.print("Password: ");
        String password = ScannerWrapper.getInstance().nextLine();
        for (Admin admin : dataBase.getAdmins()) {
            if (admin.logIn(userName, password)) {
                System.out.println("***Admin loged in successfully***\n");
                adminMenu();
                return;
            }
        }
        System.out.println("***Username or password is wrong***\n");
        userLogInMenu();
    }

    private void customerLogIn() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Customer log in***\n");
        System.out.print("Username: ");
        String userName = ScannerWrapper.getInstance().nextLine();
        System.out.print("Password: ");
        String password = ScannerWrapper.getInstance().nextLine();
        for (Customer customer : dataBase.getCustomers()) {
            if (customer.logIn(userName, password)) {
                System.out.println("***Customer loged in successfully***\n");
                customerMenu(customer);
                return;
            }
        }
        System.out.println("***Username or password is wrong***\n");
        userLogInMenu();
    }

    private void responsibleLogIn() {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Responsible log in***\n");
        System.out.print("Username: ");
        String userName = ScannerWrapper.getInstance().nextLine();
        System.out.print("Password: ");
        String password = ScannerWrapper.getInstance().nextLine();
        for (Responsible responsible : dataBase.getResponsibles()) {
            if (responsible.logIn(userName, password)) {
                System.out.println("***Responsible loged in successfully***\n");
                responsibleMenu(responsible);
                return;
            }
        }
        System.out.println("***Username or password is wrong***\n");
        userLogInMenu();
    }

    private void adminMenu() {
        HandleSetting setting = new HandleSetting(dataBase);
        setting.settingMenu();
        System.out.println("\n--------------------------------------------");
        System.out.println("***Admin menu***\n");
        System.out.println("1-Restaurants menu");
        System.out.println("2-SuperMarkets menu");
        System.out.println("3-Fruit shops menu");
        System.out.println("4-Delivery menu");
        System.out.println("5-Order menu");
        System.out.println("6-Customer menu");
        System.out.println("7-Setting");
        System.out.println("0-Log out");
        int option = chooseInRange(0, 7);
        adminMenuHandler(option);
    }

    private void adminMenuHandler(int option) {
        if (option != 0) {
            System.out.println("\n--------------------------------------------");
        }
        switch (option) {
            case 1:
                System.out.println("***Restaurants menu***\n");
                HandleRestaurantMenu handleRestaurantMenu = new HandleRestaurantMenu(dataBase);
                handleRestaurantMenu.restaurantMenu();
                adminMenu();
            case 2:
                System.out.println("***Super markets menu***\n");
                HandleSuperMarketMenu handleSuperMarketMenu = new HandleSuperMarketMenu(dataBase);
                handleSuperMarketMenu.superMarketMenu();
                break;
            case 3:
                System.out.println("***Fruit shops menu***\n");
                HandleFruitShopMenu handleFruitShopMenu = new HandleFruitShopMenu(dataBase);
                handleFruitShopMenu.fruitShopMenu();
                break;
            case 4:
                System.out.println("***Delivery menu***\n");
                HandleDeliveryMenu handleDeliveryMenu = new HandleDeliveryMenu(dataBase);
                handleDeliveryMenu.deliveryMenu();
                adminMenu();
            case 5:
                System.out.println("***Order menu***\n");
                HandleOrderMenu handleOrderMenu = new HandleOrderMenu(dataBase);
                handleOrderMenu.orderMenu();
                adminMenu();
            case 6:
                System.out.println("***Customer menu***\n");
                HandleCustomerMenu handleCustomerMenu = new HandleCustomerMenu(dataBase);
                handleCustomerMenu.customerMenu();
                adminMenu();
            case 7:
                System.out.println("***Setting***\n");
                HandleSetting setting = new HandleSetting(dataBase);
                setting.settingMenu();
                adminMenu();
            case 0:
                return;
            default:
                System.out.println("***Invalid input***\n");
                adminMenu();
        }
    }

    private void customerMenu(Customer customer) {
        System.out.println("\n--------------------------------------------");
        System.out.println("***Customer menu***\n");
        CustomerMenu customerMenu = new CustomerMenu(dataBase, customer);
        customerMenu.customerMenu();
    }

    private void responsibleMenu(Responsible responsible) {
        System.out.println("\n--------------------------------------------");
        if (responsible.getStore() instanceof Restaurant) {
            Restaurant restaurant = (Restaurant) responsible.getStore();
            System.out.println("***Responsible of " + restaurant + " menu***\n");
            ResponsibleRestaurantMenu responsibleRestaurantMenu = new ResponsibleRestaurantMenu(dataBase, restaurant);
            responsibleRestaurantMenu.restaurantMenu();
        }
        if (responsible.getStore() instanceof SuperMarket) {
            SuperMarket superMarket = (SuperMarket) responsible.getStore();
            System.out.println("***Responsible of " + superMarket + " menu***\n");
            ResponsibleSuperMarketMenu responsibleSuperMarketMenu = new ResponsibleSuperMarketMenu(dataBase, superMarket);
            responsibleSuperMarketMenu.superMarketMenu();
        }
        if (responsible.getStore() instanceof FruitShop) {
            FruitShop fruitShop = (FruitShop) responsible.getStore();
            System.out.println("***Responsible of " + fruitShop + " menu***\n");
            ResponsibleFruitShopMenu responsibleFruitShopMenu = new ResponsibleFruitShopMenu(dataBase, fruitShop);
            responsibleFruitShopMenu.fruitShopMenu();
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
}
