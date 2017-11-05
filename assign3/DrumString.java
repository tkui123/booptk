/*
 * DrumString.java
 *
 */

package assign3;
import java.lang.Math;


public class DrumString extends InstString{

    private final double sampleRate = 44100.0;
   
    public DrumString(double frequency) {
	int n = (int)(Math.round(sampleRate / frequency));
	rb = new RingBuffer(n);

	for (int i = 0; i < n; i++) {
	    rb.enqueue(0.0);
	}
	//rb.debug();
    }

    public DrumString(double[] init) {
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
	    
	    if (Math.sin(i) > 0)
		value = 1.0;
	    else
		value = -1.0;
	    
	    rb.enqueue(value);
	}
	//rb.debug();
    }
    
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
