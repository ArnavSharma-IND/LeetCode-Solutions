import java.util.*;

class Solution {

    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack(n, 0, 0, new StringBuilder(), result);

        return result;
    }

    private void backtrack(
        int n,
        int open,
        int close,
        StringBuilder current,
        List<String> result
    ) {

        // Base case: all brackets have been used
        if (open == n && close == n) {
            result.add(current.toString());
            return;
        }

        // Add opening bracket
        if (open < n) {

            current.append('(');

            backtrack(n, open + 1, close, current, result);

            // Backtrack
            current.deleteCharAt(current.length() - 1);
        }

        // Add closing bracket
        if (close < open) {

            current.append(')');

            backtrack(n, open, close + 1, current, result);

            // Backtrack
            current.deleteCharAt(current.length() - 1);
        }
    }
}
