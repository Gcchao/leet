import java.util.Map;

/*
 * @lc app=leetcode id=686 lang=java
 *
 * [686] Repeated String Match
 *
 * https://leetcode.com/problems/repeated-string-match/description/
 *
 * algorithms
 * Easy (31.27%)
 * Total Accepted:    52.8K
 * Total Submissions: 168.7K
 * Testcase Example:  '"abcd"\n"cdabcdab"'
 *
 * Given two strings A and B, find the minimum number of times A has to be
 * repeated such that B is a substring of it. If no such solution, return -1.
 * 
 * For example, with A = "abcd" and B = "cdabcdab".
 * 
 * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a
 * substring of it; and B is not a substring of A repeated two times
 * ("abcdabcd").
 * 
 * Note:
 * The length of A and B will be between 1 and 10000.
 * 
 */
class Solution {
    public int repeatedStringMatch(String A, String B) {
        int res = 0;
        String cur = "";
        while (cur.length() < B.length()) {
            cur += A;
            res++;
        }
        if (cur.contains(B)) {
            return res;
        }
        cur += A;
        return cur.contains(B) ? res + 1 : -1;

    }
}
