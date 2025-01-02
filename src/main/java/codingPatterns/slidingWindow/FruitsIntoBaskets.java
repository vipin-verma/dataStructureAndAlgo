package codingPatterns.slidingWindow;

import java.util.*;

public class FruitsIntoBaskets {
    public static int maxFruits(char[] fruits) {
        // If the input array is null or empty, return 0 as no fruits can be collected
        if (fruits == null || fruits.length == 0) {
            return 0;
        }

        int maxFruits = 0; // Keeps track of the maximum number of fruits collected
        int windowStart = 0; // Marks the start of the sliding window
        Map<Character, Integer> fruitCount = new HashMap<>(); // Stores the count of each fruit in the current window

        // Expand the sliding window by iterating through the array
        for (int windowEnd = 0; windowEnd < fruits.length; windowEnd++) {
            char rightFruit = fruits[windowEnd]; // Current fruit being added to the window
            // Increment the count of the current fruit in the HashMap
            fruitCount.put(rightFruit, fruitCount.getOrDefault(rightFruit, 0) + 1);

            // Shrink the window if there are more than 2 types of fruits
            while (fruitCount.size() > 2) {
                char leftFruit = fruits[windowStart]; // Fruit at the start of the window
                // Decrement the count of the leftmost fruit
                fruitCount.put(leftFruit, fruitCount.get(leftFruit) - 1);
                // If the count of a fruit becomes zero, remove it from the HashMap
                if (fruitCount.get(leftFruit) == 0) {
                    fruitCount.remove(leftFruit);
                }
                windowStart++; // Move the start of the window forward
            }

            // Calculate the size of the current window and update the maximum fruits collected
            maxFruits = Math.max(maxFruits, windowEnd - windowStart + 1);
        }

        return maxFruits; // Return the maximum number of fruits collected
    }

    public static void main(String[] args) {
        // Test cases to verify the implementation
        System.out.println("Max fruits: " + maxFruits(new char[]{'A', 'B', 'C', 'A', 'C'})); // Output: 3
        System.out.println("Max fruits: " + maxFruits(new char[]{'A', 'B', 'C', 'B', 'B', 'C'})); // Output: 5
        System.out.println("Max fruits: " + maxFruits(new char[]{'A', 'A', 'A', 'B', 'B', 'C'})); // Output: 4
    }
}
