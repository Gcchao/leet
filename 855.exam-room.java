import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=855 lang=java
 *
 * [855] Exam Room
 *
 * https://leetcode.com/problems/exam-room/description/
 *
 * algorithms
 * Medium (34.93%)
 * Total Accepted:    8.9K
 * Total Submissions: 25.4K
 * Testcase Example:  '["ExamRoom","seat","seat","seat","seat","leave","seat"]\n[[10],[],[],[],[],[4],[]]'
 *
 * In an exam room, there are N seats in a single row, numbered 0, 1, 2, ...,
 * N-1.
 * 
 * When a student enters the room, they must sit in the seat that maximizes the
 * distance to the closest person.  If there are multiple such seats, they sit
 * in the seat with the lowest number.  (Also, if no one is in the room, then
 * the student sits at seat number 0.)
 * 
 * Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat()
 * returning an int representing what seat the student sat in, and
 * ExamRoom.leave(int p) representing that the student in seat number p now
 * leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have
 * a student sitting in seat p.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"],
 * [[10],[],[],[],[],[4],[]]
 * Output: [null,0,9,4,2,null,5]
 * Explanation:
 * ExamRoom(10) -> null
 * seat() -> 0, no one is in the room, then the student sits at seat number 0.
 * seat() -> 9, the student sits at the last seat number 9.
 * seat() -> 4, the student sits at the last seat number 4.
 * seat() -> 2, the student sits at the last seat number 2.
 * leave(4) -> null
 * seat() -> 5, the student sits at the last seat number 5.
 * 
 * 
 * ​​​​​​​
 * 
 * Note:
 * 
 * 
 * 1 <= N <= 10^9
 * ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times
 * across all test cases.
 * Calls to ExamRoom.leave(p) are guaranteed to have a student currently
 * sitting in seat number p.
 * 
 * 
 */
class ExamRoom {
    int N;
    PriorityQueue<Interval> pq;

    class Interval {
        int x, y, dist;

        public Interval(int x, int y) {
            this.x = x;
            this.y = y;
            if (x == -1) {
                dist = y;
            } else if (y == N) {
                dist = N - 1 - x;
            } else {
                dist = Math.abs(x - y) / 2;
            }
        }
    }

    public ExamRoom(int N) {
        pq = new PriorityQueue<>((a, b) -> {
            return a.dist != b.dist ? b.dist - a.dist : a.x - b.x;
        });
        this.N = N;
        pq.add(new Interval(-1, N));
    }

    public int seat() {
        int seat = 0;
        Interval i = pq.poll();
        if (i.x == -1) {
            seat = 0;
        } else if (i.y == N) {
            seat = N - 1;
        } else {
            seat = i.x + i.y / 2;
        }
        pq.add(new Interval(i.x, seat));
        pq.add(new Interval(seat, i.y));
        return seat;
    }

    public void leave(int p) {
        PriorityQueue<Interval> copy = new PriorityQueue<>(pq);
        Interval head = null, tail = null;
        while (!copy.isEmpty()) {
            Interval tmp = copy.poll();
            if (tmp.x == p) {
                tail = tmp;
            }
            if (tmp.y == p) {
                head = tmp;
            }
            if (head != null && tail != null) {
                break;
            }
        }
        pq.remove(head);
        pq.remove(tail);
        pq.add(new Interval(head.x, tail.y));
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such: ExamRoom obj =
 * new ExamRoom(N); int param_1 = obj.seat(); obj.leave(p);
 */
