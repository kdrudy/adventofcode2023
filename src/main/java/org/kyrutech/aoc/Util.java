package org.kyrutech.aoc;

public class Util {
    public static boolean DEBUG = true;

    public static void debug(Object msg) {
        if(DEBUG) {
            System.out.printf("DEBUG: %s\n", msg.toString());
        }
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
