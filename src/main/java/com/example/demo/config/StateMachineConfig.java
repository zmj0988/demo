package com.example.demo.config;

import com.example.demo.consts.Events;
import com.example.demo.consts.Status;
import com.example.demo.domain.Group;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<Status, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<Status, Events> states) throws Exception {
        states.withStates()
                // 定义初始状态
                .initial(Status.PENDING_APPROVAL)
                .choice(Status.CHOICE)
                .states(EnumSet.allOf(Status.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Status, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(Status.PENDING_APPROVAL).target(Status.PARTIALLY_APPROVED).event(Events.APPROVE)
                .and()
                .withExternal()
                .source(Status.PARTIALLY_APPROVED).target(Status.CHOICE).event(Events.APPROVE)
                .and()
                .withChoice()
                .source(Status.CHOICE)
                .first(Status.PENGDING_DOCUMENT_CHECK, (context) -> {
                    Group group = context.getMessage().getHeaders().get("group", Group.class);
                    return group.isAdvance();
                })
                .last(Status.APPROVED)
                .and()
                .withExternal()
                .source(Status.PENGDING_DOCUMENT_CHECK).target(Status.PENDING_APPROVAL_CONFIRMATION).event(Events.APPROVE)
                .and()
                .withExternal()
                .source(Status.PENDING_APPROVAL_CONFIRMATION).target(Status.APPROVED).event(Events.APPROVE)
                .and()
                .withExternal()
                .source(Status.PENDING_APPROVAL_CONFIRMATION).target(Status.PENGDING_DOCUMENT_CHECK).event(Events.REJECT)
                .and()
                .withExternal()
                .source(Status.PENGDING_DOCUMENT_CHECK).target(Status.PENDING_REJECT_CONFIRMATION).event(Events.REJECT)
                .and()
                .withExternal()
                .source(Status.PENDING_REJECT_CONFIRMATION).target(Status.PENGDING_DOCUMENT_CHECK).event(Events.REJECT)
                .and()
                .withExternal()
                .source(Status.PENDING_REJECT_CONFIRMATION).target(Status.REJECTED).event(Events.APPROVE)
                .and()
                .withExternal()
                .source(Status.PENDING_APPROVAL).target(Status.REJECTED).event(Events.REJECT)
                .and()
                .withExternal()
                .source(Status.PARTIALLY_APPROVED).target(Status.REJECTED).event(Events.REJECT);
    }
}
