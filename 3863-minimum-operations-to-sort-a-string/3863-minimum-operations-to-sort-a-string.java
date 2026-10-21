
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;
        char minChar = 'z';
        char maxChar = 'a';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            minChar = (char) Math.min(minChar, c);
            maxChar = (char) Math.max(maxChar, c);

            if (i > 0 && s.charAt(i - 1) > c) {
                sorted = false;
            }
        }

        if (sorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        // Either condition is sufficient for one operation.
        if (s.charAt(0) == minChar ||
            s.charAt(n - 1) == maxChar) {
            return 1;
        }

        // Check whether min or max occurs in the middle.
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == minChar ||
                s.charAt(i) == maxChar) {
                return 2;
            }
        }

        return 3;
    }
}
