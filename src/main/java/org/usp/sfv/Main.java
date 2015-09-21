package org.usp.sfv;

import org.usp.sfv.algorithm.PartitionAlgo;
import org.usp.sfv.domain.Process;
import org.usp.sfv.reader.LTSReader;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/11/15
 */
public class Main {

    private static final String LTS_FILE = "lts/exemplo.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Bissimulation Start");

        Process p1 = LTSReader.getInstance().read(LTS_FILE);
        PartitionAlgo partitionAlgo = new PartitionAlgo(p1);
        partitionAlgo.calculate();

        System.out.println("Bissimulation Ended");


    }



}
