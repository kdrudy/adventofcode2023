package org.kyrutech.aoc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day5 {
    public static void main(String[] args) {

        Scanner sc = Util.getScanner("day5input.txt");
        long[] seeds = null;
        List<Long[]> seedToSoil = new ArrayList<>();
        List<Long[]> soilToFert = new ArrayList<>();
        List<Long[]> fertToWater = new ArrayList<>();
        List<Long[]> waterToLight = new ArrayList<>();
        List<Long[]> lightToTemp = new ArrayList<>();
        List<Long[]> tempToHumidity = new ArrayList<>();
        List<Long[]> humidityToLoc = new ArrayList<>();
        while(sc.hasNext()) {
            String line = sc.nextLine();
            if(line.startsWith("seeds:")) {
                String[] split = line.split(":");
                String[] seedStrings = split[1].trim().split(" ");
                seeds = new long[seedStrings.length];
                for (int i = 0; i < seedStrings.length; i++) {
                    seeds[i] = Long.parseLong(seedStrings[i]);

                }
            } else {
                switch(line) {
                    case "seed-to-soil map:":
                        getMap(sc, seedToSoil);
                        break;
                    case "soil-to-fertilizer map:":
                        getMap(sc, soilToFert);
                        break;
                    case "fertilizer-to-water map:":
                        getMap(sc, fertToWater);
                        break;
                    case "water-to-light map:":
                        getMap(sc, waterToLight);
                        break;
                    case "light-to-temperature map:":
                        getMap(sc, lightToTemp);
                        break;
                    case "temperature-to-humidity map:":
                        getMap(sc, tempToHumidity);
                        break;
                    case "humidity-to-location map:":
                        getMap(sc, humidityToLoc);
                        break;
                }
            }
            humidityToLoc = humidityToLoc.stream().sorted(Comparator.comparing(a -> a[0])).collect(Collectors.toList());
        }

        for(long i = 1; ;i++) {
            long humidity = getPreviousValue(i, humidityToLoc);
            long temp = getPreviousValue(humidity, tempToHumidity);
            long light = getPreviousValue(temp, lightToTemp);
            long water = getPreviousValue(light, waterToLight);
            long fert = getPreviousValue(water, fertToWater);
            long soil = getPreviousValue(fert, soilToFert);
            long seed = getPreviousValue(soil, seedToSoil);

            int seedIndex = 0;
            while(seedIndex < seeds.length) {
                long start = seeds[seedIndex];
                long range = seeds[seedIndex+1];
                if(seed >= start && seed < start+range) {
                    System.out.println();
                    System.out.println("Lowest Location: " + i);
                    return;
                }
                seedIndex+=2;
            }

        }

//            for (long i = seeds[startIndex]; i < seeds[startIndex] + seeds[rangeIndex]; i++) {
//                long soil = getNextValue(i, seedToSoil);
//                Util.debug(String.format("Seed %d equals Soil %d", i, soil));
//                long fert = getNextValue(soil, soilToFert);
//                Util.debug(String.format("Soil %d equals Fert %d", soil, fert));
//                long water = getNextValue(fert, fertToWater);
//                Util.debug(String.format("Fert %d equals Water %d", fert, water));
//                long light = getNextValue(water, waterToLight);
//                Util.debug(String.format("Water %d equals Light %d", water, light));
//                long temp = getNextValue(light, lightToTemp);
//                Util.debug(String.format("Light %d equals Temp %d", light, temp));
//                long humidity = getNextValue(temp, tempToHumidity);
//                Util.debug(String.format("Temp %d equals Humidity %d", temp, humidity));
//                long loc = getNextValue(humidity, humidityToLoc);
//                Util.debug(String.format("Humidity %d equals Loc %d", humidity, loc));
//
//                if(lowestLoc[0] == -1) {
//                    lowestLoc[0] = loc;
//                } else if(lowestLoc[0] > loc) {
//                    lowestLoc[0] = loc;
//                }
//            }
//            startIndex+=2;
//            rangeIndex+=2;
//        } while(startIndex < seeds.length);

//        for(long seed : seeds) {
//            long soil = getNextValue(seed, seedToSoil);
//            Util.debug(String.format("Seed %d equals Soil %d", seed, soil));
//            long fert = getNextValue(soil, soilToFert);
//            Util.debug(String.format("Soil %d equals Fert %d", soil, fert));
//            long water = getNextValue(fert, fertToWater);
//            Util.debug(String.format("Fert %d equals Water %d", fert, water));
//            long light = getNextValue(water, waterToLight);
//            Util.debug(String.format("Water %d equals Light %d", water, light));
//            long temp = getNextValue(light, lightToTemp);
//            Util.debug(String.format("Light %d equals Temp %d", light, temp));
//            long humidity = getNextValue(temp, tempToHumidity);
//            Util.debug(String.format("Temp %d equals Humidity %d", temp, humidity));
//            long loc = getNextValue(humidity, humidityToLoc);
//            Util.debug(String.format("Humidity %d equals Loc %d", humidity, loc));
//
//            if(lowestLoc == -1) {
//                lowestLoc = loc;
//            } else if(lowestLoc > loc) {
//                lowestLoc = loc;
//            }
//        }
//        System.out.println();
//        System.out.println("Lowest Location: " + lowestLoc);
    }

    private static long getPreviousValue(long current, List<Long[]> map) {
        long prev = current;
        Long[] mapEntry = map.stream().filter((e -> e[0] <= current && e[0] + e[2] - 1 >= current)).findFirst().orElse(null);
        if(mapEntry != null) {
            long offset = current - mapEntry[0];
            prev = mapEntry[1] + offset;
        }
        return prev;
    }

    private static long getNextValue(long current, List<Long[]> map) {
        long next = current;
        Long[] seedToSoilEntry = map.stream().filter(e -> e[1] <= current && e[1] + e[2] - 1 >= current).findFirst().orElse(null);
        if(seedToSoilEntry != null) {
            long offset = current - seedToSoilEntry[1];
            next = seedToSoilEntry[0] + offset;
        }
        return next;
    }

    private static void getMap(Scanner sc, List<Long[]> seedToSoil) {
        String nextLine = sc.nextLine();
        do {
            String[] s = nextLine.split(" ");
            Long[] entries = new Long[s.length];
            for (int i = 0; i < s.length; i++) {
                entries[i] = Long.parseLong(s[i]);
            }
            seedToSoil.add(entries);
            if(sc.hasNext()) {
                nextLine = sc.nextLine();
            } else {
                nextLine = "";
            }
        } while(!nextLine.isBlank());
    }
}
