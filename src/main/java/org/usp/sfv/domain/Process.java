package org.usp.sfv.domain;

import java.util.*;

/**
 * App: bissimulation User: caiobos Date: 9/11/15
 */
public class Process {

    private String s0 = null;
    private Set<String> states = new HashSet<>();
    private Set<String> events = new HashSet<>();

    private Map<String, HashSet<String>> eventFromMap = new HashMap<>();
    private List<Transition> transitions = new ArrayList<>();

    public void addTransition(Transition t) {
        transitions.add(t);
        states.add(t.getStateFrom());
        states.add(t.getStateTo());
        events.add(t.getEvent());
        if (s0 == null) s0 = t.getStateFrom();


        //EVENT FROM MAP
        HashSet<String> statesFrom =  eventFromMap.get(t.getEvent());
        if(statesFrom==null){
            statesFrom = new HashSet<>();
        }
        statesFrom.add(t.getStateFrom());
        eventFromMap.put(t.getEvent(), statesFrom);


    }

    public String getS0() {
        return s0;
    }

    public Set<String> getStates() {
        return states;
    }

    public Set<String> getEvents() {
        return events;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public Map<String, HashSet<String>> getEventFromMap() {
        return eventFromMap;
    }

    public void setEventFromMap(Map<String, HashSet<String>> eventFromMap) {
        this.eventFromMap = eventFromMap;
    }

    @Override
    public String toString() {
        return "Process{" +
            "s0='" + s0 + '\'' +
            ", states=" + states +
            ", events=" + events +
            ", eventFromMap=" + eventFromMap +
            ", transitions=" + transitions +
            '}';
    }

}
