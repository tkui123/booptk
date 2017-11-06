/*
 * RingBuffer.java
 *
 * "I pledge my honor that I have abided by the Stevens Honor System."
 *
 * Tyler Kui (c) 2017
 *
 */

package assign3;

import java.util.Arrays;

/*
 * Implements a ring buffer
 *
 * @author    Tyler Kui
 * @version   1.0
 * @since     20171104
 *
 */
public class RingBuffer {
    private int first;            // index of first item in buffer
    private int last;             // index of last item in buffer
    private int size;             // current number of items of buffer
    private double[] buffer;

    // create an empty buffer, with given max capacity
    public RingBuffer(int capacity) {
	buffer = new double[capacity];
	size = 0;
	first = 0;
	last = 0;
    }

    /* 
     * Returns number of items currently in the buffer.
     *
     * @return   The integer variable size that represents the number of items in the buffer.
     *
     */
    public int getSize() {
	return size;
    }

    /*
     * Sets the value of size, which represents the number of items in the buffer.
     *
     * @param   num   desired size of the buffer.
     * 
     */
    public void setSize(int num) {
	size = num;
    }

    /*
     * Tells the user is the buffer is empty.
     *
     * @return   true if the variable size is 0, otherwise false.
     *
     */
    public boolean isEmpty() {
	return size == 0 ? true : false;
    }

     /*
     * Tells the user is the buffer is full.
     *
     * @return   true if the variable size is equal to the length of the buffer array, otherwise false.
     *
     */
    public boolean isFull() {
	return size == buffer.length ? true : false;
    }

    /*
     * Enqueues/adds item x to the end of the buffer. This method also adds 1 to the last and size variable.
     *
     * @param   x   type double to be added after the last non-empty element of the buffer.
     *
     */
    public void enqueue(double x) {
        if (isFull()) { throw new RuntimeException("Ring buffer overflow"); }
	buffer[last] = x;
	
	last++;
	size++;

	if (last > buffer.length - 1)
	    last = 0;
    }

    /*
     * Dequeues/removes item x at the beginning of the buffer. This method also adds 1 to the first variable 
     * and subtracts 1 from the size variable.
     *
     * @return   the element that is dequeued
     *
     */
    public double dequeue() {
        if (isEmpty()) { throw new RuntimeException("Ring buffer underflow"); }
	double temp = buffer[first];
	buffer[first] = 0.0;
	
	first++;
	size--;

	if (first > buffer.length - 1)
	    first = 0;
	
	return temp;
    }

    /*
     * Returns the value of the first element of the buffer at index "first".
     *
     * @return   value of the first element of the buffer at index "first".
     *
     */
    public double peek() {
	//debug();
        if (isEmpty()) { throw new RuntimeException("Ring buffer underflow"); }
	return buffer[first];
    }

    /*
     * Used to debug, which prints the variables first, last, size, and array buffer
     *
     */
    public void debug() {
	System.out.println("First: " + first);
	System.out.println("Last: " + last);
	System.out.println("Size: " + getSize());
	System.out.println(Arrays.toString(buffer));
    }

    /*
     * Main method to test whether the ring buffer functions properly.
     *
     * @param   args   command-line arguments read in as strings.
     *
     */
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(N);
        for (int i = 1; i <= N; i++) {
            buffer.enqueue(i);
        }
	//buffer.debug();
		double t = buffer.dequeue();
		buffer.enqueue(t);
		System.out.println("Size after wrap-around is " 
				   + buffer.getSize());
		//buffer.debug();
		//System.out.println("WHILE LOOP STARTING!");
        while (buffer.getSize() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
	    //buffer.debug();
        }
        System.out.println(buffer.peek());
	//buffer.debug();
    }
}



