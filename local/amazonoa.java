import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Point {
    int x;
    int y;

    Point(int a, int b) {
        x = a;
        y = b;
    }
}

class amazonoa {

    public static void main(String[] args) {
        Integer[][] nums1 = new Integer[][] { { 1, 0, 0, 0, 9 }, { 0, 0, 1, 1, 1 }, { 1, 0, 1, 0, 0 },
                { 1, 1, 1, 0, 0 } };

        Integer[][] nums2 = new Integer[][] { { 1, 0 }, { 2, 0 }, { 1, 1 }, { 0, 0 }, { 0, 1 } };
        List<List<Integer>> list = new ArrayList<>();
        for (Integer[] nums : nums2) {
            list.add(Arrays.asList(nums));
        }
        ;
        findmink(list, 3);
        // System.out.println(findmin(list));
    }

    private static int findmin(List<List<Integer>> list) {
        if (list == null || list.size() == 0) {
            return -1;
        }
        int[][] dp = new int[list.size()][list.get(0).size()];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                if (list.get(i).get(j) == 9) {
                    dfs(list, dp, i, j);
                }
            }
        }
        return dp[0][0];
    }

    private static int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    private static void dfs(List<List<Integer>> list, int[][] dp, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { i, j });
        dp[i][j] = 0;
        boolean[][] visit = new boolean[dp.length][dp[0].length];
        visit[i][j] = true;
        int lvl = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int[] tmp = q.poll();
                int tmpx = tmp[0];
                int tmpy = tmp[1];
                for (int[] d : dir) {
                    int nx = tmpx + d[0];
                    int ny = tmpy + d[1];
                    if (nx < 0 || nx >= dp.length || ny < 0 || ny >= dp[0].length || list.get(nx).get(ny) == 0
                            || visit[nx][ny]) {
                        continue;
                    }
                    visit[nx][ny] = true;
                    dp[nx][ny] = lvl;
                    q.add(new int[] { nx, ny });
                    System.out.println(nx + "+" + ny);
                }
            }
            lvl++;
        }
    }

    private static void findmink(List<List<Integer>> nums, int k) {
        Point origin = new Point(0, 0);
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> {
            int dis = (a.x * a.x + a.y * a.y) - (b.x * b.x + b.y * b.y);
            if (dis == 0) {
                return a.x == b.x ? a.y - b.y : a.x - b.x;
            } else {
                return dis;
            }
        });

        for (List<Integer> list : nums) {
            pq.add(new Point(list.get(0), list.get(1)));
        }
        while (k > 0 && !pq.isEmpty()) {
            Point tmp = pq.poll();
            k--;
            System.out.println(tmp.x + "++++++" + tmp.y);
        }
        return;
    }

}
