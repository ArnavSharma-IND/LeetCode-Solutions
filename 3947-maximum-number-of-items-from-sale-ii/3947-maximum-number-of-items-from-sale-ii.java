import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            int f = item[0];
            long p = item[1];
            freq[f]++;
            if (p < minPrice) {
                minPrice = p;
            }
        }

        // Count how many items have a factor divisible by v
        int[] divCount = new int[n + 1];
        for (int v = 1; v <= n; v++) {
            if (freq[v] > 0) {
                for (int multiple = v; multiple <= n; multiple += v) {
                    divCount[v] += freq[multiple];
                }
            }
        }

        // Collect [price, cap] for items that can grant at least 1 free copy
        List<long[]> bundles = new ArrayList<>();
        for (int[] item : items) {
            int cap = divCount[item[0]] - 1;
            if (cap > 0) {
                bundles.add(new long[]{item[1], cap});
            }
        }

        // Sort 2-value bundles by price ascending
        bundles.sort(Comparator.comparingLong(a -> a[0]));

        long remBudget = budget;
        long totalItems = 0;
        long best = remBudget / minPrice;

        for (long[] bundle : bundles) {
            long p = bundle[0];
            long cap = bundle[1];

            if (remBudget < p) {
                best = Math.max(best, totalItems + remBudget / minPrice);
                break;
            }

            if (p >= 2 * minPrice) {
                // Check buying 1 copy of this bundle vs finishing purely with minPrice
                best = Math.max(best, totalItems + 2 + (remBudget - p) / minPrice);
                best = Math.max(best, totalItems + remBudget / minPrice);
                break;
            }

            long take = Math.min(cap, remBudget / p);
            totalItems += take * 2;
            remBudget -= take * p;
            best = Math.max(best, totalItems + remBudget / minPrice);
        }

        return (int) best;
    }
}
