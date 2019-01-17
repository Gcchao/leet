/*
 * @lc app=leetcode id=310 lang=java
 *
 * [310] Minimum Height Trees
 *
 * https://leetcode.com/problems/minimum-height-trees/description/
 *
 * algorithms
 * Medium (29.49%)
 * Total Accepted:    56.9K
 * Total Submissions: 192.8K
 * Testcase Example:  '4\n[[1,0],[1,2],[1,3]]'
 *
 * For an undirected graph with tree characteristics, we can choose any node as
 * the root. The result graph is then a rooted tree. Among all possible rooted
 * trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list
 * of their root labels.
 * 
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1. You will be
 * given the number n and a list of undirected edges (each edge is a pair of
 * labels).
 * 
 * You can assume that no duplicate edges will appear in edges. Since all edges
 * are undirected, [0, 1] is the same as [1, 0] and thus will not appear
 * together in edges.
 * 
 * Example 1 :
 * 
 * 
 * Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 * 
 * ⁠       0
 * ⁠       |
 * ⁠       1
 * ⁠      / \
 * ⁠     2   3 
 * 
 * Output: [1]
 * 
 * 
 * Example 2 :
 * 
 * 
 * Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 * 
 * ⁠    0  1  2
 * ⁠     \ | /
 * ⁠       3
 * ⁠       |
 * ⁠       4
 * ⁠       |
 * ⁠       5 
 * 
 * Output: [3, 4]
 * 
 * Note:
 * 
 * 
 * According to the definition of tree on Wikipedia: “a tree is an undirected
 * graph in which any two vertices are connected by exactly one path. In other
 * words, any connected graph without simple cycles is a tree.”
 * The height of a rooted tree is the number of edges on the longest downward
 * path between the root and a leaf.
 * 
 * 
 */
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        if (n <= 0) {
            return res;
        }
        if (n == 1) {
            res.add(0);
            return res;
        }
        for (int[] e : edges) {
            if (!map.containsKey(e[0])) {
                map.put(e[0], new ArrayList<>());
            }
            if (!map.containsKey(e[1])) {
                map.put(e[1], new ArrayList<>());
            }
            map.get(e[0]).add(e[1]);
            map.get(e[1]).add(e[0]);
        }
        int[] ind = new int[n];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            ind[i] = map.get(i).size();
            if (ind[i] == 1) {
                q.add(i);
            }
        }
        while (n > 2) {
            int size = q.size();
            n -= size;
            for (int i = 0; i < size; i++) {
                int key = q.poll();
                for (int cur : map.get(key)) {
                    ind[cur]--;
                    if (ind[cur] == 1) {
                        q.add(cur);
                    }
                }
            }
        }
        res.addAll(q);
        return res;

    }
}
