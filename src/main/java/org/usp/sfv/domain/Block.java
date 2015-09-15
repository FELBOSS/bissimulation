package org.usp.sfv.domain;

import java.util.*;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/11/15
 */
public class Block {

    private Set<String> states = new HashSet<>();

    public Block(Set<String> states) {
        this.states = states;
    }

    public Set<String> getStates() {
        return states;
    }

    public void setStates(Set<String> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "Block{" +
            "states=" + states +
            '}';
    }
}
