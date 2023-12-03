package org.kyrutech.aoc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day2 {

    public static int MAX_RED = 12;
    public static int MAX_GREEN = 13;
    public static int MAX_BLUE = 14;

    public static void main(String[] args) {

        Scanner sc = new Scanner(Day1.class.getClassLoader().getResourceAsStream("day2input.txt"));
        int sum = 0;
        int gamepower = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            Util.debug(line);
            String[] firstSplit = line.split(":");
            String[] firstSplitSplit = firstSplit[0].split(" ");
            int game = Integer.parseInt(firstSplitSplit[1]);

            String[] hands = firstSplit[1].split(";");
            boolean valid = true;
            Map<String, Integer> balls = new HashMap<>();
            for (String hand : hands) {
                String[] cards = hand.split(",");
                for (String card : cards) {
                    String[] parts = card.trim().split(" ");
                    int value = Integer.parseInt(parts[0]);
                    switch (parts[1]) {
                        case "red":
                            if(value > MAX_RED) valid = false;
                            break;
                        case "green":
                            if(value > MAX_GREEN) valid = false;
                            break;
                        case "blue":
                            if(value > MAX_BLUE) valid = false;
                            break;
                    }
                    if(balls.containsKey(parts[1])) {
                        if(balls.get(parts[1]) < value) {
                            balls.put(parts[1], value);
                        }
                    } else {
                        balls.put(parts[1], value);
                    }
                }
            }
            if(valid) {
                sum += game;
            }
            int power = 1;
            for(int value : balls.values()) {
                power *= value;
            }
            gamepower += power;
        }
        System.out.println("Valid Sum: " + sum);
        System.out.println("Game Power: " + gamepower);
    }
}
