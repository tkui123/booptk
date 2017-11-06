/*
 * InstString.java
 *
 * "I pledge my honor that I have abided by the Stevens Honor System."
 *
 * Tyler Kui (c) 2017
 *
 */

package assign3;

/* 
 * Abstract class InstString
 *
 * @author    Tyler Kui
 * @version   1.0
 * @since     20171104
 *
 */

public abstract class InstString {

    protected RingBuffer rb;
    protected int ticCounter = 0;
    
    /*
     * Abstract method pluck: plays the note
     */
    public abstract void pluck();
    
    /*
     * Abstract method tic: rings notes (it lets them continue playing before dissipating completely)
     */
    public abstract void tic();

    /*
     * Returns front value of the ring buffer rb
     *
     * @return   front value of ring buffer rb
     *
     */
    public double sample(){
	return rb.peek();
    }

    /*
     * Returns the number of times tic() has been called
     *
     * @return   number of times tic() has been called (ticCounter)
     *
     */
    public int time(){
	return ticCounter;
    }

}
