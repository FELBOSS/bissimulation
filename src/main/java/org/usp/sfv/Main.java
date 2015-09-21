package org.usp.sfv;

import org.usp.sfv.algorithm.FlyAlgo;
import org.usp.sfv.algorithm.PartitionAlgo;
import org.usp.sfv.domain.Process;
import org.usp.sfv.reader.LTSReader;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/11/15
 */
public class Main {

    private static final String LTS_FILE1 = "lts/p.txt";
    private static final String LTS_FILE2 = "lts/r.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("--- Bissimulation Start");

        Process p1 = LTSReader.getInstance().read(LTS_FILE1);
        Process p2 = LTSReader.getInstance().read(LTS_FILE2);


        partitionAlgo(p1, p2);
        flyAlgo(p1, p2);

        System.out.println("-- Bissimulation Ended");

    }

    public static void flyAlgo(Process p1, Process p2){
        System.out.println("-- Start FlyAlgo simultaneously");
        FlyAlgo flyAlgo = new FlyAlgo(p1, p2);
        flyAlgo.calculate();
        System.out.println("-- End FlyAlgo simultaneously");
    }

    public static void partitionAlgo(Process p1, Process p2){
        System.out.println("-- Start partitionAlgo");
        PartitionAlgo partitionAlgo1 = new PartitionAlgo(p1,p2);
        partitionAlgo1.calculate();
        System.out.println("-- End partitionAlgo");

    }



}
