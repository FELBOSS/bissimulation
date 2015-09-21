package org.usp.sfv.algorithm;

import org.usp.sfv.domain.Block;
import org.usp.sfv.domain.Process;
import org.usp.sfv.domain.fly.PartitionTuple;
import org.usp.sfv.domain.fly.Tuple;

import java.util.HashSet;
import java.util.Set;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/14/15
 */
public class FlyAlgo {

    private Process p1 = null;
    private Process p2 = null;
    //private PartitionTuple partition = null;


    public FlyAlgo(Process p1, Process p2) {
        this.p1 = p1;
        this.p2 = p2;

        //Initialize partition with tuple composed by all states of both process

    }

    public void calculate() {

        PartitionTuple partition = new PartitionTuple();;
        Tuple tuple = new Tuple(new Block(p1.getStates()), new Block(p2.getStates()));
        partition.addTuple(tuple);

        for(String event: p1.getEvents()) {
            System.out.println("calculate for event="+event);

            //Find states where event is an outgoing edge
            Block fromStates1 = new Block(p1.getEventFromMap().get(event));
            Block fromStates2 = new Block(p2.getEventFromMap().get(event));


            try {
                partition = doPartition(partition, fromStates1, fromStates2);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("calculate after event="+event+" partition=" + partition);
        }
        System.out.println("RESULT=TRUE, PROCESS EQUIVALENTS");
        System.out.println("----- ALGORITHM END");
    }

    private PartitionTuple doPartition(PartitionTuple partition, Block fromStates1, Block fromStates2) throws Exception {

        PartitionTuple newPartition = new PartitionTuple();
        for(Tuple tuple: partition.getTuples()) {
            Tuple booleanTuple1 = findBlocksDiff(tuple.getB1(), fromStates1);
            Tuple booleanTuple2 = findBlocksDiff(tuple.getB2(), fromStates2);

            Tuple hasEdge = new Tuple(booleanTuple1.getB1(), booleanTuple2.getB1());
            Tuple noEdge = new Tuple(booleanTuple1.getB2(), booleanTuple2.getB2());

            if(!isEquivalent(hasEdge) || !isEquivalent(noEdge)){
                throw new Exception("RESULT=FAlSE, PROCESS NOT EQUIVALENTS");
            }
            newPartition.addTuple(hasEdge);
            newPartition.addTuple(noEdge);
        }
        return newPartition;
    }

    private boolean isEquivalent(Tuple tuple) {
        if(tuple.getB1().notEmpty() == tuple.getB2().notEmpty()) {
            return true;
        }
        return false;
    }
    //    public Partition doPartition(Partition partition, Block fromStates) {
//        Partition newPartition = new Partition();
//        for(Block block: partition.getBlocks()) {
//            Partition subPartition = findSubPartition(block, fromStates);
//            newPartition.addBlocks(subPartition.getBlocks());
//            if(subPartition.getBlocks().size()>1){
//                subPartition.addBlock(block);
//                W.add(subPartition);
//                System.out.println("SPLIT W=" + W);
//            }
//        }
//
//        System.out.println("doPartition partition=" + partition + "fromStates="+fromStates + " newPartition="+newPartition);
//
//        return newPartition;
//    }

    /**
     * Block b1 contains states of block a present block b
     * Block b2 contains states of block a not present in block b
     */
    public Tuple findBlocksDiff(Block a, Block b){
        Block c1 = findComplement(a, b);
        Block c2 = findComplement(a, c1);
        Tuple tuple = new Tuple(c2, c1);
        System.out.println("findBlocksDiff a=" + a + " b=" + b+ " tuple="+tuple);
        return tuple;
    }

    public Block findComplement(Block a, Block b){
        Set<String> complement = new HashSet<>(a.getStates());
        complement.removeAll(b.getStates());
        //System.out.println("findComplement a=" + a + " b=" + b+ " complement="+complement);
        return new Block(complement);
    }

}
