package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ToolDataLoader implements CommandLineRunner {

    private final ToolRepository toolRepository;

    public ToolDataLoader(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Override
    public void run(String... args) {

        if (toolRepository.count() == 0) {

            Tool t1 = new Tool();
            t1.setToolNumber("T01");
            t1.setToolName("Face Mill");
            t1.setStatus("AVAILABLE");

            Tool t2 = new Tool();
            t2.setToolNumber("T02");
            t2.setToolName("Drill");
            t2.setStatus("AVAILABLE");

            Tool t3 = new Tool();
            t3.setToolNumber("T03");
            t3.setToolName("End Mill");
            t3.setStatus("AVAILABLE");

            Tool t4 = new Tool();
            t4.setToolNumber("T04");
            t4.setToolName("Tap");
            t4.setStatus("NOT_AVAILABLE");

            toolRepository.save(t1);
            toolRepository.save(t2);
            toolRepository.save(t3);
            toolRepository.save(t4);
        }
    }
}