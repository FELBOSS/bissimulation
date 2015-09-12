package org.usp.sfv;

import org.usp.sfv.reader.LTSReader;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/11/15
 */
public class Main {

    private static final String LTS_FILE = "lts/p1.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Bissimulation Start");

        LTSReader reader = new LTSReader();
        reader.read(LTS_FILE);

        System.out.println("Bissimulation Ended");

    }



}
