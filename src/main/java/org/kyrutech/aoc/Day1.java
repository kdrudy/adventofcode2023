package org.kyrutech.aoc;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(Day1.class.getClassLoader().getResourceAsStream("day1input.txt"));
        int sum = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            Util.debug(line);
            String[] parts = line.split("");
            List<String> numbers = new ArrayList<>();
            for(int i = 0;i<parts.length;i++) {
                String ch = parts[i];
                if (Util.isDigit(ch)) {
                    numbers.add(ch);
                } else {
                    String sub = line.substring(i);
                    if (sub.startsWith("one")) {
                        numbers.add("1");
                    } else if(sub.startsWith("two")) {
                        numbers.add("2");
                    } else if(sub.startsWith("three")) {
                        numbers.add("3");
                    } else if(sub.startsWith("four")) {
                        numbers.add("4");
                    } else if(sub.startsWith("five")) {
                        numbers.add("5");
                    } else if(sub.startsWith("six")) {
                        numbers.add("6");
                    } else if(sub.startsWith("seven")) {
                        numbers.add("7");
                    } else if(sub.startsWith("eight")) {
                        numbers.add("8");
                    } else if(sub.startsWith("nine")) {
                        numbers.add("9");
                    }
                }
            }
            Util.debug(numbers);
            String number = numbers.get(0) + numbers.get(numbers.size()-1);
            Util.debug(number);
            sum += Integer.parseInt(number);
        }
        System.out.println(sum);
    }

}
