class Solution {
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> pq = new LinkedList<>();
        Queue<int[]> aq = new LinkedList<>();
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            pq.add(new int[]{i, 0});
            aq.add(new int[]{i, n - 1});
            pacific[i][0] = true;
            atlantic[i][n - 1] = true;
        }
        for (int i = 0; i < n; i++) {
            pq.add(new int[]{0, i});
            aq.add(new int[]{m - 1, i});
            pacific[0][i] = true;
            atlantic[m - 1][i] = true;
        }
        bfs(pq, pacific, matrix);
        bfs(aq, atlantic, matrix);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }
        return res;
    }
    private void bfs(Queue<int[]> q, boolean[][] visited, int[][] matrix) {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] d : dir) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];
                if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || matrix[cur[0]][cur[1]] > matrix[x][y] || visited[x][y]) {
                    continue;
                }
                q.add(new int[]{x, y});
                visited[x][y] = true;
            }
        }
    }
}
