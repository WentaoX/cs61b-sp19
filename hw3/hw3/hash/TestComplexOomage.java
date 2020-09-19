package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }


    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();
        List<Integer> params = new ArrayList<>();
        int nOmage = 1000;
        int lengthN = 10;
        int nMaxVlaue = 4;
        /* max of int is 2^32 - 1 = 256^4, if we assign 4 255, then all others keep 0, the hash should be the same*/
//        List<Integer> rangeN = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int i = 0; i < lengthN; i++) {
            if (i < nMaxVlaue) params.add(255);
            else params.add(0);
        }
        for (int i = 0; i < nOmage; i ++) {
            Collections.shuffle(params);
            ComplexOomage co = new ComplexOomage(params);
            deadlyList.add(co);
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
