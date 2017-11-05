/*****************************************************************************
 *
 *  This is a template file for RingBuffer.java. It lists the constructors and
 *  methods you need, along with descriptions of what they're supposed to do.
 *  
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 *****************************************************************************/
package assign3;

import java.util.Arrays;

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

    // return number of items currently in the buffer
    public int getSize() {
	return size;
    }

    public void setSize(int num) {
	size = num;
    }

    // is the buffer empty (size equals zero)?
    public boolean isEmpty() {
	return size == 0 ? true : false;
    }

    // is the buffer full (size equals array capacity)?
    public boolean isFull() {
	return size == buffer.length ? true : false;
    }

    // add item x to the end
    public void enqueue(double x) {
        if (isFull()) { throw new RuntimeException("Ring buffer overflow"); }
	buffer[last] = x;
	
	last++;
	size++;

	if (last > buffer.length - 1)
	    last = 0;
    }

    // delete and return item from the front
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

    // return (but do not delete) item from the front
    public double peek() {
	//debug();
        if (isEmpty()) { throw new RuntimeException("Ring buffer underflow"); }
	return buffer[first];
    }

    /*public void debug() {
	System.out.println("First: " + first);
	System.out.println("Last: " + last);
	System.out.println("Size: " + getSize());
	System.out.println(Arrays.toString(buffer));
	}*/

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



