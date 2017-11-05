/*
 * RockBand.java
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

public class RockBand {

    public static void playFromFile(String fileName) throws IOException {
	String content;
	boolean songNameFound = false;
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

	for (String part : content.split("\\s+")) {
	    //System.out.println(part);
	    note = part;
	    
	    if (!songNameFound) {
		if (part.substring(0, 2).equals("@@")) {
		    System.out.println("NOW PLAYING - " + part.substring(2));
		    songNameFound = true;
		    continue;
		}
	    } else if (part.length() > 1) {
	        if (part.substring(0, 2).equals("##")) {
		    speed = Integer.valueOf(part.substring(2));
		    continue;
		} else if (part.substring(0, 2).equals("LL")) {
		    bassToggle = !bassToggle;
		    note = part.substring(2);
		}
	    }

	    for (int i = 0; i < note.length(); i++) {
		char key = note.charAt(i);
		System.out.println(key);

		if (bassToggle) {
		    if (key == '/') {
			bassToggle = false;
			continue;
		    }
		}
			
		int guitarBassIndex = guitarBassKeyboard.indexOf(key);
		int pianoIndex = pianoKeyboard.indexOf(key);
		int drumIndex = drumKeyboard.indexOf(key);

		if (!(key == '/')) {
		    if (guitarBassIndex > -1) {
			if (!bassToggle) {
			    guitar.playNote(guitarBassIndex);
			} else {
			    //System.out.println("Hi");
			    System.out.println(key);
			    bass.playNote(guitarBassIndex);

			}
		    }
		 
		    else if (pianoIndex > -1) { piano.playNote(pianoIndex); }

		    else if (drumIndex > -1) { drum.playNote(drumIndex); }

		    for (int j = 0; j <= speed; j++) {
			StdAudio.play(guitar.ringNotes() + bass.ringNotes() +  piano.ringNotes() + drum.ringNotes());
		    }
		}
	    }
	}
	
    }
    
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
