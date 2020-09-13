import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.SwingWrapper;


/**
 * Created by hug.
 */
public class Experiments {
    private static int targetSize = 10;
//    insert 5000 random items into a BST, and make plot of average depth
    public static void experiment1() {
        BST<Integer> bst = new BST<>();

        List<Integer> myBSTAvgDepth = new ArrayList<>();
        List<Integer> optiBSTAvgDepth = new ArrayList<>();
        List<Integer> nNodes = new ArrayList<>();

        RandomGenerator rg = new RandomGenerator();
        int size = 0;
        while (bst.size() < targetSize) {
            int next = rg.getRandomInt(100000);
            if (!bst.contains(next)) {
                size ++;
                bst.add(next);
                nNodes.add(size);
                optiBSTAvgDepth.add(ExperimentHelper.optimalIPL(size));
                myBSTAvgDepth.add(bst.averageDepth());
            }
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("nodes number").yAxisTitle("average depth").build();
        chart.addSeries("optimal avg depth", nNodes, optiBSTAvgDepth);
        chart.addSeries("my avg depth", nNodes, myBSTAvgDepth);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
        experiment1();
    }
}
