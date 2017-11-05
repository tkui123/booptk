/*
 * PianoString.java
 *
 */

package assign3;
import java.lang.Math;

public class PianoString extends InstString {

    private final double sampleRate = 44100.0;
    
    public PianoString(double frequency) {
	int n = (int)(Math.round(sampleRate / frequency));
	rb = new RingBuffer(n);

	for (int i = 0; i < n; i++) {
	    rb.enqueue(0.0);
	}
	//rb.debug();
    }

    public PianoString(double[] init) {
	rb = new RingBuffer(init.length);

	for (double item : init) {
	    rb.enqueue(item);
	}
    }
   
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
   
    public void tic() {	
	double num1 = rb.dequeue();
	double num2 = rb.peek();

	double lpfVal = ((num1 + num2) / 2) * 0.996;
	rb.enqueue(lpfVal);
	
	ticCounter++;
    }

}
