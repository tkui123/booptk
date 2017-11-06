/*
 * GuitarString.java
 *
 * "I pledge my honor that I have abided by the Stevens Honor System."
 *
 * Tyler Kui (c) 2017
 *
 */

package assign3;
import java.lang.Math;

/* 
 * Implements GuitarString class
 *
 * @author    Tyler Kui
 * @version   1.0
 * @since     20171104
 *
 */

public class GuitarString extends InstString{

    private final double sampleRate = 44100.0;

    /*
     * Default Guitar String constructor
     *
     */
    public GuitarString(){};

    /*
     * Constructor #1 for GuitarString
     *
     * @param   frequency   fundamental frequency of vibration
     *
     */
    public GuitarString(double frequency) {
	int n = (int)(Math.round(sampleRate / frequency));
	rb = new RingBuffer(n);

	for (int i = 0; i < n; i++) {
	    rb.enqueue(0.0);
	}
	//rb.debug();
    }

    /*
     * Constructor #1 for GuitarString
     *
     * @param   init   array of elements to enqueue into ring buffer
     *
     */
    public GuitarString(double[] init) {
        rb = new RingBuffer(init.length);

	for (double item : init) {
	    rb.enqueue(item);
	}
    }

    /*
     * "Plucks" the string, generating random values from -0.5 to 0.5 in the ring buffer's array
     *
     */
    public void pluck() {
	int arrSize = rb.getSize();
	rb.setSize(0);
	for (int i = 0; i < arrSize; i++) {
	    //rb.debug();
	    double randNum = Math.random() - 0.5;
	    rb.enqueue(randNum);
	}
	//rb.debug();
    }
   
    public void tic() {
	double num1 = rb.dequeue();
	double num2 = rb.peek();

	double lpfVal = ((num1 + num2) / 2) * 0.996;
	rb.enqueue(lpfVal);
	
	ticCounter++;
    }
}
