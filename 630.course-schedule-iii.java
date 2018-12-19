class Solution {
    public int scheduleCourse(int[][] courses) {
        if (courses.length == 0 || courses == null) {
            return 0;
        }
        int[] s = new int[courses.length];
        int[] e = new int[courses.length];
        for (int i = 0; i < courses.length; i++) {
            s[i] = course[i][0];
            s[i] = course[i][1];
        }
        Arrays.sort(s);
        Arrays.sort(e);
        int res = 0;
        for (int i = 0; i < s.length; i++) {
            if (i == s.length - 1 || s[i + 1] >= e[i]) {
                res++;
            }
        }
        return res;
    }
}
