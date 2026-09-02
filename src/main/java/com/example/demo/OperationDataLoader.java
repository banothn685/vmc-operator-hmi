package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OperationDataLoader implements CommandLineRunner {

    private final OperationRepository operationRepository;
    private final ToolRepository toolRepository;
    private final OperationToolRepository operationToolRepository;

    public OperationDataLoader(
            OperationRepository operationRepository,
            ToolRepository toolRepository,
            OperationToolRepository operationToolRepository) {

        this.operationRepository = operationRepository;
        this.toolRepository = toolRepository;
        this.operationToolRepository = operationToolRepository;
    }

    @Override
    public void run(String... args) {

        if (operationRepository.count() == 0) {

            Tool faceMill = toolRepository.findByToolNumber("T01");
            Tool drill = toolRepository.findByToolNumber("T02");
            Tool endMill = toolRepository.findByToolNumber("T03");

            Operation faceMilling = new Operation();
            faceMilling.setOperationName("Face Milling");
            faceMilling.setStatus("PENDING");

            Operation drilling = new Operation();
            drilling.setOperationName("Drilling");
            drilling.setStatus("PENDING");

            Operation pocketMilling = new Operation();
            pocketMilling.setOperationName("Pocket Milling");
            pocketMilling.setStatus("PENDING");

            operationRepository.save(faceMilling);
            operationRepository.save(drilling);
            operationRepository.save(pocketMilling);

            OperationTool ot1 = new OperationTool();
            ot1.setOperation(faceMilling);
            ot1.setTool(faceMill);

            OperationTool ot2 = new OperationTool();
            ot2.setOperation(drilling);
            ot2.setTool(drill);

            OperationTool ot3 = new OperationTool();
            ot3.setOperation(pocketMilling);
            ot3.setTool(endMill);

            operationToolRepository.save(ot1);
            operationToolRepository.save(ot2);
            operationToolRepository.save(ot3);
        }
    }
}