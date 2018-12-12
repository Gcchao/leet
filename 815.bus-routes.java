/*
 * @lc app=leetcode id=815 lang=java
 *
 * [815] Bus Routes
 *
 * https://leetcode.com/problems/bus-routes/description/
 *
 * algorithms
 * Hard (37.02%)
 * Total Accepted:    12.7K
 * Total Submissions: 34.4K
 * Testcase Example:  '[[1,2,7],[3,6,7]]\n1\n6'
 *
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th
 * bus repeats forever. For example if routes[0] = [1, 5, 7], this means that
 * the first bus (0-th indexed) travels in the sequence
 * 1->5->7->1->5->7->1->... forever.
 * 
 * We start at bus stop S (initially not on a bus), and we want to go to bus
 * stop T. Travelling by buses only, what is the least number of buses we must
 * take to reach our destination? Return -1 if it is not possible.
 * 
 * 
 * Example:
 * Input: 
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation: 
 * The best strategy is take the first bus to the bus stop 7, then take the
 * second bus to the bus stop 6.
 * 
 * 
 * Note: 
 * 
 * 
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 500.
 * 0 <= routes[i][j] < 10 ^ 6.
 * 
 * 
 */
class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> visit = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        int res = 0;
        q.add(S);
        for (int bus = 0; bus < routes.length; bus++) {
            for (int stop = 0; stop < routes[bus].length; stop++) {
                if (!map.containsKey(routes[bus][stop])) {
                    map.put(routes[bus][stop], new ArrayList<>());
                }
                map.get(routes[bus][stop]).add(bus);
            }
        }
        
        while (!q.isEmpty()) {
            int size = q.size();
            res++;
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (int bus : map.get(cur)) {
                    if (visit.contains(bus)) {
                        continue;
                    }
                    visit.add(bus);
                    for (int j = 0; j < routes[bus].length; j++) {
                        if (routes[bus][j] == T) {
                            return res;
                        }
                        q.add(routes[bus][j]);
                    }
                }
            }
        }
        return -1;
        
    
    }
}
