package org.kyrutech.aoc;

import java.util.Objects;
import java.util.Scanner;

public class Util {
    public static boolean DEBUG = true;

    public static void debug(Object msg) {
        if(DEBUG) {
            System.out.printf("DEBUG: %s\n", msg.toString());
        }
    }

    public static Scanner getScanner(String filename) {
        return new Scanner(Objects.requireNonNull(Day1.class.getClassLoader().getResourceAsStream(filename)));
    }

    public static boolean isDigit(String ch) {
        try {
            int i = Integer.parseInt(ch);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
