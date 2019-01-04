import java.util.Map;

/*
 * @lc app=leetcode id=890 lang=java
 *
 * [890] Find and Replace Pattern
 *
 * https://leetcode.com/problems/find-and-replace-pattern/description/
 *
 * algorithms
 * Medium (69.55%)
 * Total Accepted:    16.6K
 * Total Submissions: 23.8K
 * Testcase Example:  '["abc","deq","mee","aqq","dkd","ccc"]\n"abb"'
 *
 * You have a list of words and a pattern, and you want to know which words in
 * words matches the pattern.
 * 
 * A word matches the pattern if there exists a permutation of letters p so
 * that after replacing every letter x in the pattern with p(x), we get the
 * desired word.
 * 
 * (Recall that a permutation of letters is a bijection from letters to
 * letters: every letter maps to another letter, and no two letters map to the
 * same letter.)
 * 
 * Return a list of the words in words that match the given pattern. 
 * 
 * You may return the answer in any order.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a ->
 * m, b -> e, ...}. 
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a
 * permutation,
 * since a and b map to the same letter.
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= words.length <= 50
 * 1 <= pattern.length = words[i].length <= 20
 * 
 * 
 * 
 */
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0 || pattern == null || pattern.length() == 0) {
            return res;
        }
        for (String w : words) {
            if (ispattern(w, pattern)) {
                res.add(w);
            }
        }
        return res;
    }
    private boolean ispattern(String s, String p) {
        Map<Character, Character> map = new HashMap<>();
        if (s.length() != p.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(p.charAt(i))) {
                if (map.get(p.charAt(i)) != s.charAt(i)) {
                    return false;
                }
            }
            else {
                if (map.containsValue(s.charAt(i))) {
                    return false;
                }
                map.put(p.charAt(i), s.charAt(i));
            }
        }
        return true;
    }
}
