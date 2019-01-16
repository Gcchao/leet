/*
 * @lc app=leetcode id=229 lang=java
 *
 * [229] Majority Element II
 *
 * https://leetcode.com/problems/majority-element-ii/description/
 *
 * algorithms
 * Medium (30.95%)
 * Total Accepted:    89.2K
 * Total Submissions: 288.1K
 * Testcase Example:  '[3,2,3]'
 *
 * Given an integer array of size n, find all elements that appear more than ⌊
 * n/3 ⌋ times.
 * 
 * Note: The algorithm should run in linear time and in O(1) space.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,2,3]
 * Output: [3]
 * 
 * Example 2:
 * 
 * 
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 * 
 */
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int c1 = 0, c2 = 0, count1 = 0, count2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == c1) {
                count1++;
            }
            else if (nums[i] == c2) {
                count2++;
            }
            else if (count1 == 0) {
                c1 = nums[i];
                count1 = 1;
            }
            else if (count2 == 0) {
                c2 = nums[i];
                count2 = 1;
            }
            else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
		    return new ArrayList<Integer>();
        for (int num : nums) {
            if (num == c1) {
                count1++;
            }
            if (num == c2) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            res.add(c1);
        }
        if (count2 > nums.length / 3 && c1 != c2) {
            res.add(c2);
        }
        return res;
    }
}
