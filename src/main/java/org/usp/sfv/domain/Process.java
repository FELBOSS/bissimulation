package org.usp.sfv.domain;

import java.util.*;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/11/15
 */
public class Process {

    private Set<String> states = new HashSet<>();
    private Set<String> events = new HashSet<>();


    public void addRelationship(Relationship r){
        states.add(r.getStateFrom());
        states.add(r.getStateTo());
        events.add(r.getEvent());

    }

    public void addState(String state){
        states.add(state);
    }

    public void addEvent(String action){
        events.add(action);
    }

    @Override
    public String
    toString() {
        return "Process{" +
            "states=" + states +
            ", events=" + events +
            '}';
    }
}
