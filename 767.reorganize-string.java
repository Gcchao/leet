import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=767 lang=java
 *
 * [767] Reorganize String
 *
 * https://leetcode.com/problems/reorganize-string/description/
 *
 * algorithms
 * Medium (40.12%)
 * Total Accepted:    16.8K
 * Total Submissions: 41.9K
 * Testcase Example:  '"aab"'
 *
 * Given a string S, check if the letters can be rearranged so that two
 * characters that are adjacent to each other are not the same.
 * 
 * If possible, output any possible result.  If not possible, return the empty
 * string.
 * 
 * Example 1:
 * 
 * 
 * Input: S = "aab"
 * Output: "aba"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: S = "aaab"
 * Output: ""
 * 
 * 
 * Note:
 * 
 * 
 * S will consist of lowercase letters and have length in range [1, 500].
 * 
 * 
 * 
 * 
 */
class Solution {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        int len = S.length();
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > (len + 1) / 2) {
                return "";
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[1] - a[1];
        });
        for (char c : map.keySet()) {
            pq.add(new int[]{c, map.get(c)});
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != first[0]) {
                sb.append((char)first[0]);
                if (--first[1] > 0) {
                    pq.add(first);
                }
            }
            else {
                int[] second = pq.poll();
                sb.append((char)second[0]);
                if (--second[1] > 0) {
                    pq.add(second);
                }
                pq.add(first);
            }
        }
        return sb.toString();
    }
}
