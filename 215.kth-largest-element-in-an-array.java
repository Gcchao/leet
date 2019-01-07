/*
 * @lc app=leetcode id=215 lang=java
 *
 * [215] Kth Largest Element in an Array
 *
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (44.76%)
 * Total Accepted:    298.3K
 * Total Submissions: 666.4K
 * Testcase Example:  '[3,2,1,5,6,4]\n2'
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * 
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        
        int cur = partition(0, nums.length -1, nums.length- k + 1, nums);
        return nums[cur];
    }
    private int partition(int l, int h, int k, int[] nums) {
        int piv = nums[h];
        int i = l, j = h;
        
        for (int c = l; c < h; c++) {
            if (nums[c] < piv) {
                swap(nums, i, c);
                i++;
            }
        }
        swap(nums, h, i);
        int m = i - l + 1;
        if (m == k) {
            return i;
        }
        if (m > k) {
            return partition(l, i - 1, k, nums);
        }
        else {
            return partition(i + 1, h, k - m, nums);
        }

    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
