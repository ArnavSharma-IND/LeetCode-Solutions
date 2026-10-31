
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        long[][] right = new long[m][n];
        long[][] down = new long[m][n];

        long ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Mirror cell
                if (grid[i][j] == 1) {

                    // Entered from left: reflect downward
                    if (right[i][j] > 0 && i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + right[i][j]) % MOD;
                    }

                    // Entered from above: reflect rightward
                    if (down[i][j] > 0 && j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + down[i][j]) % MOD;
                    }

                } else {

                    // Starting cell
                    long ways;

                    if (i == 0 && j == 0) {
                        ways = 1;
                    } else {
                        ways = (right[i][j] + down[i][j]) % MOD;
                    }

                    // Destination reached
                    if (i == m - 1 && j == n - 1) {
                        ans = ways;
                        continue;
                    }

                    // Move right
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + ways) % MOD;
                    }

                    // Move down
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + ways) % MOD;
                    }
                }
            }
        }

        return (int) ans;
    }
}
