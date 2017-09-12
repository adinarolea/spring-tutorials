package com.spring.project.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StateMachineProvider {

    private Map<String, StateMachine> stateMachineMap;

    @Autowired
    public StateMachineProvider(Collection<StateMachine> stateMachines) {
        stateMachineMap = stateMachines.stream().collect(Collectors.toMap(StateMachine::getId, stateMachine -> stateMachine));
    }

    public StateMachine getStateMachine(String id) {
        return stateMachineMap.get(id);
    }
}
