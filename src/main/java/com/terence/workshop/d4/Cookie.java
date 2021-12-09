package com.terence.workshop.d4;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Cookie {
//    private String path;
//
//    public void setPath(String path) {
//        this.path = "src/main/CookieFile";
//    }

    public static String  randomCookie() {
        String option = String.valueOf((int) (Math.random() * 4) + 1);
        String fileSelection = "src/main/CookieFile/" + option + ".txt";
        File file = new File(fileSelection);
        Scanner scan = null;
        try {
            scan = new Scanner(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return (scan.nextLine());
    }
}
