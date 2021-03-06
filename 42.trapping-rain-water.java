/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 *
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (41.27%)
 * Total Accepted:    244K
 * Total Submissions: 591.2K
 * Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]'
 *
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * 
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped. Thanks
 * Marcos for contributing this image!
 * 
 * Example:
 * 
 * 
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * 
 */
class Solution {
    public int trap(int[] height) {
        Stack<Integer> s = new Stack<>();
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (s.isEmpty() || height[s.peek()] >= height[i]) {
                s.push(i);
            }
            else {
                int bot = s.pop();
                max += s.isEmpty() ? 0 : (Math.min(height[i], height[s.peek()]) - height[bot]) * (i - s.peek() - 1);
                i--;
            }
        }
        return max;
    }
}
