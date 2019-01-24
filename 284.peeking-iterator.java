import java.util.Iterator;

/*
 * @lc app=leetcode id=284 lang=java
 *
 * [284] Peeking Iterator
 *
 * https://leetcode.com/problems/peeking-iterator/description/
 *
 * algorithms
 * Medium (38.62%)
 * Total Accepted:    68K
 * Total Submissions: 176.1K
 * Testcase Example:  '["PeekingIterator","next","peek","next","next","hasNext"]\n[[[1,2,3]],[],[],[],[],[]]'
 *
 * Given an Iterator class interface with methods: next() and hasNext(), design
 * and implement a PeekingIterator that support the peek() operation -- it
 * essentially peek() at the element that will be returned by the next call to
 * next().
 * 
 * Example:
 * 
 * 
 * Assume that the iterator is initialized to the beginning of the list:
 * [1,2,3].
 * 
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after
 * that still return 2. 
 * You call next() the final time and it returns 3, the last element. 
 * Calling hasNext() after that should return false.
 * 
 * 
 * Follow up: How would you extend your design to be generic and work with all
 * types, not just integer?
 * 
 */
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {


// 	就是实现iterator的再封装,加入一个peek()方法,这个peek()相当于next()方法,如果执行了peek方法,
//则next方法返回的值和刚才peek过返回的值一致(即不再取下一个值了)
// hasNext()方法返回是否后面还有元素
	boolean ispeek;
	int peekval;
	Iterator<Integer> iterator;
	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iterator = iterator;
		peekval = 0;
		ispeek = false;
	    
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if (ispeek) {
			return peekval;
		}
		else {
			ispeek = true;
			peekval = iterator.next();
		}
		return peekval;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if (ispeek) {
			ispeek = false;
			return peekval;
		}
		else {
			return iterator.next();
		}
	}

	@Override
	public boolean hasNext() {
	    return ispeek || iterator.hasNext();
	}
}
