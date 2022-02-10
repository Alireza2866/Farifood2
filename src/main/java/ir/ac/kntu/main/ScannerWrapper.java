package ir.ac.kntu.main;

import java.util.Scanner;

public class ScannerWrapper {
    private static ScannerWrapper instance = new ScannerWrapper();

    private Scanner scanner;

    private ScannerWrapper(){
        scanner = new Scanner(System.in);
    }

    public static ScannerWrapper getInstance(){
        return instance;
    }

    public int nextInt(){
        String input = scanner.nextLine().replaceAll("\\s", "");
        if(input.matches("[0-9]+")) {
            return Integer.parseInt(input);
        }
        return -1;
    }

    public double nextDouble(){
        return scanner.nextDouble();
    }

    public String nextLine(){
        return scanner.nextLine();
    }

    public String next() {
        return scanner.next();
    }

    public void close() {
        scanner.close();
    }
}
