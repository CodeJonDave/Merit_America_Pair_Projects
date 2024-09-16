package com.techelevator;

import java.util.*;

public class Grades {
    public static int countCombinations(List<Integer> nums, int x, int y) {
        int[][] dp = new int[x + 1][y + 1];
        dp[0][0] = 1;

        for (int num : nums) {
            for (int i = x; i >= num; i--) {
                for (int j = 1; j <= y; j++) {
                    dp[i][j] += dp[i - num][j - 1];
                }
            }
        }

        return dp[x][y];
    }

    public static void main(String[] args) {
        List<Integer> nums = List.of(2, 2, 1, 3, 2);
        int x = 4;
        int y = 2;
        int combinations = countCombinations(nums, x, y);
        System.out.println(combinations); // Output should be 2
    }
}