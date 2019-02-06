/*
 * @lc app=leetcode id=783 lang=java
 *
 * [783] Minimum Distance Between BST Nodes
 *
 * https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/
 *
 * algorithms
 * Easy (49.55%)
 * Total Accepted:    25.5K
 * Total Submissions: 51.4K
 * Testcase Example:  '[4,2,6,1,3,null,null]'
 *
 * Given a Binary Search Tree (BST) with the root node root, return the minimum
 * difference between the values of any two different nodes in the tree.
 * 
 * Example :
 * 
 * 
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 * 
 * The given tree [4,2,6,1,3,null,null] is represented by the following
 * diagram:
 * 
 * ⁠         4
 * ⁠       /   \
 * ⁠     2      6
 * ⁠    / \    
 * ⁠   1   3  
 * 
 * while the minimum difference in this tree is 1, it occurs between node 1 and
 * node 2, also between node 3 and node 2.
 * 
 * 
 * Note:
 * 
 * 
 * The size of the BST will be between 2 and 100.
 * The BST is always valid, each node's value is an integer, and each node's
 * value is different.
 * 
 * 
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int dif = Integer.MAX_VALUE;
    TreeNode pre = null;
    public int minDiffInBST(TreeNode root) {
        helper(root);
        return dif;
    }
    public void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        if (pre != null) {
            dif = Math.min(Math.abs(root.val - pre.val), dif);
        }
        pre = root;
        helper(root.right);
    }
}
