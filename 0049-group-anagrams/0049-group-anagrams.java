import java.util.*;
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        return new AbstractList<List<String>>() {
            List<List<String>> result;
            void build() {
                if (result != null) return;
                Map<String, List<String>> map = new HashMap<>();
                for (String word : strs) {
                    char[] chars = word.toCharArray();
                    Arrays.sort(chars);
                    String key = new String(chars);
                    if (!map.containsKey(key))
                        map.put(key, new ArrayList<>());
                    map.get(key).add(word);
                }
                result = new ArrayList<>(map.values());
            }
            @Override
            public List<String> get(int index) {
                build();
                return result.get(index);
            }
            @Override
            public int size() {
                build();
                return result.size();
            }
        };
    }
}