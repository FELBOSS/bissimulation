package org.usp.sfv.algorithm;

import org.usp.sfv.domain.*;
import org.usp.sfv.domain.Process;
import org.usp.sfv.domain.partition.Partition;

import java.util.*;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/14/15
 */
public class PartitionAlgo {

    private Process process = null;
    private List<Partition> W = new ArrayList<>();
    private Partition partition = new Partition();

    public PartitionAlgo(Process process) {
        this.process = process;
    }

    public void calculate() {
        System.out.println("----- ALGORITHM STEP 1");

        Block b0 = new Block(process.getStates());
        partition.addBlock(b0);


        for(String event: process.getEvents()) {

            //Find states where event is an outgoing edge
            Set<String> fromStates = process.getEventFromMap().get(event);
            partition = doPartition(partition, new Block(fromStates));



            //partition by event

//            Set<String> complement = findComplement(b0, fromStates);
//
//
//            partition.addBlock(new Block(fromStates));
//            partition.addBlock(new Block(complement));

            //W.add(new Block(fromStates));
//            W.add(new Block(complement));
            System.out.println("calculate after event="+event+" partition=" + partition);
            System.out.println("W="+ W);
        }

        System.out.println("----- ALGORITHM STEP 2");



        System.out.println("----- ALGORITHM END");
    }

    public Partition doPartition(Partition partition, Block fromStates) {
        Partition newPartition = new Partition();
        for(Block block: partition.getBlocks()) {
            Partition subPartition = findSubPartition(block, fromStates);
            newPartition.addBlocks(subPartition.getBlocks());
            if(subPartition.getBlocks().size()>1){
                subPartition.addBlock(block);
                W.add(subPartition);
                System.out.println("SPLIT W=" + W);
            }
        }

        System.out.println("doPartition partition=" + partition + "fromStates="+fromStates + " newPartition="+newPartition);

        return newPartition;
    }

    public Partition findSubPartition(Block a, Block b){
        Partition partition = new Partition();
        Block c1 = findComplement(a, b);
        Block c2 = findComplement(a, c1);
        partition.addBlock(c1);
        partition.addBlock(c2);
        System.out.println("findSubPartition a=" + a + " b=" + b+ " partition="+partition);
        return partition;
    }

    public Block findComplement(Block a, Block b){
        Set<String> complement = new HashSet<>(a.getStates());
        complement.removeAll(b.getStates());
        //System.out.println("findComplement a=" + a + " b=" + b+ " complement="+complement);
        return new Block(complement);
    }

}
