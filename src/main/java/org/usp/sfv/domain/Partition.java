package org.usp.sfv.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/11/15
 */
public class Partition {

    private Set<Block> blocks = new HashSet<>();

    public Set<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(Set<Block> blocks) {
        this.blocks = blocks;
    }

    public void addBlock(Block block) {
        if(block.getStates().isEmpty())
            return;
        this.blocks.add(block);
    }

    public void addBlocks(Set<Block> blocks) {
        for(Block b: blocks) {
            addBlock(b);
        }
    }


    @Override
    public String toString() {
        return "Partition{" +
            "blocks=" + blocks +
            '}';
    }
}
