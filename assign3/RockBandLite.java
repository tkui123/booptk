
  package assign3;
  import cos126.StdDraw;
  import cos126.StdAudio;

  public class RockBandLite {
      public static void main(String[] args) {

          // create two guitar strings, for concert A and C
          double CONCERT_A = 440.0; 
          double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);
          PianoString stringA = new PianoString(CONCERT_A);
          PianoString stringC = new PianoString(CONCERT_C);

          while (true) {

              // check if the user has typed a key; if so, process it   
              if (StdDraw.hasNextKeyTyped()) {
                  char key = StdDraw.nextKeyTyped();
                  if      (key == 'a') { stringA.pluck(); }
                  else if (key == 'c') { stringC.pluck(); }
              }

              // compute the superposition of samples
              double sample = stringA.sample() + stringC.sample();
  
              // send the result to the sound card
              StdAudio.play(sample);
  
              // advance the simulation of each guitar string by one step   
              stringA.tic();
              stringC.tic();
          }
       }
  }
