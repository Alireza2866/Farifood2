package ir.ac.kntu.main.adminmenu;

import ir.ac.kntu.main.*;
import ir.ac.kntu.main.ScannerWrapper;

public class HandleSetting {
    private Sort sort;

    private DataBase dataBase;

    public HandleSetting(DataBase dataBase) {
        this.dataBase = dataBase;
        sort = new Sort(dataBase);
    }

    public void settingMenu() {
        System.out.println("How to display lists?");
        System.out.println("1-Ascending based on score");
        System.out.println("2-Descending based on score");
        System.out.println("3-Ascending based on comments number");
        System.out.println("4-Descending based on comments number");
        int option = chooseInRange(1, 4);
        settingMenuHandler(option);
    }

    private void settingMenuHandler(int option) {
        switch (option) {
            case 1:
                dataBase.setSortMode(SortMode.ASCENDING_BASED_ON_SCORE);
                break;
            case 2:
                dataBase.setSortMode(SortMode.DESCENDING_BASED_ON_SCORE);
                break;
            case 3:
                dataBase.setSortMode(SortMode.ASCENDING_BASED_ON_COMMENTS);
                break;
            case 4:
                dataBase.setSortMode(SortMode.DESCENDING_BASED_ON_COMMENTS);
                break;
            default:
                break;
        }
        sort.sorting(dataBase.getSortMode());
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
