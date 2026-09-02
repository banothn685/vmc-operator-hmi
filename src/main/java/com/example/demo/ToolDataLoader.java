package com.example.demo;

import org.springframework.core.annotation.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ToolDataLoader implements CommandLineRunner {

    private final ToolRepository toolRepository;

    public ToolDataLoader(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Override
    public void run(String... args) {

        Tool t1 = toolRepository.findByToolNumber("T01");

        if (t1 == null) {
            t1 = new Tool();
            t1.setToolNumber("T01");
            t1.setToolName("Face Mill");
            t1.setStatus("AVAILABLE");
            toolRepository.save(t1);
        }

        Tool t2 = toolRepository.findByToolNumber("T02");

        if (t2 == null) {
            t2 = new Tool();
            t2.setToolNumber("T02");
            t2.setToolName("Drill");
            t2.setStatus("AVAILABLE");
            toolRepository.save(t2);
        }

        Tool t3 = toolRepository.findByToolNumber("T03");

        if (t3 == null) {
            t3 = new Tool();
            t3.setToolNumber("T03");
            t3.setToolName("End Mill");
            t3.setStatus("AVAILABLE");
            toolRepository.save(t3);
        }

        Tool t4 = toolRepository.findByToolNumber("T04");

        if (t4 == null) {
            t4 = new Tool();
            t4.setToolNumber("T04");
            t4.setToolName("Tap");
            t4.setStatus("NOT_AVAILABLE");
            toolRepository.save(t4);
        }
    }
}