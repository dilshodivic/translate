package org.example.util;

import java.util.Scanner;

public class ScannerUtil {
    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);
    public static final Scanner SCANNER_CHAR = new Scanner(System.in);
    public static int getAction() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter action: ");
            try {
                int n = scanner.nextInt();
                return n;
            } catch (RuntimeException e) {
                System.out.println("Hato kirildi. Son kiriting Mazgi.");
            }
        }

    }

}
