package com.example.demo.service;

import com.example.demo.config.PersistStateMachineHandler;
import com.example.demo.consts.Events;
import com.example.demo.consts.Status;
import com.example.demo.domain.Group;
import com.example.demo.domain.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;

public class GroupService {

    @Autowired
    private PersistStateMachineHandler handler;
    @Autowired
    private GroupRepository repository;

    public boolean handleAction(int groupId, String event) {
        Group group = repository.findGroupById(groupId);
        //发送事件去触发状态机
        return handler.handleEventWithState(MessageBuilder.withPayload(Events.valueOf(event))
                .setHeader("group", group).build(), Status.valueOf(group.getStatus()));
    }

    public void create(Group group) {
        repository.create(group);
    }

    public List listAll() {
        return repository.listAll();
    }
}
