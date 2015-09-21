package org.usp.sfv.domain.fly;

import org.usp.sfv.domain.Block;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/11/15
 */
public class Tuple {

    private Block b1;
    private Block b2;

    public Tuple(Block b1, Block b2) {
        this.b1 = b1;
        this.b2 = b2;
    }

    public Block getB1() {
        return b1;
    }

    public void setB1(Block b1) {
        this.b1 = b1;
    }

    public Block getB2() {
        return b2;
    }

    public void setB2(Block b2) {
        this.b2 = b2;
    }

    @Override
    public String toString() {
        return "Tuple{" +
            "b1=" + b1 +
            ", b2=" + b2 +
            '}';
    }
}
