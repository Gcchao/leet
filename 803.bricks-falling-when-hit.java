/*
 * [821] Bricks Falling When Hit
 *
 * https://leetcode.com/problems/bricks-falling-when-hit/description/
 *
 * algorithms
 * Hard (25.56%)
 * Total Accepted:    6.6K
 * Total Submissions: 25.6K
 * Testcase Example:  '[[1,0,0,0],[1,1,1,0]]\n[[1,0]]'
 *
 * We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick
 * will not drop if and only if it is directly connected to the top of the
 * grid, or at least one of its (4-way) adjacent bricks will not drop.
 * 
 * We will do some erasures sequentially. Each time we want to do the erasure
 * at the location (i, j), the brick (if it exists) on that location will
 * disappear, and then some other bricks may drop because of that erasure.
 * 
 * Return an array representing the number of bricks that will drop after each
 * erasure in sequence.
 * 
 * 
 * Example 1:
 * Input: 
 * grid = [[1,0,0,0],[1,1,1,0]]
 * hits = [[1,0]]
 * Output: [2]
 * Explanation: 
 * If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop.
 * So we should return 2.
 * 
 * 
 * Example 2:
 * Input: 
 * grid = [[1,0,0,0],[1,1,0,0]]
 * hits = [[1,1],[1,0]]
 * Output: [0,0]
 * Explanation: 
 * When we erase the brick at (1, 0), the brick at (1, 1) has already
 * disappeared due to the last move. So each erasure will cause no bricks
 * dropping.  Note that the erased brick (1, 0) will not be counted as a
 * dropped brick.
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The number of rows and columns in the grid will be in the range [1,
 * 200].
 * The number of erasures will not exceed the area of the grid.
 * It is guaranteed that each erasure will be different from any other erasure,
 * and located inside the grid.
 * An erasure may refer to a location with no brick - if it does, no bricks
 * drop.
 * 
 * 
 */
class Solution {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        if (grid == null || grid.length == 0) {
            return new int[]{};
        }
        int m = grid.length, n = grid[0].length;
        UnionFind un = new UnionFind(m * n + 1);
        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) {
                grid[hit[0]][hit[1]] = 2;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    unionaround(i, j, grid, un);
                }
            }
        }
        int[] res = new int[hits.length];
        int count = un.size[un.find(0)];
        for (int i = hits.length - 1; i >= 0; i--) {
            int[] hit = hits[i];
            if (grid[hit[0]][hit[1]] == 2) {
                unionaround(hit[0], hit[1], grid, un);
                grid[hit[0]][hit[1]] = 1;
            }


            int newsize = un.size[un.find(0)];
            res[i] = newsize - count > 0 ? newsize - count - 1 : 0;
            count = newsize;
        }
        return res;
    }
    public void unionaround(int i, int j, int[][] grid, UnionFind un) {
        int m = grid.length, n = grid[0].length;
        int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,-1}, {0,1}};
        for (int[] d : dirs) {
            int nx = i + d[0];
            int ny = j + d[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            if (grid[nx][ny] == 1) {
                un.union(nx * n + ny + 1, i * n + j + 1);
            }
        }
        if (i == 0) {
            un.union(i * n + j + 1, 0);
        }
    }
    class UnionFind{
        int[] id;
        int[] size;
        public UnionFind(int x) {
            id = new int[x];
            size = new int[x];
            for (int i = 0; i < x; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }
        public int find(int root) {
            while (id[root] != root) {
                id[root] = id[id[root]];
                root = id[root];
            }
            return root;
        }
        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa != pb) {
                id[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }
}


// 主要思路是反向unionfind。
// 具体的做法是首先遍历一遍hits数组，把所有要打的砖块位置打掉
// （标记成2）， 然后对所有剩下的砖块做四个方向的union（上下左右），
// 所有与上边界相邻的点都留下，统计一下剩下的个数，
// 这个个数就是打掉所有砖块后一定会剩下的个数。 
// 然后我们再倒序便利一遍hits数组，把后面要打的先复原回原来的board，
// 对复原的点坐四个方向的union，这时候得到的size就是所有必须通过这块
// 砖才能保证不掉的砖块数量，用这个数量减去原先计算的一定会剩下的个数
// 再减去1（打掉的这块）就是这次打后剩下的数量。完成整个遍历就得到
// 了result数组。
// 代码如下：

// class Solution {
//     public int[] hitBricks(int[][] grid, int[][] hits) {
//         int m = grid.length;
//         int n = grid[0].length;
// 				//这里的 + 1主要是多一个0来表示顶，所有的第一排的砖在unionfind的时候都会直接与这个0相连。
//         UnionFind uf = new UnionFind(m * n + 1);
//         //首先把所有要打的砖块标记为2.
//         for (int[]hit : hits) {
//             if (grid[hit[0]][hit[1]] == 1) {
//                 grid[hit[0]][hit[1]] = 2;
//             }
//         }
//         //然后对打掉后的数组中的砖块进行四个方向的union
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (grid[i][j] == 1) {
//                     unionAround(i, j, grid, uf);
//                 }
//             }
//         }
// 				//这个count就是打完后一定会剩下的砖块数量.
//         int count = uf.size[uf.find(0)];
//         int[] res = new int[hits.length];
// 			//反向遍历hits数组，一个一个复原回board
//         for (int i = hits.length - 1; i >= 0; i--) {
//             int[] hit = hits[i];
//             if (grid[hit[0]][hit[1]] == 2) {
// 						   // 对于需要复原的这个砖块做四个方向union，主要是为了得到有多少砖必须通过这块砖才能连接到顶部。
//                 unionAround(hit[0], hit[1], grid, uf);
// 								//由于是从后向前，做完要把这块砖重新标记回来
//                 grid[hit[0]][hit[1]] = 1;
//             }
// 						//newSize就是复原这块砖之后，有多少与顶部相连的砖块.
//             int newSize = uf.size[uf.find(0)];
// 						// 打掉当前砖块会掉的数量就是  复原后的数量- 开始时的数量 - 1 （本身）
//             res[i] = (newSize - count > 0) ? newSize - count - 1 : 0;
// 						//由于是从后向前，所以下次循环时，这个砖还没掉，更新count。
//             count = newSize;
//         }
//         return res;
//     }
//     private void unionAround(int x, int y, int[][] grid, UnionFind uf) {
//         int m = grid.length;
//         int n = grid[0].length;
//         int[] dx = new int[] {-1, 1, 0, 0};
//         int[] dy = new int[] {0, 0, -1, 1};
//         for (int i = 0; i < 4; i++) {
//             int nextX = x + dx[i];
//             int nextY = y + dy[i];
//             if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;
//             if (grid[nextX][nextY] == 1) {
//                 uf.union(x * n + y + 1, nextX * n + nextY + 1);
//             }
//         }
// 				//第一排的直接与顶相连。
//         if (x == 0) {
//             uf.union(x * n + y + 1, 0);
//         }
//     }
// }
// class UnionFind {
//     int[] id;
//     int[] size;
//     public UnionFind(int len) {
//         id = new int[len];
//         size = new int[len];
//         for (int i = 0; i < len; i++) {
//             id[i] = i;
//             size[i] = 1;
//         }
//     }
//     public int find(int toFind) {
//         while (id[toFind] != toFind) {
//             id[toFind] = id[id[toFind]];
//             toFind = id[toFind];
//         }
//         return toFind;
//     }
//     public void union(int a, int b) {
//         int rootA = find(a);
//         int rootB = find(b);
//         if (rootA != rootB) {
//             id[rootA] = rootB;
//             size[rootB] += size[rootA];
//         }
//     }
    
// }