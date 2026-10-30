
import java.util.*;

class Solution {

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        if (digits.length() == 0) {
            return result;
        }

        String[] mapping = {
            "", "", "abc", "def",
            "ghi", "jkl", "mno",
            "pqrs", "tuv", "wxyz"
        };

        backtrack(digits, 0, new StringBuilder(), mapping, result);

        return result;
    }

    private void backtrack(
        String digits,
        int index,
        StringBuilder current,
        String[] mapping,
        List<String> result
    ) {

        // Base case: all digits processed
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get letters corresponding to current digit
        int digit = digits.charAt(index) - '0';

        String letters = mapping[digit];

        // Try every possible letter
        for (int i = 0; i < letters.length(); i++) {

            current.append(letters.charAt(i));

            // Move to next digit
            backtrack(digits, index + 1, current, mapping, result);

            // Backtrack: remove last character
            current.deleteCharAt(current.length() - 1);
        }
    }
}
