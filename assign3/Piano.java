/*
 * Piano.java
 *
 * "I pledge my honor that I have abided by the Stevens Honor System."
 *
 * Tyler Kui (c) 2017
 *
 */

package assign3;

/* 
 * Implements the piano
 *
 * @author    Tyler Kui
 * @version   1.0
 * @since     20171104
 *
 */
public class Piano extends Instrument{

    private static InstString[][] pStrings; //2D array of strings

    /*
     * Constructor for Piano
     *
     * @param   numNotes   the number of notes that Piano can play
     *
     */
    public Piano(int numNotes){
	pStrings = new InstString[numNotes][3];
	
	for (int i = 0; i < numNotes; i++) {
	    pStrings[i][0] = new PianoString(440.0 * Math.pow(2, (i - 24)/12.0));
	    pStrings[i][1] = new PianoString((440.0 * Math.pow(2, (i - 24)/12.0)) + 0.45);
	    pStrings[i][2] = new PianoString((440.0 * Math.pow(2, (i - 24)/12.0)) - 0.45);
	}

    }

    /*
     * Plucks 3 PianoString object
     *
     * @param   index   index for row in 2d array pStrings
     *
     */
    public void playNote(int index){
	 pStrings[index][0].pluck();
	 pStrings[index][1].pluck();
	 pStrings[index][2].pluck();
    }

    /*
     * Lets the notes ring (method override customization for piano)
     *
     * @return   the sum of the row of pStrings array's sample() returns
     *
     */
    public double ringNotes(){
	double sum = 0;
	
	for (InstString[] item : pStrings) {
	    item[0].tic();
	    sum += item[0].sample();
	    item[1].tic();
	    sum += item[1].sample();
	    item[2].tic();
	    sum += item[2].sample();
	    
	}

	return sum;
    }
			    

}
