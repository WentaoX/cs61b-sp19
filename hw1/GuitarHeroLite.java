/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import es.datastructur.synthesizer.GuitarString;

import java.util.ArrayList;

public class GuitarHeroLite {
    private static final double CONCERT_A = 440.0;

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        int string_n = keyboard.length();
        GuitarString[] guitars = new GuitarString[string_n];
        for (int i=0; i<string_n; i++) {
            double concert_current = CONCERT_A * Math.pow(2, (i-24)/12);
            guitars[i] = new GuitarString(concert_current);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            int index = -1;
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                try {
                    index = keyboard.indexOf(key);
                    guitars[index].pluck();
                } catch (Exception e) { } //ignore exception
            }

            double sample = 0;
            for (int i = 0; i < string_n; i++) {
                /* compute the superposition of samples */
                //            double sample = stringA.sample() + stringC.sample();
                sample += guitars[i].sample();
            }
             /* play the sample on standard audio */
             StdAudio.play(sample);

             for (int i = 0; i < string_n; i++) {
                /* advance the simulation of each guitar string by one step */
                //            stringA.tic();
                //            stringC.tic();
                guitars[i].tic();
            }
        }
    }
}

