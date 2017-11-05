/*
 * InstString.java
 *
 */

package assign3;

public abstract class InstString {

    protected RingBuffer rb;
    protected int ticCounter = 0;
    
    /* To be implemented by subclasses*/
    public abstract void pluck();
    public abstract void tic();

    public double sample(){
	return rb.peek();
    }

    public int time(){
	return ticCounter;
    }

}
