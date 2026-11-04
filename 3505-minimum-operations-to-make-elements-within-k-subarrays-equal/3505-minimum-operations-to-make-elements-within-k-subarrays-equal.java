import java.util.*;
class Solution {

    TreeMap<Integer, Integer> low = new TreeMap<>();
    TreeMap<Integer, Integer> high = new TreeMap<>();

    long sumLow = 0, sumHigh = 0;
    int sizeLow = 0, sizeHigh = 0;

    void add(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    void remove(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);

        if (count == 1)
            map.remove(val);
        else
            map.put(val, count - 1);
    }

    void insert(int val) {

        if (low.isEmpty() || val <= low.lastKey()) {
            add(low, val);
            sumLow += val;
            sizeLow++;
        } else {
            add(high, val);
            sumHigh += val;
            sizeHigh++;
        }

        balance();
    }

    void erase(int val) {

        if (low.containsKey(val)) {
            remove(low, val);
            sumLow -= val;
            sizeLow--;
        } else {
            remove(high, val);
            sumHigh -= val;
            sizeHigh--;
        }

        balance();
    }

    void balance() {

        while (sizeLow > sizeHigh + 1) {
            int val = low.lastKey();

            remove(low, val);
            sizeLow--;
            sumLow -= val;

            add(high, val);
            sizeHigh++;
            sumHigh += val;
        }

        while (sizeLow < sizeHigh) {
            int val = high.firstKey();

            remove(high, val);
            sizeHigh--;
            sumHigh -= val;

            add(low, val);
            sizeLow++;
            sumLow += val;
        }
    }

    long getCost() {

        long median = low.lastKey();

        return median * sizeLow - sumLow
             + sumHigh - median * sizeHigh;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            insert(nums[i]);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {

            erase(nums[i - x]);
            insert(nums[i]);

            cost[i - x + 1] = getCost();
        }

        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        Arrays.fill(prev, 0);

        for (int j = 1; j <= k; j++) {

            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                curr[i] = curr[i - 1];

                if (i >= x && prev[i - x] != INF) {

                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            prev = curr;
        }

        return prev[n];
    }
}
