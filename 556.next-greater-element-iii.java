/*
 * @lc app=leetcode id=556 lang=java
 *
 * [556] Next Greater Element III
 *
 * https://leetcode.com/problems/next-greater-element-iii/description/
 *
 * algorithms
 * Medium (29.59%)
 * Total Accepted:    22.4K
 * Total Submissions: 75.7K
 * Testcase Example:  '12'
 *
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit
 * integer which has exactly the same digits existing in the integer n and is
 * greater in value than n. If no such positive 32-bit integer exists, you need
 * to return -1.
 * 
 * Example 1:
 * 
 * 
 * Input: 12
 * Output: 21
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 21
 * Output: -1
 * 
 * 
 * 
 * 
 */
class Solution {
    public int nextGreaterElement(int n) {
        char[] nums = (n + "").toCharArray();
        int e = nums.length - 2;
        while (e >= 0 && nums[e] - '0' >= nums[e + 1] - '0') {
            e--;
        }
        if (e == -1) {
            return -1;
        }
        else {
            int smal = nums.length - 1;
            while (nums[smal] - '0' <= nums[e] - '0') {
                smal--;
            }
            swap(nums, e, smal);
            reverse(nums, e + 1, nums.length - 1);
        }
        long val = Long.parseLong(new String(nums));
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;
    }
    private void swap(char[] nums, int a, int b) {
        char tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    private void reverse(char[] nums, int s, int e) {
        while (s < e) {
            char tmp = nums[s];
            nums[s] = nums[e];
            nums[e] = tmp;
            s++;
            e--;
        }
    }
}
