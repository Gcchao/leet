import java.util.Map;

/*
 * [399] Evaluate Division
 *
 * https://leetcode.com/problems/evaluate-division/description/
 *
 * algorithms
 * Medium (44.79%)
 * Total Accepted:    54.1K
 * Total Submissions: 120.7K
 * Testcase Example:  '[ ["a","b"],["b","c"] ]\n[2.0,3.0]\n[ ["a","c"],["b","c"],["a","e"],["a","a"],["x","x"] ]'
 *
 * 
 * Equations are given in the format A / B = k, where  A and B are variables
 * represented as strings, and k is a real number (floating point number).
 * Given some queries, return the answers. If the answer does not exist, return
 * -1.0.
 * 
 * Example:
 * Given  a / b = 2.0, b / c = 3.0. queries are:  a / c = ?,  b / a = ?, a / e
 * = ?,  a / a = ?, x / x = ? . return  [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * 
 * 
 * The input is:  vector<pair<string, string>> equations, vector<double>&
 * values, vector<pair<string, string>> queries , where equations.size() ==
 * values.size(), and the values are positive. This represents the equations.
 * Return  vector<double>.
 * 
 * 
 * According to the example above:
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"]
 * ]. 
 * 
 * 
 * 
 * The input is always valid. You may assume that evaluating the queries will
 * result in no division by zero and there is no contradiction.
 * 
 */
class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, String> root = new HashMap<>();
        Map<String, Double> value = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String r1 = find(root, value, equations[i][0]);
            String r2 = find(root, value, equations[i][1]);
            root.put(r1, r2);
            value.put(r1, value.get(equations[i][1])*values[i]/value.get(equations[i][0]));
        }
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (!root.containsKey(queries[i][0]) || !root.containsKey(queries[i][1])) {
                res[i] = -1;
                continue;
            }
            String r1 = find(root, value, queries[i][0]);
            String r2 = find(root, value, queries[i][1]);
            if (!r1.equals(r2)) {
                res[i] = -1;
                continue;
            }
            res[i] = (double)value.get(queries[i][0]) / value.get(queries[i][1]);
        }
        return res;
    }
    private String find(Map<String, String> root, Map<String, Double> value, String s) {
        if (!root.containsKey(s)) {
            root.put(s, s);
            value.put(s, 1.0);
            return s;
        }
        String lastp = root.get(s);
        if (lastp.equals(s)) {
            return s;
        }
        String p = find(root, value, lastp);
        root.put(s, p);
        value.put(s, value.get(s) * value.get(lastp));
        return p;
    }
}
