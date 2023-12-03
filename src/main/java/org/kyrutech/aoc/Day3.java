package org.kyrutech.aoc;

import java.util.Objects;
import java.util.Scanner;

public class Day3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(Objects.requireNonNull(Day1.class.getClassLoader().getResourceAsStream("day3input.txt")));
        int width = 0;
        int height = 0;
        while(sc.hasNext()) {
            String line = sc.nextLine();
            width = line.length();
            height++;
        }

        String schematic[][] = new String[height][width];
        sc = new Scanner(Objects.requireNonNull(Day1.class.getClassLoader().getResourceAsStream("day3input.txt")));
        int index = 0;
        while(sc.hasNext()) {
            String line = sc.nextLine();
            schematic[index] = line.split("");
            index++;
        }

        int sumOfParts = 0;
        int totalRatio = 0;

        for(int y = 0;y<schematic.length;y++) {
            for(int x = 0;x<schematic[y].length;x++) {
                System.out.print(schematic[y][x]);
            }
            System.out.println();
        }

        for(int y = 0;y<schematic.length;y++) {
            for(int x = 0;x<schematic[y].length;x++) {
                if(Util.isDigit(schematic[y][x]) && isStartOfNumber(x, y, schematic)) {
                    int number = getNumber(x, y, schematic);
                    if(isPartNumber(x, y, schematic)) {
                        sumOfParts += number;
                    }
                }
                if("*".equals(schematic[y][x])) {
                    int aboveNumber = findAboveNumber(x, y, schematic);
                    int aboveLeftNumber = aboveNumber == -1 ? findAboveLeftNumber(x, y, schematic) : -1;
                    int aboveRightNumber = aboveNumber == -1 ? findAboveRightNumber(x, y, schematic) : -1;
                    int belowNumber = findBelowNumber(x, y, schematic);
                    int belowLeftNumber = belowNumber == -1 ? findBelowLeftNumber(x, y, schematic) : -1;
                    int belowRightNumber = belowNumber == -1 ? findBelowRightNumber(x, y, schematic) : -1;
                    int leftNumber = findLeftNumber(x, y, schematic);
                    int rightNumber = findRightNumber(x, y, schematic);
                    int totalParts = 0;
                    if(aboveNumber > 0) totalParts++;
                    if(aboveLeftNumber > 0) totalParts++;
                    if(aboveRightNumber > 0) totalParts++;
                    if(belowNumber > 0) totalParts++;
                    if(belowLeftNumber > 0) totalParts++;
                    if(belowRightNumber > 0) totalParts++;
                    if(leftNumber > 0) totalParts++;
                    if(rightNumber > 0) totalParts++;
                    if(totalParts == 2) {
                        Util.debug(String.format("%s,%s valid", y, x));
                        int ratio = Math.abs(aboveNumber) *
                                Math.abs(aboveLeftNumber) *
                                Math.abs(aboveRightNumber) *
                                Math.abs(belowNumber) *
                                Math.abs(belowLeftNumber) *
                                Math.abs(belowRightNumber) *
                                Math.abs(leftNumber) *
                                Math.abs(rightNumber);
                        totalRatio += ratio;
                    } else {
                        Util.debug(String.format("%s,%s not valid", y, x));
                    }
                }
            }
        }

        System.out.println("Sum of Parts: " + sumOfParts);
        System.out.println("Total Ratio: " + totalRatio);
    }

    private static int findRightNumber(int x, int y, String[][] schematic) {
        if(x == schematic[y].length-1) { return -1; }
        if(Util.isDigit(schematic[y][x+1])) {
            return getNumber(x+1, y, schematic);
        }
        return -1;
    }

    private static int findLeftNumber(int x, int y, String[][] schematic) {
        if(x == 0) return -1;
        if(Util.isDigit(schematic[y][x-1])) {
            int startX = x-1;
            for(int i = x-1;i >= 0 && Util.isDigit(schematic[y][i]);i--) {
                if(Util.isDigit(schematic[y][i])) {
                    startX = i;
                }
            }
            return getNumber(startX, y, schematic);
        }
        return -1;
    }

    private static int findBelowLeftNumber(int x, int y, String[][] schematic) {
        if(y == schematic.length-1) return -1;
        if(x > 0 && Util.isDigit(schematic[y+1][x-1])) {
            int startX = x-1;
            for(int i = x-1;i >= 0 && Util.isDigit(schematic[y+1][i]);i--) {
                if(Util.isDigit(schematic[y+1][i])) {
                    startX = i;
                }
            }
            return getNumber(startX, y+1, schematic);
        }
        return -1;
    }

    private static int findBelowRightNumber(int x, int y, String[][] schematic) {
        if(y == schematic.length-1) return -1;
        if(x<schematic[y+1].length-1 && Util.isDigit(schematic[y+1][x+1])) {
            return getNumber(x+1, y+1, schematic);
        }
        return -1;
    }
    private static int findBelowNumber(int x, int y, String[][] schematic) {
        if(y == schematic.length-1) return -1;
        if(Util.isDigit(schematic[y+1][x])) {
            int startX = x;
            for(int i = x;i >= 0 && Util.isDigit(schematic[y+1][i]);i--) {
                 startX = i;
            }
            return getNumber(startX, y+1, schematic);
        }
        return -1;
    }

    private static int findAboveRightNumber(int x, int y, String[][] schematic) {
        if(y == 0) return -1;
        if(x<schematic[y-1].length-1 && Util.isDigit(schematic[y-1][x+1])) {
            return getNumber(x+1, y-1, schematic);
        }
        return -1;
    }
    private static int findAboveLeftNumber(int x, int y, String[][] schematic) {
        if(y == 0) return -1;
        if(x > 0 && Util.isDigit(schematic[y-1][x-1])) {
            int startX = x-1;
            for(int i = x-1;i >= 0 && Util.isDigit(schematic[y-1][i]);i--) {
                if(Util.isDigit(schematic[y-1][i])) {
                    startX = i;
                }
            }
            return getNumber(startX, y-1, schematic);
        }
        return -1;
    }
    private static int findAboveNumber(int x, int y, String[][] schematic) {
        if(y == 0) return -1;
        if(Util.isDigit(schematic[y-1][x])) {
            int startX = x;
            for(int i = x;i >= 0 && Util.isDigit(schematic[y-1][i]);i--) {
                startX = i;
            }
            return getNumber(startX, y-1, schematic);
        }
        return -1;
    }

    private static boolean isPartNumber(int x, int y, String[][] schematic) {
        boolean isPartNumber = false;
        for(int i = x; i<schematic[y].length && Util.isDigit(schematic[y][i]);i++) {
            //Above
            if(y != 0) {
                if(i != 0 && isSymbol(schematic[y-1][i-1])) {
                    return true;
                }
                if(isSymbol(schematic[y-1][i])) {
                    return true;
                }
                if(i < schematic[y].length-1 && isSymbol(schematic[y-1][i+1])) {
                    return true;
                }
            }
            //Same
            if(i != 0 && isSymbol(schematic[y][i-1])) {
                return true;
            }
            if(i < schematic[y].length-1 && isSymbol(schematic[y][i+1])) {
                return true;
            }
            //Below
            if(y < schematic.length-1) {
                if(i != 0 && isSymbol(schematic[y+1][i-1])) {
                    return true;
                }
                if(isSymbol(schematic[y+1][i])) {
                    return true;
                }
                if(i < schematic[y].length-1 && isSymbol(schematic[y+1][i+1])) {
                    return true;
                }
            }
        }

        return isPartNumber;
    }

    private static boolean isSymbol(String s) {
        return !Util.isDigit(s) && !".".equals(s);
    }

    private static int getNumber(int x, int y, String[][] schematic) {
        String number = schematic[y][x];
        for(int i = x+1; i<schematic[y].length && Util.isDigit(schematic[y][i]);i++) {
            number = number + schematic[y][i];
        }
        return Integer.parseInt(number);
    }

    private static boolean isStartOfNumber(int x, int y, String[][] schematic) {
        if(x == 0) return true;
        return !Util.isDigit(schematic[y][x-1]);
    }
}
