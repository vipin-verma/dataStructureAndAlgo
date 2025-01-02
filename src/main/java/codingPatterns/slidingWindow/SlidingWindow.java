package codingPatterns.slidingWindow;

import java.util.*;

public class SlidingWindow {
    public static int longestSubstringWithKDistinct(String s, int k) {
        // If the input string is null, empty, or k is less than or equal to 0, return 0 as no valid substring exists
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }

        int maxLength = 0; // Tracks the maximum length of substring with at most k distinct characters
        int windowStart = 0; // Marks the start of the sliding window
        Map<Character, Integer> charFrequencyMap = new HashMap<>(); // Stores the frequency of characters in the current window

        // Expand the sliding window by iterating through the string
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd); // Current character being added to the window
            // Increment the frequency of the character in the HashMap
            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);

            // Shrink the window if the number of distinct characters exceeds k
            while (charFrequencyMap.size() > k) {
                char leftChar = s.charAt(windowStart); // Character at the start of the window
                // Decrease the frequency of the leftmost character
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                // Remove the character from the map if its frequency becomes 0
                if (charFrequencyMap.get(leftChar) == 0) {
                    charFrequencyMap.remove(leftChar);
                }
                windowStart++; // Move the start of the window forward
            }

            // Calculate the length of the current window and update the maximum length if needed
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength; // Return the maximum length of substring with at most k distinct characters
    }

    public static void main(String[] args) {
        // Test cases to verify the implementation
        System.out.println("Longest substring length: " + longestSubstringWithKDistinct("araaci", 2)); // Output: 4
        System.out.println("Longest substring length: " + longestSubstringWithKDistinct("araaci", 1)); // Output: 2
        System.out.println("Longest substring length: " + longestSubstringWithKDistinct("cbbebi", 3)); // Output: 5
    }
}
