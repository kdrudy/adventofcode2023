package org.kyrutech.aoc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Objects.requireNonNull(Day1.class.getClassLoader().getResourceAsStream("day4input.txt")));
        int totalScore = 0;
        Map<Integer, Integer> cards = new HashMap<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] parts = line.split(":");
            String[] card = parts[0].split("\\s+");
            int cardNumber = Integer.parseInt(card[1]);
            if(cards.containsKey(cardNumber)) {
                cards.put(cardNumber, cards.get(cardNumber)+1);
            } else {
                cards.put(cardNumber, 1);
            }
            String[] numbersParts = parts[1].split("\\|");
            String[] winningNumbers = numbersParts[0].trim().split(" ");
            String[] myNumbers = numbersParts[1].trim().split(" ");
            int foundNumbers = 0;
            for (String wNum : winningNumbers) {
                if("".equals(wNum.trim())) {
                    continue;
                }
                for (String mNum : myNumbers) {
                    if (wNum.equals(mNum)) {
                        foundNumbers++;
                        break;
                    }
                }
            }
            int score = 0;
            if (foundNumbers > 0) {
                score = (int) Math.pow(2, foundNumbers-1);
            }
            int currentTotalCards = cards.get(cardNumber);
            while(foundNumbers > 0) {
                int cardToAdd = cardNumber + foundNumbers;
                if(cards.containsKey(cardToAdd)) {
                    cards.put(cardToAdd, cards.get(cardToAdd)+currentTotalCards);
                } else {
                    cards.put(cardToAdd, currentTotalCards);
                }
                foundNumbers--;
            }
//            System.out.println(parts[0] + " Score: " + score);
            totalScore += score;
        }
        System.out.println("Total Score: " + totalScore);
        System.out.println("Total Cards: " + cards.values().stream().mapToInt(c -> c).sum());
    }
}
