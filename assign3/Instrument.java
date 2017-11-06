/*
 * Instrument.java
 *
 * "I pledge my honor that I have abided by the Stevens Honor System."
 *
 * Tyler Kui (c) 2017
 *
 */

package assign3;

/* 
 * Abstract class Instrument
 *
 * @author    Tyler Kui
 * @version   1.0
 * @since     20171104
 *
 */

public abstract class Instrument {
    
    protected InstString[] strings;

    /*
     * Plays note by calling pluck() method of InstString object in strings[] array.
     * 
     * @param    i   index of strings array
     *
     */
    public void playNote(int i){
	strings[i].pluck();
    }

    /*
     * Rings notes by calling tic() for each string in strings array.
     *     
     * @return       sum of samples from tic() calls
     *
     */

    public double ringNotes(){
	double sum = 0;
	
	for (InstString item : strings) {
	    item.tic();
	    sum += item.sample();
	    
	}

	return sum;
    }

}
