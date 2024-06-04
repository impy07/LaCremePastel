package com.ispwproject.lacremepastel.controller.cli.states;

import com.ispwproject.lacremepastel.controller.cli.machine.AbstractCLIStateMachine;
import com.ispwproject.lacremepastel.controller.cli.other.CLIMessages;

public class RefundState extends AbstractState{

    @Override
    public void entry(AbstractCLIStateMachine contextSM) {
        contextSM.setMessage(CLIMessages.DEVELOPING+"\n");
        contextSM.printMessage();
        contextSM.transition(contextSM.getPrevState());
    }
}
