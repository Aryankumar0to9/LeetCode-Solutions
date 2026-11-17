
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {

        int n = s.length();
        int INF = 1000000;

        // Stage 1 --  Precompute costs
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {

            for (int l = 0; l + len <= n; l++) {

                int r = l + len - 1;
                cost[l][r] = INF;

                // Try every - proper divisor
                for (int d = 1; d < len; d++) {

                    if (len % d != 0)
                        continue;

                    int groups = len / d;
                    int changes = 0;

                    // Check each group ,
                    for (int i = 0; i < d; i++) {

                        for (int j = 0; j < groups / 2; j++) {

                            int left = l + i + j * d;

                            int right = l + i
                                    + (groups - 1 - j) * d;

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        // Stage -- 2) Dynamic Programming
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {

            for (int i = 2 * p; i <= n; i++) {

                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
