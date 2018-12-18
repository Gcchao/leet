/*
 * [457] Circular Array Loop
 *
 * https://leetcode.com/problems/circular-array-loop/description/
 *
 * algorithms
 * Medium (26.02%)
 * Total Accepted:    11.6K
 * Total Submissions: 44.6K
 * Testcase Example:  '[2, -1, 1, 2, 2]'
 *
 * You are given an array of positive and negative integers. If a number n at
 * an index is positive, then move forward n steps. Conversely, if it's
 * negative (-n), move backward n steps. Assume the first element of the array
 * is forward next to the last element, and the last element is backward next
 * to the first element. Determine if there is a loop in this array. A loop
 * starts and ends at a particular index with more than 1 element along the
 * loop. The loop must be "forward" or "backward'.
 * 
 * Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0
 * -> 2 -> 3 -> 0.
 * 
 * Example 2: Given the array [-1, 2], there is no loop.
 * 
 * Note: The given array is guaranteed to contain no element "0".
 * 
 * Can you do it in O(n) time complexity and O(1) space complexity?
 * 
 */
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = i, fast = getindex(nums, i);
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[getindex(nums, fast)] > 0) {
                if (fast == slow) {
                    if (fast == getindex(nums, fast)) {
                        break;
                        // check circle
                    }
                    return true;
                }
                slow = getindex(nums, slow);
                fast = getindex(nums, getindex(nums, fast));
            }

            int val = nums[i];
            int j = i;
            while (val * nums[j] > 0) {
                nums[j] = 0;
                j = getindex(nums, j);
            }
        }
        return false;
    }

    private int getindex(int[] nums, int i) {
        return nums[i] + i >= 0 ? (nums[i] + i) % nums.length : nums.length + (nums[i] + i) % nums.length;
    }
}
