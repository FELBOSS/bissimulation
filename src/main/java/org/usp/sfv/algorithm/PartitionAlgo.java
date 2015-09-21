package org.usp.sfv.algorithm;

import org.usp.sfv.domain.*;
import org.usp.sfv.domain.Process;

import java.util.*;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/14/15
 */
public class PartitionAlgo {

    private static PartitionAlgo ourInstance = new PartitionAlgo();

    public static PartitionAlgo getInstance() {
        return ourInstance;
    }

    private PartitionAlgo() {
    }

    public void calculate(Process process) {

        Block b0 = new Block(process.getStates());
        List<Block> W = new ArrayList<>();
        W.add(b0);
        for(String event: process.getEvents()){

            //partition by event
            Set<String> fromStates = process.getEventFromMap().get(event);
            Set<String> complement = findComplement(b0.getStates(), fromStates);

            Partition partition = new Partition();
            partition.addBlock(new Block(fromStates));
            partition.addBlock(new Block(complement));

            W.add(new Block(fromStates));
            W.add(new Block(complement));
            System.out.println("partition=" + partition);
            System.out.println("W="+ W);
        }

    }


    private Set<String> findComplement(Set<String> a, Set<String> b){
        Set<String> complement = new HashSet<>(a);
        complement.removeAll(b);
        return complement;
    }

}
