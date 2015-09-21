package org.usp.sfv.domain.fly;

import java.util.HashSet;
import java.util.Set;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/11/15
 */
public class PartitionTuple {

    private Set<Tuple> tuples = new HashSet<>();

    public PartitionTuple() {
    }

    public Set<Tuple> getTuples() {
        return tuples;
    }

    public void addTuple(Tuple tuple) {
        if(tuple.getB1().notEmpty() && tuple.getB2().notEmpty()) {
            this.tuples.add(tuple);
        }
    }

    public void removeTuple(Tuple tuple) {
        this.tuples.remove(tuple);
    }

    @Override
    public String toString() {
        return "PartitionTuple{" +
            "tuples=" + tuples +
            '}';
    }
}
