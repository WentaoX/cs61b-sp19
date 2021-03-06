import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.SwingWrapper;


/**
 * Created by hug.
 */
public class Experiments {
    private static int targetSize = 8000;
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

    public static void getInitialized (BST<Integer> bst, int treeNodeN) {
        RandomGenerator rg = new RandomGenerator();
        /* Initialize tree */
        while (bst.size() < treeNodeN) {
            int next = rg.getRandomInt(1000000);
            if (!bst.contains(next)) {
                bst.add(next);
            }
        }
    }


    public static void experiment2() {
        RandomGenerator rg = new RandomGenerator();

        BST<Integer> bst = new BST<>();
        int treeNodeN = 3000;


        List<Integer> myBSTAvgDepth = new ArrayList<>();
        List<Integer> series = new ArrayList<>();
        getInitialized(bst, treeNodeN);

        myBSTAvgDepth.add(bst.averageDepth());
        series.add(0);

        // start to delete and add
        int repeatN = 100000;
        for (int count = 1; count <= repeatN; count++) {
            ExperimentHelper.deleteTakingSuccessor(bst);
            while (true) {
                int next = rg.getRandomInt(1000000);
                if (!bst.contains(next)) {
                    bst.add(next);
                    series.add(count);
                    myBSTAvgDepth.add(bst.averageDepth());
                    break;
                }
            }
        }

            XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("repeat delete(taking successor) and add").yAxisTitle("average depth").build();
            chart.addSeries("steps to repeat delete and add process", series, myBSTAvgDepth);

            new SwingWrapper(chart).displayChart();
    }


    public static void experiment3() {
        RandomGenerator rg = new RandomGenerator();

        BST<Integer> bst = new BST<>();
        int treeNodeN = 3000;


        List<Integer> myBSTAvgDepth = new ArrayList<>();
        List<Integer> series = new ArrayList<>();
        getInitialized(bst, treeNodeN);

        myBSTAvgDepth.add(bst.averageDepth());
        series.add(0);

        // start to delete and add
        int repeatN = 100000;
        for (int count = 1; count <= repeatN; count++) {
            ExperimentHelper.deleteTakingRandom(bst);
            while (true) {
                int next = rg.getRandomInt(100000);
                if (!bst.contains(next)) {
                    bst.add(next);
                    series.add(count);
                    myBSTAvgDepth.add(bst.averageDepth());
                    break;
                }
            }
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("repeat delete(taking random) and add").yAxisTitle("average depth").build();
        chart.addSeries("steps to repeat delete and add process", series, myBSTAvgDepth);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment1();
        experiment2();
        experiment3();

        }
}
