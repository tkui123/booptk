/*
 * DrumString.java
 *
 * "I pledge my honor that I have abided by the Stevens Honor System."
 *
 * Tyler Kui (c) 2017
 *
 */

package assign3;
import java.lang.Math;

/* 
 * Implements DrumString class
 *
 * @author    Tyler Kui
 * @version   1.0
 * @since     20171104
 *
 */

public class DrumString extends InstString{

    private final double sampleRate = 44100.0;

    /*
     * Constructor #1 for DrumString
     *
     * @param   frequency   fundamental frequency of vibration
     *
     */
    public DrumString(double frequency) {
	int n = (int)(Math.round(sampleRate / frequency));
	rb = new RingBuffer(n);

	for (int i = 0; i < n; i++) { // enqueues all values of n into ring buffer
	    rb.enqueue(0.0);
	}
	//rb.debug();
    }

    /*
     * Constructor #2 for DrumString
     *
     * @param   init   array of elements to enqueue into ring buffer
     *
     */
    public DrumString(double[] init) {
	rb = new RingBuffer(init.length);

	for (double item : init) { // enqueues all values of init array into ring buffer
	    rb.enqueue(item);
	}
    }

    /*
     * "Plucks" the string, generating random values based off if Math.sin(i) is negative or positive.
     * if Math.sin(i) is positive, enqueue the value 1.
     * Otherwise, enqueue the value -1.
     *
     */
    public void pluck() {
	int arrSize = rb.getSize();
	rb.setSize(0);
	for (int i = 0; i < arrSize; i++) {
	    //rb.debug();

	    double value = 0.0;
	    
	    if (Math.sin(i) > 0)
		value = 1.0;
	    else
		value = -1.0;
	    
	    rb.enqueue(value);
	}
	//rb.debug();
    }

    /*
     * Rings the string's note using the following conditions:
     * if (!(Math.random() < 0.5 * (1 - 0.6))) ; enqueue num1
     * if (Math.random() < 0.5 * 0.6) ; enqueue the original lpfVal * -1;
     * otherwise enqueue the default lpfVal (((num1 + num2) / 2) * 0.996)
     *
     */
    public void tic() {
	double num1 = rb.dequeue();
	double num2 = rb.peek();

	double lpfVal = ((num1 + num2) / 2) * 0.996;

	if (!(Math.random() < 0.5 * (1 - 0.6)))
	    lpfVal = num1;

	if (Math.random() < 0.5 * 0.6)
	    lpfVal *= -1;
	
	rb.enqueue(lpfVal);
	
	ticCounter++;
    }
}
