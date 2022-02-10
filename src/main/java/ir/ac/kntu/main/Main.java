package ir.ac.kntu.main;

public class Main {
    public static void main(String[] args) {
        System.out.println("---*** WELCOME TO FARIFOOD SERVICE ***---\n");
        DataBase dataBase = new DataBase();
        HandleMenu handleMenu = new HandleMenu(dataBase);
        handleMenu.enterMenu();
        System.out.println("\nThanks for using our service.");
    }
}
