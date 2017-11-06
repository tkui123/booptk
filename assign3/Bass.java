/*
 * Bass.java
 *
 * "I pledge my honor that I have abided by the Stevens Honor System."
 *
 * Tyler Kui (c) 2017
 *
 */

package assign3;

/* 
 * Implements Bass class
 *
 * @author    Tyler Kui
 * @version   1.0
 * @since     20171104
 *
 */

public class Bass extends Instrument{

    /*
     * Constructor for Bass class
     *
     * @param   numNotes   number of notes for number of strings to be created
     *
     */
    public Bass(int numNotes){
	strings = new InstString[numNotes];
	
	for (int i = 0; i < numNotes; i++) {
	    strings[i] = new GuitarString(440.0 * Math.pow(2, (i - 48)/12.0));
	}
    }		    
}
