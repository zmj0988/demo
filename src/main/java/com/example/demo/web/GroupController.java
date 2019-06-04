package com.example.demo.web;

import com.example.demo.domain.Group;
import com.example.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    GroupService groupService;

    @RequestMapping("/group/list")
    public List<Group> list() {
        return groupService.listAll();
    }

    @PostMapping("/group/create")
    public boolean create(@RequestBody Group group) {
        groupService.create(group);
        return true;
    }

    //web入口，处理某个group{id}的某个事件，例如group/2/APPROVE就是对group id 为2  做approve
    @RequestMapping("/group/{id}/{event}")
    public boolean handle(@PathVariable("id") Integer id, @PathVariable("event") String event) {
        return groupService.handleAction(id, event);
    }

}