package com.ispwproject.lacremepastel.controller.cli.states;

import com.ispwproject.lacremepastel.controller.cli.machine.AbstractCLIStateMachine;
import com.ispwproject.lacremepastel.controller.cli.other.CLIMessages;
import com.ispwproject.lacremepastel.controller.cli.other.SupportedStates;

import java.util.*;

public abstract class AbstractState {

    protected HashMap<Integer,AbstractState> availableStates;
    protected SupportedStates stateName;

    protected AbstractState() {
        this.availableStates = new HashMap<>();
    }

    public List<AbstractState> getAvailableStates() {
        ArrayList<AbstractState> list = new ArrayList<>();
        for(int i = 0; i<availableStates.size(); i++){
            AbstractState state = this.availableStates.get(i);
            if(state != null) {
                list.add(state);
            }
        }
        return list;
    }

    public void addState(AbstractState state) {
        this.availableStates.put(this.availableStates.size()+1,state);
    }

    public void removeState(int id) {
        this.availableStates.remove(id);
    }

    public AbstractState getState(int id){
        return this.availableStates.get(id);
    }

    public String getStateName(){
        return stateName.toString();
    }

    public void setStateName(SupportedStates newName){
        this.stateName = newName;
    }

    public AbstractState getChosenState(int choose){
        return this.availableStates.get(choose);
    }

    public void entry(AbstractCLIStateMachine contextSM){}
    public void exit(AbstractCLIStateMachine contextSM){contextSM.setMessage("");}

    /**
     * @param contextSM Concrete instance AbstractCLIStateMachine
     * @return true if there is something to do, false otherwise
     */
    public boolean doAction(AbstractCLIStateMachine contextSM){return false;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractState that = (AbstractState) o;
        return stateName == that.stateName;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stateName);
    }

    @Override
    public String toString() {
        return "AbstractState{" +
                "availableStates=" + availableStates +
                ", stateName=" + stateName +
                '}';
    }

    public String prettifyAvailableStates(){
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i<= this.availableStates.size(); i++){
            builder.append(i).append(") ").append(this.availableStates.get(i).stateName.toString()).append("\n");
        }
        return builder.toString();
    }
}
