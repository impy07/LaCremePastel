package com.ispwproject.lacremepastel.controller.cli.states;

import com.ispwproject.lacremepastel.controller.cli.machine.AbstractCLIStateMachine;
import com.ispwproject.lacremepastel.controller.cli.other.CLIMessages;

public class InitialState extends AbstractState{
    public InitialState() {
        super();
    }

    @Override
    public void entry(AbstractCLIStateMachine contextSM) {
        StringBuilder message = new StringBuilder();
        message.append(CLIMessages.WELCOME);
        message.append(CLIMessages.CHOOSE_EXPR);
        message.append(prettifyAvailableStates());
        message.append(CLIMessages.PROMPT_EXPR);
        contextSM.setMessage(message.toString());
    }
}
