package hw3.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;


public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();

        int[] numInBucket = new int[M];
        for (Oomage s : oomages) {
            int bucketNumber = (s.hashCode() & 0x7FFFFFFF) % M;
            numInBucket[bucketNumber] += 1;
        }

        Arrays.sort(numInBucket);
        int minInBucket = numInBucket[0];
        int maxInBucket = numInBucket[M-1];

        return  minInBucket >= N/50 && (maxInBucket <= N/2.5);
    }
}
