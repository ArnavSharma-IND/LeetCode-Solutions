class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set=new HashSet<>();
        int prev=0,c=0;
        for(int i=0;i<s.length();i++){
            while(set.contains(s.charAt(i))){
                set.remove(s.charAt(prev));
                prev++;
            }
            set.add(s.charAt(i));
            c=Math.max(c,i-prev+1);
        }
        return c;
    }
}