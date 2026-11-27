
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);
        }

        int[] reward = new int[n];

        for (int i = 0; i < n; i++) {
            reward[i] = 1;

            for (int j = 0; j < n; j++) {
                if (i != j &&
                    items[j][0] % items[i][0] == 0) {
                    reward[i]++;
                }
            }
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i <= budget; i++) {
            dp[i] = -1;
        }

        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];

            for (int j = budget; j >= price; j--) {
                if (dp[j - price] != -1) {
                    dp[j] = Math.max(
                        dp[j],
                        dp[j - price] + reward[i]
                    );
                }
            }
        }

        int answer = 0;

        for (int cost = 0; cost <= budget; cost++) {
            if (dp[cost] != -1) {
                int extra = (budget - cost) / minPrice;

                answer = Math.max(
                    answer,
                    dp[cost] + extra
                );
            }
        }

        return answer;
    }
}
