package org.usp.sfv.algorithm;

import org.usp.sfv.domain.Block;
import org.usp.sfv.domain.Process;

import java.util.HashSet;
import java.util.Set;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/14/15
 */
public abstract class Algo {

    protected Process p1 = null;
    protected Process p2 = null;

    public Algo(Process p1, Process p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public abstract boolean calculate();


    public Block findComplement(Block a, Block b){
        Set<String> complement = new HashSet<>(a.getStates());
        complement.removeAll(b.getStates());
        //System.out.println("findComplement a=" + a + " b=" + b+ " complement="+complement);
        return new Block(complement);
    }

}
