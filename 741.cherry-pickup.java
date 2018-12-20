class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n + 1][n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        dp[1][1][1] = grid[0][0];
        for (int x1 = 1; x1 <= n; x1++) {
            for (int y1 = 1; y1 <= n; y1++) {
                for (int x2 = 1; x2 <= n; x2++) {
                    int y2 = x1 + y1 - x2;
                    if (dp[x1][y1][x2] > 0 || y2 < 1 || y2 > n || grid[x1 - 1][y1 - 1] == -1 || grid[x2 - 1][y2 - 1] == -1) {
                        continue;
                    }
                    int cur = Math.max(Math.max(dp[x1 - 1][y1][x2 -1], dp[x1 - 1][y1][x2]), Math.max(dp[x1][y1 - 1][x2 - 1], dp[x1][y1 - 1][x2]));
                    if (cur < 0) {
                        continue;
                    }
                    if (x1 == x2) {
                        dp[x1][y1][x2] = cur + grid[x1 - 1][y1 - 1];
                    }
                    else {
                        dp[x1][y1][x2] = cur + grid[x1 - 1][y1 - 1] + grid[x2 - 1][y2 - 1];

                    }
                }
            }
        }
        return dp[n][n][n] > 0 ? dp[n][n][n] : 0; 
    }
}
