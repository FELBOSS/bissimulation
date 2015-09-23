package org.usp.sfv.algorithm;

import org.usp.sfv.domain.Block;
import org.usp.sfv.domain.Process;
import org.usp.sfv.domain.fly.PartitionTuple;
import org.usp.sfv.domain.fly.Tuple;

import java.util.Set;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/14/15
 */
public class FlyAlgo extends Algo {

    public FlyAlgo(Process p1, Process p2) {
        super(p1, p2);
    }

    public boolean calculate() {

        //Initialize partition with tuple composed by all states of both process
        PartitionTuple partition = new PartitionTuple();
        Tuple tuple = new Tuple(new Block(p1.getStates()), new Block(p2.getStates()));
        partition.addTuple(tuple);

        try {
            partition = doRefinement(partition, p1.getEvents());
        }
        catch (Exception e) {
            return false;
        }

        PartitionTuple partitionNotChecked = partition;
        System.out.println("All partitions done! Now we have to check if this is the stable partition="+partition);

        try {
            partition = doRefinement(partition, p1.getEvents());
        }
        catch (Exception e) {
            return false;
        }

        System.out.println("FINAL partitionChecked    ="+partition);
        System.out.println("FINAL partitionNotChecked ="+partitionNotChecked);

        System.out.println("RESULT=TRUE, THEY ARE EQUIVALENTS");
        return  true;
    }

    private PartitionTuple doRefinement(PartitionTuple partition, Set<String> events) throws Exception {
        for(String event: events) {

            try {
                partition = doPartition(partition, event);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("RESULT=FALSE, NOT EQUIVALENTS");
                throw e;
            }
            System.out.println("calculate after event=" + event + " partition=" + partition);
        }
        return partition;
    }

    private PartitionTuple doPartition(PartitionTuple partition, String event) throws Exception {
        System.out.println("Do partition for event="+event);

        //Find states where event is an outgoing edge
        Block fromStates1 = new Block(p1.getEventFromMap().get(event));
        Block fromStates2 = new Block(p2.getEventFromMap().get(event));

        PartitionTuple newPartition = new PartitionTuple();
        for(Tuple tuple: partition.getTuples()) {
            Tuple edgeTuple1 = findBlocksDiff(tuple.getB1(), fromStates1);
            Tuple edgeTuple2 = findBlocksDiff(tuple.getB2(), fromStates2);

            System.out.println("(P1) states outgoing="+fromStates1+" block=" + tuple.getB1() + " hasEdge=" + edgeTuple1.getB1() + " noEdge=" +
                edgeTuple1.getB2());

            System.out.println("(P2) states outgoing="+fromStates2+" block=" + tuple.getB2() + " hasEdge=" + edgeTuple2.getB1() + " noEdge=" +
                edgeTuple2.getB2());

            Tuple hasEdge = new Tuple(edgeTuple1.getB1(), edgeTuple2.getB1());
            Tuple noEdge = new Tuple(edgeTuple1.getB2(), edgeTuple2.getB2());

            if(!isEquivalent(hasEdge) || !isEquivalent(noEdge)){
                throw new Exception("Not Equivalent at step partition="+partition + " fromStates1="+fromStates1 + " fromStates2="+fromStates2);
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

    /**
     * Block b1 contains states of block a present block b
     * Block b2 contains states of block a not present in block b
     */
    public Tuple findBlocksDiff(Block a, Block b){
        Block c1 = findComplement(a, b);
        Block c2 = findComplement(a, c1);
        Tuple tuple = new Tuple(c2, c1);
        //System.out.println("findBlocksDiff a=" + a + " b=" + b + " tuple=" + tuple);
        return tuple;
    }

}
