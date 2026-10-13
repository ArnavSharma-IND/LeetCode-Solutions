
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int[] freq = new int[40001];
        for (int x : nums) {
            freq[Math.abs(x)]++;
        }
        long sum = 0;
        int left = 0, right = 40000;
        for (int k = 0; k < nums.length / 2; k++) {
            while (freq[left] == 0) left++;
            while (freq[right] == 0) right--;

            sum += (long) right * right - (long) left * left;

            freq[left]--;
            freq[right]--;
        }
        if (nums.length % 2 == 1) {
            while (freq[right] == 0) right--;
            sum += (long) right * right;
        }
        return sum;
    }
}
