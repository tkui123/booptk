/*
 * Instrument.java
 */
package assign3;

public abstract class Instrument {
    
    protected InstString[] strings;
    
    public void playNote(int i){
	strings[i].pluck();
    }

    public double ringNotes(){
	double sum = 0;
	
	for (InstString item : strings) {
	    item.tic();
	    sum += item.sample();
	    
	}

	return sum;
    }

}
