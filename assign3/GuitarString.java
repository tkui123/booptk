/*
 * GuitarString.java
 *
 */

package assign3;
import java.lang.Math;

public class GuitarString extends InstString{

    private final double sampleRate = 44100.0;
    
    public GuitarString(){};
 
    public GuitarString(double frequency) {
	int n = (int)(Math.round(sampleRate / frequency));
	rb = new RingBuffer(n);

	//for (int i = 0; i < n; i++) {
	//rb.enqueue(0.0);
	    //}
    }

    public GuitarString(double[] init) {
        rb = new RingBuffer(init.length);

	for (double item : init) {
	    rb.enqueue(item);
	}
    }
   
    public void pluck() {	
	for (int i = 0; i < rb.getSize(); i++) {
	    double randNum = Math.random() - 0.5;
	    rb.enqueue(randNum);
	}
    }
   
    public void tic() {
	double num1 = rb.dequeue();
	double num2 = rb.peek();

	double lpfVal = ((num1 + num2) / 2) * 0.996;
	rb.enqueue(lpfVal);
	
	ticCounter++;
    }
}
