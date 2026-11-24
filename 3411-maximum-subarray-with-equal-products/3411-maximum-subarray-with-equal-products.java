class Solution {
    private static final int[] MASK = {0, 0, 1, 2, 1, 4, 3, 8, 1, 2, 5};
    public int maxLength(int[] nums) {
        int left = 0, used = 0, best = 0;

        for (int right = 0; right < nums.length; right++) {
            int m = MASK[nums[right]];

            while ((used & m) != 0) {
                used ^= MASK[nums[left]];
                left++;
            }

            used |= m;
            best = Math.max(best, right - left + 1);
        }
        return Math.max(best, 2);
    }
}