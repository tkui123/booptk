/*
 * PianoString.java
 *
 * "I pledge my honor that I have abided by the Stevens Honor System."
 *
 * Tyler Kui (c) 2017
 *
 */
package assign3;
import java.lang.Math;

/* 
 * Implements the piano string
 *
 * @author    Tyler Kui
 * @version   1.0
 * @since     20171104
 *
 */
public class PianoString extends InstString {
    
    private final double sampleRate = 44100.0;

    /*
     * Constructor #1 for PianoString class. Enqueues n 0.0s into PianoString's ring buffer.
     *
     * @param   frequency   fundamental frequency of vibration
     *
     */
    public PianoString(double frequency) {
	int n = (int)(Math.round(sampleRate / frequency));
	rb = new RingBuffer(n);

	for (int i = 0; i < n; i++) { // enqueues all values of n into ring buffer
	    rb.enqueue(0.0);
	}
	//rb.debug();
    }

    /*
     * Constructor #2 for PianoString class. Enqueues n 0.0s into PianoString's ring buffer.
     *
     * @param   init   array type double containing values for buffer array
     *
     */
    public PianoString(double[] init) {
	rb = new RingBuffer(init.length);

	for (double item : init) { // enqueues all values of init array into ring buffer
	    rb.enqueue(item);
	}
    }

    /*
     * Replace the n elements in buffer array with values generated frm the following function:
     * for x values where (7/16 * n) >= x >= (9/16 * n), enqueue "0.25 * (sin(8 * pi * ((x / n) - (7 / 16))))" 
     * into buffer. Otherwise enqueue 0.0 into buffer.
     *
     */
    public void pluck() {
	int arrSize = rb.getSize();
	rb.setSize(0);
	for (int i = 0; i < arrSize; i++) {
	    //rb.debug();

	    double value = 0.0;
	    
	    if ((i >= ((7.0 / 16) * (double) arrSize)) && (i <= ((9.0 / 16) * (double) arrSize))) {
		value = 0.25 * (Math.sin(8 * Math.PI * ((i / (double) arrSize) - (7.0 / 16))));
	    }
	    
	    rb.enqueue(value);
	}
	//rb.debug();
    }

    /*
     *
     * Applies Karplus-Strong update (Low-Pass-Filter)
     *
     */
    public void tic() {	
	double num1 = rb.dequeue();
	double num2 = rb.peek();

	double lpfVal = ((num1 + num2) / 2) * 0.996;
	rb.enqueue(lpfVal);
	
	ticCounter++;
    }

}
