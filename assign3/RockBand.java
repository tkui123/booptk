/*
 * RockBand.java
 *
 * "I pledge my honor that I have abided by the Stevens Honor System."
 *
 * Tyler Kui (c) 2017
 *
 */

package assign3;
import cos126.StdDraw;
import cos126.StdAudio;
import java.io.IOException;
import java.util.ArrayList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * Plays songs from a text file or reads in keyboard input to play instrument sounds.
 * 
 * @author    Tyler Kui
 * @version   1.0
 * @since     20171104
 *
 */
public class RockBand {

    /* Reads the notes from the text file. First, it splits up the whole file into the individual chunks.
     * First, the chunks where the song name and speed are found as indicated by "@@" and "##" respectively.
     * Afterwards, each note in each part is checked with the keyboards for the different instruments. Once
     * that is found, the note is plucked and rung.
     * 
     * @param   fileName  name of file to be read
     */
    public static void playFromFile(String fileName) throws IOException {
	String content;
	int speed = 0;
	boolean bassToggle = false;
	String note = "";

	Guitar guitar = new Guitar(37);
	Bass bass = new Bass(37);
	Piano piano = new Piano(37);
	Drum drum = new Drum(19);
	
	String guitarBassKeyboard = "`1234567890-=qwertyuiop[]\\asdfghjkl;'";
	String pianoKeyboard = "~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"";
	String drumKeyboard = "ZXCVBNM<>?zxcvbnm,.";

	content = new String(Files.readAllBytes(Paths.get(fileName)));
	//System.out.println(content);

	// gets different chunks/parts of notes in song text file
	for (String part : content.split("\\s+")) {
	    //System.out.println(part);
	    note = part;
	    
	    if (part.length() > 1) {
		if (part.substring(0, 2).equals("@@")) { // reads title
		    System.out.println(part.substring(2));
		    continue;
		}
	        if (part.substring(0, 2).equals("##")) { // reads speeds
		    speed = Integer.valueOf(part.substring(2));
		    continue;
		} else if (part.substring(0, 2).equals("LL")) { // reads for bass toggle
		    bassToggle = !bassToggle;
		    note = part.substring(2);
		}
	    }

	    // goes through individual keys for notes in each chunk/part
	    for (int i = 0; i < note.length(); i++) {
		char key = note.charAt(i);
		System.out.println(key);

		// toggles base back when '/' by itself is read
		if (bassToggle) {
		    if (key == '/') {
			bassToggle = false;
			continue;
		    }
		}
			
		int guitarBassIndex = guitarBassKeyboard.indexOf(key);
		int pianoIndex = pianoKeyboard.indexOf(key);
		int drumIndex = drumKeyboard.indexOf(key);

		// plays notes using instrument keyboards to read keys in text file
		if (!(key == '/')) {
		    if (guitarBassIndex > -1) {
			if (!bassToggle) {
			    guitar.playNote(guitarBassIndex);
			} else {
			    //System.out.println("Hi");
			    //System.out.println(key);
			    bass.playNote(guitarBassIndex);

			}
		    }
		 
		    else if (pianoIndex > -1) { piano.playNote(pianoIndex); }

		    else if (drumIndex > -1) { drum.playNote(drumIndex); }

		    // rings instrument notes
		    for (int j = 0; j <= speed; j++) {
			StdAudio.play(guitar.ringNotes() + bass.ringNotes() +  piano.ringNotes() + drum.ringNotes());
		    }
		}
	    }
	}
	
    }

    /*
     * Main method to play notes of musical instruments. Two ways to run:
     * 1. If you run the program, it will open a window, where you can use the keyboard to play notes.
     * 2. Using a command after the java run command called "-play_from_file" followed by the name of
     * the text file (i.g. "name.txt") will allow the user to play a song read off of the text file.
     *
     * @param   args   array for command line arguments.
     *
     */
    public static void main(String[] args) throws IOException{
	//System.out.println(args[0]);
	
	if (args.length == 2) {
	    //System.out.println(args[0]);
	    //System.out.println(args[1]);

	    //System.out.println(args[0].equals("-play_from_file"));
	    
	    if (args[0].equals("-play_from_file")) {
		playFromFile(args[1]);
		
	    }

	    return;
	}
	
	Guitar guitar = new Guitar(37);
	Bass bass = new Bass(37);
	Piano piano = new Piano(37);
	Drum drum = new Drum(19);
	
	String guitarBassKeyboard = "`1234567890-=qwertyuiop[]\\asdfghjkl;'";
	String pianoKeyboard = "~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"";
	String drumKeyboard = "ZXCVBNM<>?zxcvbnm,.";

	boolean bassToggle = false;

	while (true) {
	     if (StdDraw.hasNextKeyTyped()) {
                 char key = StdDraw.nextKeyTyped();
		 int index;

		 if (key == '\n')
		     bassToggle = !bassToggle;

		 //System.out.println(bassToggle);
		 
		 int guitarBassIndex = guitarBassKeyboard.indexOf(key);
		 int pianoIndex = pianoKeyboard.indexOf(key);
		 int drumIndex = drumKeyboard.indexOf(key);
		 if (guitarBassIndex > -1) {
		     if (!bassToggle) {
			 guitar.playNote(guitarBassIndex);
		     }
		     else {
			 //System.out.println("Hi");
			 //System.out.println(key);
			 bass.playNote(guitarBassIndex);

		     }
		 }
		 
                 else if (pianoIndex > -1) { piano.playNote(pianoIndex); }

		 else if (drumIndex > -1) { drum.playNote(drumIndex); }
             }

	     StdAudio.play(guitar.ringNotes() + bass.ringNotes() +  piano.ringNotes() + drum.ringNotes());
	
	}
	
    }
}
