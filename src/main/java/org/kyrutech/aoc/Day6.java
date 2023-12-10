package org.kyrutech.aoc;

import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        Scanner sc = Util.getScanner("day6input.txt");
//        String[] times = null;
//        String[] distances = null;

        long time = 0;
        long distance = 0;

        while(sc.hasNext()) {
            String line = sc.nextLine();
            String[] parts = line.split(":");
            switch(parts[0]) {
                case "Time":
//                    times = parts[1].trim().split("\\s+");
                    time = Long.parseLong(parts[1].replace(" ", ""));
                    break;
                case "Distance":
//                    distances = parts[1].trim().split("\\s+");
                    distance = Long.parseLong(parts[1].replace(" ", ""));
                    break;
            }
        }
//        int[] waysToWin = new int[times.length];
//        for(int i = 0;i<times.length;i++) {
//            int time = Integer.parseInt(times[i]);
//            int distance = Integer.parseInt(distances[i]);
        long waysToWin = 0;
            for(int speed = 0;speed <= time;speed++) {
                long timeAtSpeed = time - speed;
                long distanceTraveled = speed * timeAtSpeed;
                if(distanceTraveled > distance) {
                    waysToWin++;
                }
            }
//        }
//        int combosToWin = 1;
//        for(int way : waysToWin) {
//            combosToWin *= way;
//        }
        System.out.println("Combos To Win: " + waysToWin);
    }
}
