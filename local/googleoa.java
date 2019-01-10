class googleoa {

    static int[] res;

    public static void main(String[] args) {

        res = new int[6];
        int[] nums = new int[] { 1, 2, 1, 3 };
        System.out.println(findmax(nums));

        // int[][] dp = new int[6][6];
        // int n = nums.length;

        // for (int i = 0; i < n; i++) {
        // dp[i][i] = nums[i];
        // }
        // for (int l = 1; l <= n; l++) {
        // for (int from = 0, to = from + l - 1; to < n; from++, to++) {
        // int plus = 0;
        // int minum = from + 2 < n ? nums[from] + nums[from + 1] - dp[from + 2][to] :
        // 0;
        // int taktimes = from + 3 < n ? nums[from] + nums[from + 1] + nums[from + 2] -
        // dp[from + 3][to] : 0;
        // dp[from][to] = Math.max(takeone, Math.max(taketwo, takethree));
        // }
        // }
        // System.out.println(dp[0][5]);

    }

    private static int findmax(int[] nums) {

        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        for (int l = 1; l <= n; l++) {
            for (int from = 0, to = from + l - 1; to < n; from++, to++) {
                for (int k = from + 1; k <= to; k++) {
                    int time = dp[from][k - 1] * dp[k][to];
                    int plus = dp[from][k - 1] + dp[k][to];
                    dp[from][to] = Math.max(dp[from][to], Math.max(time, plus));
                }
            }
        }
        return dp[0][n - 1];
    }

}