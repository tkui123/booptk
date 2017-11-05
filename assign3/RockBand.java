/*
 * RockBand.java
 *
 */

package assign3;
import cos126.StdDraw;
import cos126.StdAudio;
import java.io.IOException;
import java.util.ArrayList;

public class RockBand {
    
    public static void main(String[] args) {
	Guitar guitar = new Guitar(37);
	Piano piano = new Piano(37);
	Drum drum = new Drum(21);
	
	String guitarBassKeyboard = "`1234567890-=qwertyuiop[]\\asdfghjkl;'";
	String pianoKeyboard = "~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"";
	String drumKeyboard = "ZXCVBNM<>?zxcvbnm,./ ";

	while (true) {
	     if (StdDraw.hasNextKeyTyped()) {
                 char key = StdDraw.nextKeyTyped();
		 int index;

		 index = guitarBassKeyboard.indexOf(key);
                 if (index > -1) { guitar.playNote(index); }

		 index = pianoKeyboard.indexOf(key);
                 if (index > -1) { piano.playNote(index); }

		 index = drumKeyboard.indexOf(key);
		 if (index > -1) { drum.playNote(index); }
             }

	     StdAudio.play(guitar.ringNotes() + piano.ringNotes() + drum.ringNotes());
	
	}
	
    }
}
