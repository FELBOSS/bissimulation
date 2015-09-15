package org.usp.sfv;

import org.usp.sfv.domain.Process;
import org.usp.sfv.reader.LTSReader;
import org.usp.sfv.reader.PartitionAlgo;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/11/15
 */
public class Main {

    private static final String LTS_FILE = "lts/p1.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Bissimulation Start");

        Process p1 = LTSReader.getInstance().read(LTS_FILE);
        PartitionAlgo.getInstance().calculate(p1);

        System.out.println("Bissimulation Ended");

    }



}
