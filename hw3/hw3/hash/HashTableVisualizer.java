package hw3.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        /* After getting your simpleOomages to spread out
           nicely, be sure to try
           scale = 0.5, N = 2000, M = 100. */

        double scale = 1;
        int N = 200;
        int M = 10;

        HashTableDrawingUtility.setScale(scale);
        List<Oomage> oomies = new ArrayList<>();
        boolean normalTest = true;

        /* this is the original test */
        if (normalTest == true) {
            for (int i = 0; i < N; i += 1) {
                //            oomies.add(SimpleOomage.randomSimpleOomage());
                oomies.add(ComplexOomage.randomComplexOomage());
            }
        } else {
            /* this part is for the purpose of visualization hashcode problems in complexOmage */
            int nMaxValue = 6;
            List<Integer> params = new ArrayList<>();
            int lengthN = 10;

            for (int i = 0; i < lengthN; i++) {
                if (i < nMaxValue) params.add(255);
                else params.add(0);
            }
            for (int i = 0; i < N; i++) {
                Collections.shuffle(params);
                ComplexOomage co = new ComplexOomage(params);
                oomies.add(co);
            }
        }
        visualize(oomies, M, scale);
    }

    public static void visualize(List<Oomage> oomages, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);
        int[] numInBucket = new int[M];
        for (Oomage s : oomages) {
            int bucketNumber = (s.hashCode() & 0x7FFFFFFF) % M;
            double x = HashTableDrawingUtility.xCoord(numInBucket[bucketNumber]);
            numInBucket[bucketNumber] += 1;
            double y = HashTableDrawingUtility.yCoord(bucketNumber, M);
            s.draw(x, y, scale);
        }
    }
} 
