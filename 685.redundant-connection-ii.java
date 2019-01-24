/*
 * @lc app=leetcode id=685 lang=java
 *
 * [685] Redundant Connection II
 *
 * https://leetcode.com/problems/redundant-connection-ii/description/
 *
 * algorithms
 * Hard (29.14%)
 * Total Accepted:    15.6K
 * Total Submissions: 53.7K
 * Testcase Example:  '[[1,2],[1,3],[2,3]]'
 *
 * 
 * In this problem, a rooted tree is a directed graph such that, there is
 * exactly one node (the root) for which all other nodes are descendants of
 * this node, plus every node has exactly one parent, except for the root node
 * which has no parents.
 * 
 * The given input is a directed graph that started as a rooted tree with N
 * nodes (with distinct values 1, 2, ..., N), with one additional directed edge
 * added.  The added edge has two different vertices chosen from 1 to N, and
 * was not an edge that already existed.
 * 
 * The resulting graph is given as a 2D-array of edges.  Each element of edges
 * is a pair [u, v] that represents a directed edge connecting nodes u and v,
 * where u is a parent of child v.
 * 
 * Return an edge that can be removed so that the resulting graph is a rooted
 * tree of N nodes.  If there are multiple answers, return the answer that
 * occurs last in the given 2D-array.
 * Example 1:
 * 
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given directed graph will be like this:
 * ⁠ 1
 * ⁠/ \
 * v   v
 * 2-->3
 * 
 * 
 * Example 2:
 * 
 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * Output: [4,1]
 * Explanation: The given directed graph will be like this:
 * 5  2
 * ⁠    ^    |
 * ⁠    |    v
 * ⁠    4 
 * 
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N
 * is the size of the input array.
 * 
 */
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return new int[2];
        }
        int[] can1 = new int[]{-1, -1};
        int[] can2 = new int[]{-1, -1};
        int[] uf = new int[edges.length + 1];
        for (int[] e : edges) {
            if (uf[e[1]] == 0) {
                uf[e[1]] = e[0];
            }
            else {
                can1 = new int[]{uf[e[1]], e[1]};
                can2 = new int[]{e[0], e[1]};
                e[1] = 0;
            }
        }

        for (int i = 0; i < uf.length; i++) {
            uf[i] = i;
        }

        for (int[] e : edges) {
            if (e[1] == 0) {
                continue;
            } 
            int a = e[0], b = e[1];
            if (find(uf, a) == b) {
                if (can1[0] == -1) {
                    return e;
                }
                return can1;
            }
            uf[b] = a;
        }
        return can2;
    }

    // int child = edges[i][1], father = edges[i][0];
    // if (root(parent, father) == child) {
    //     if (can1[0] == -1) {
    //         return edges[i];
    //     }
    //     return can1;
    // }
    // parent[child] = father;

    private int find(int[] un, int root) {
        while (root != un[root]) {
            un[root] = un[un[root]];
            root = un[root];
        }
        return root;
    }
}
