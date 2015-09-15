package org.usp.sfv.domain;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/11/15
 */
public class Transition {

    private String stateFrom;
    private String stateTo;
    private String event;

    public Transition(String stateFrom, String event, String stateTo) {
        this.stateFrom = stateFrom;
        this.stateTo = stateTo;
        this.event = event;
    }

    public String getStateFrom() {
        return stateFrom;
    }

    public void setStateFrom(String stateFrom) {
        this.stateFrom = stateFrom;
    }

    public String getStateTo() {
        return stateTo;
    }

    public void setStateTo(String stateTo) {
        this.stateTo = stateTo;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Transition{" +
            "stateFrom='" + stateFrom + '\'' +
            ", stateTo='" + stateTo + '\'' +
            ", event='" + event + '\'' +
            '}';
    }
}
