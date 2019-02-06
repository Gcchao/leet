/*
 * @lc app=leetcode id=480 lang=java
 *
 * [480] Sliding Window Median
 *
 * https://leetcode.com/problems/sliding-window-median/description/
 *
 * algorithms
 * Hard (31.23%)
 * Total Accepted:    20.6K
 * Total Submissions: 66K
 * Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3'
 *
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * Examples: 
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5 
 * 
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k
 * numbers in the window. Each time the sliding window moves right by one
 * position. Your job is to output the median array for each window in the
 * original array.
 * 
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * 
 * 
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * ⁠1 [3  -1  -3] 5  3  6  7       -1
 * ⁠1  3 [-1  -3  5] 3  6  7       -1
 * ⁠1  3  -1 [-3  5  3] 6  7       3
 * ⁠1  3  -1  -3 [5  3  6] 7       5
 * ⁠1  3  -1  -3  5 [3  6  7]      6
 * 
 * 
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 * 
 * Note: 
 * You may assume k is always valid, ie: k is always smaller than input array's
 * size for non-empty array.
 */
class Solution {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;
    public double[] medianSlidingWindow(int[] nums, int k) {
        left = new PriorityQueue<Integer>((a, b) -> {
            return b - a;
        });
        right = new PriorityQueue<Integer>();
        double[] res = new double[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            right.add(num);
            left.add(right.poll());
            if (left.size() > right.size()) {
                right.add(left.poll());
            }

            if (i - k + 1 >= 0) {
                if (k % 2 == 0) {
                    res[i - k + 1] =(double) ((long)right.peek() + (long)left.peek())/2;
                }
                else {
                    res[i - k + 1] = (double)right.peek();
                }
                int toremove = nums[i - k + 1];
                if (toremove < right.peek()) {
                    left.remove(toremove);
                }
                else {
                    right.remove(toremove);
                }
                

            }
        }
        return res;

    }
}
                            // 0               1       2           3                                 7
// -2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648]\n3'
// -2147483648.0,-2147483648.0,-2147483648.0,-2147483648.0,-2147483648.0,-2147483648.0,2147483647.0,2147483647.0,-2147483648.0,-2147483648.0,-2147483648.0]
// -2147483648.0,-2147483648.0,-2147483648.0,-2147483648.0,-2147483648.0,2147483647.0,2147483647.0,2147483647.0,2147483647.0,2147483647.0,-2147483648.0]
// × stdout:
    // int num = nums[i];
    //         // 让maxHeap始终保存小于一半的值，minHeap保存大于一半的，正好两半
    //         if( maxHeap.size() == 0 || maxHeap.peek() >= num)
    //             maxHeap.add(num);
    //         else minHeap.add(num);
    //         // 维护两个堆，保证两个堆得大小，要么保持一致（偶数时），要么maxHeap多一个（奇数时）
    //         if( minHeap.size() > maxHeap.size() )
    //             maxHeap.add(minHeap.poll());
    //         if( maxHeap.size() > minHeap.size() + 1 )
    //             minHeap.add(maxHeap.poll());
    //         // 如果需要输出
    //         if ( i-k+1 >=0 ){
    //             if( k % 2 == 1 )
    //                 res[i- k + 1] = maxHeap.peek();
    //             else 
    //                 res[i- k + 1] = (maxHeap.peek()/2.0 + minHeap.peek()/2.0); // 小心溢出
    //             //移除并更新
    //             int toBeRemove = nums[i - k + 1];
    //             if( toBeRemove <= maxHeap.peek())
    //                 maxHeap.remove(toBeRemove);
    //             else minHeap.remove(toBeRemove);
    //             // 维护两个堆，保证两个堆得大小，要么保持一致（偶数时），要么maxHeap多一个（奇数时）
    //             if( minHeap.size() > maxHeap.size() )
    //                 maxHeap.add(minHeap.poll());
    //             if( maxHeap.size() > minHeap.size() + 1 )
    //                 minHeap.add(maxHeap.poll());

    //         }
    //     }
    //     return res;

