import java.util.*;
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        Stack<Integer> stack = new Stack<>();
        for(int i = 2 * n - 1; i >= 0; i--) {
            int index = i % n;
            while(!stack.isEmpty() &&
                  nums[stack.peek()] <= nums[index]) {
                stack.pop();
            }
            // A. Find and store the next greater element here
            if(i < n && !stack.isEmpty()) {
                answer[index] = nums[stack.peek()];
            }
            // B. Push current index here
            stack.push(index);
        }
        return answer;
    }
}
