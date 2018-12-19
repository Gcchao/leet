
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] ingree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<>());
            ingree[i] = 0;
        }
        for (int[] pre : prerequisites) {
            map.get(pre[0]).add(pre[1]);
            ingree[pre[1]]++;
        }
        int count = 0;

        Queue<Integer> q= new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (ingree[i] == 0) {
                q.add(i);
                count++;
            }
        }
        while (!q.isEmpty()) {
            int cour = q.poll();
            for (int next : map.get(cour)) {
                if (--ingree[next] == 0) {
                    q.offer(next);
                    count++;
                }
            }
        }
        return count == numCourses;
    }
}
