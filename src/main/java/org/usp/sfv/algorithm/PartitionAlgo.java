package org.usp.sfv.algorithm;

import org.usp.sfv.domain.*;
import org.usp.sfv.domain.Process;
import org.usp.sfv.domain.Partition;

import java.util.*;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/14/15
 */
public class PartitionAlgo extends Algo {

    private List<Partition> W = new ArrayList<>();

    public PartitionAlgo(Process p1, Process p2) {
        super(p1, p2);
    }

    public boolean calculate(){
        System.out.println("-- PartitionAlgo P1 Start");
        Partition partition1 = calculateForProcess(p1);
        System.out.println("-- PartitionAlgo P1 End");

        System.out.println("-- PartitionAlgo P2 Start");
        Partition partition2 = calculateForProcess(p2);
        System.out.println("-- PartitionAlgo P2 End");

        if(partition1.getBlocks().size() == partition2.getBlocks().size()) {
            System.out.println("RESULT=TRUE, THE ARE EQUIVALENTS");
            return true;
        }

        System.out.println("RESULT=FALSE, NOT EQUIVALENTS");
        return false;
    }


    public Partition calculateForProcess(Process process) {

        Partition partition = new Partition();
        Block b0 = new Block(process.getStates());
        partition.addBlock(b0);


        for(String event: process.getEvents()) {
            System.out.println("Do partition for event="+event);

            //Find states where event is an outgoing edge
            Set<String> fromStates = process.getEventFromMap().get(event);
            partition = doPartition(partition, new Block(fromStates));

            System.out.println("calculate after event="+event+" partition=" + partition);
            System.out.println("W="+ W);
        }

        return partition;
    }

    public Partition doPartition(Partition partition, Block fromStates) {
        Partition newPartition = new Partition();
        for(Block block: partition.getBlocks()) {
            Partition subPartition = findSubPartition(block, fromStates);
            System.out.println("states outgoing="+fromStates+" block=" + block + " partition="+ subPartition);
            newPartition.addBlocks(subPartition.getBlocks());
            if(subPartition.getBlocks().size()>1){
                subPartition.addBlock(block);
                W.add(subPartition);
                System.out.println("splitter add partition to W="+ W);
            }
        }

        System.out.println("Partition done! partition=" + partition + "fromStates="+fromStates + " newPartition="+newPartition);

        return newPartition;
    }

    public Partition findSubPartition(Block a, Block b){
        Partition partition = new Partition();
        Block c1 = findComplement(a, b);
        Block c2 = findComplement(a, c1);
        partition.addBlock(c1);
        partition.addBlock(c2);
        //System.out.println("findSubPartition a=" + a + " b=" + b + " partition=" + partition);
        return partition;
    }


}
