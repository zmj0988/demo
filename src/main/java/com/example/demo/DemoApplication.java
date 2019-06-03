package com.example.demo;

import com.example.demo.consts.RegEventEnum;
import com.example.demo.consts.RegStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private StateMachine<RegStatusEnum, RegEventEnum> machine;

    @Override
    public void run(String... args) {
        machine.start();
        machine.sendEvent(RegEventEnum.CONNECT);
        machine.sendEvent(RegEventEnum.REGISTER_SUCCESS);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
