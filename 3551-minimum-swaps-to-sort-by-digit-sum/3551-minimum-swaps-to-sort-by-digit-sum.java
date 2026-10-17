import java.util.*;
class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            int x = nums[i], sum = 0;
            while (x > 0) {
                sum += x % 10;
                x /= 10;
            }
            arr[i] = new int[]{nums[i], sum};
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return a[0] - b[0];
        });
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < n; i++)
            pos.put(nums[i], i);

        boolean[] vis = new boolean[n];
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;
            int j = i, cycle = 0;
            while (!vis[j]) {
                vis[j] = true;
                j = pos.get(arr[j][0]);
                cycle++;
            }
            if (cycle > 1) swaps += cycle - 1;
        }
        return swaps;
    }
}
