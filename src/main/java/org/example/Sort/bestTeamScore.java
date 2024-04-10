package org.example.Sort;

import java.util.Arrays;

public class bestTeamScore {
    int res = 0;

    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] pair = new int[n][2];

        for (int i = 0; i < n; i++) {
            pair[i][0] = ages[i];
            pair[i][1] = scores[i];
        }
        // sort pair by age -> scores
        Arrays.sort(pair, (p1, p2) -> p1[0] != p2[0] ? p1[0] - p2[0] : p1[1] - p2[1]);

        // dp save score max so far
        int[] dp = new int[n];
        dp[0] = pair[0][1];

        for (int i = 1; i < n; i++) {
            int max = pair[i][1];

            for (int j = 0; j < i; j++) {
                if (pair[i][1] >= pair[j][1]) {
                    max = Math.max(max, dp[j] + pair[i][1]);
                }
            }

            dp[i] = max;
        }

        int max = Integer.MIN_VALUE;
        for (int d : dp) {
            if (d > max)
                max = d;
        }

        return max;
    }

    public static void main(String[] args) {

        System.out.println(new bestTeamScore().bestTeamScore(new int[]{5, 3, 4, 6}, new int[]{1, 1, 2, 2}));
    }
}
