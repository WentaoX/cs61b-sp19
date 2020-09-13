/**
 * Created by hug.
 */
import java.lang.Math;


public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        // depth d of full optimal trees could have 1 + 2 + ... + 2^d = 2^(d+1) - 1
        // log base is 2. The depth cannot be negative
        int depth = (int) Math.ceil(Math.log(N+1) / Math.log(2)) - 1;
        // IPL： The sum I over all internal (circular) nodes of the paths from the root of an extended binary tree to
        // each node
        int IPL = 0;
        for (int d = 0; d < depth; d++) {
            IPL += Math.pow(2, d) * d;
        }
        // for the last depth, is is not necessary full, check how many nodes left in total.
        // the above depth - 1 level have nodes of Math.pow(2, depth) - 1
        int nodesLeftInLastLevel = N - (int) (Math.pow(2, depth) - 1);
        IPL += nodesLeftInLastLevel * depth;
        return IPL;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) / N;
    }
}
