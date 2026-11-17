
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        int[][] cost = new int[n][n];

        // Step 1: Precompute semi-palindrome costs
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {

                int j = i + len - 1;
                cost[i][j] = Integer.MAX_VALUE;

                // Check all proper divisors
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Compare symmetric characters
                    // in each group
                    for (int r = 0; r < d; r++) {

                        int left = i + r;
                        int right = i + r
                                + ((len - 1) / d) * d;

                        while (left < right) {

                            if (s.charAt(left) !=
                                s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[i][j] =
                        Math.min(cost[i][j], changes);
                }
            }
        }

        // Step 2: Dynamic Programming
        int INF = 1000000000;

        int[][] dp = new int[k + 1][n + 1];

        for (int t = 0; t <= k; t++) {
            for (int i = 0; i <= n; i++) {
                dp[t][i] = INF;
            }
        }

        dp[0][0] = 0;

        for (int t = 1; t <= k; t++) {

            for (int i = 2 * t; i <= n; i++) {

                for (int j = 2 * (t - 1);
                     j <= i - 2; j++) {

                    dp[t][i] = Math.min(
                        dp[t][i],
                        dp[t - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
