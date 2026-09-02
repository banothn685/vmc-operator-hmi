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

        Operation faceMilling =
                operationRepository.findByOperationName("Face Milling");

        if (faceMilling == null) {
            faceMilling = new Operation();
            faceMilling.setOperationName("Face Milling");
            faceMilling.setStatus("PENDING");
            faceMilling = operationRepository.save(faceMilling);
        }

        Operation drilling =
                operationRepository.findByOperationName("Drilling");

        if (drilling == null) {
            drilling = new Operation();
            drilling.setOperationName("Drilling");
            drilling.setStatus("PENDING");
            drilling = operationRepository.save(drilling);
        }

        Operation pocketMilling =
                operationRepository.findByOperationName("Pocket Milling");

        if (pocketMilling == null) {
            pocketMilling = new Operation();
            pocketMilling.setOperationName("Pocket Milling");
            pocketMilling.setStatus("PENDING");
            pocketMilling = operationRepository.save(pocketMilling);
        }

        Tool faceMill =
                toolRepository.findByToolNumber("T01");

        Tool drill =
                toolRepository.findByToolNumber("T02");

        Tool endMill =
                toolRepository.findByToolNumber("T03");

        if (faceMill != null
                && operationToolRepository
                        .findByOperationId(faceMilling.getId())
                        .isEmpty()) {

            OperationTool operationTool = new OperationTool();
            operationTool.setOperation(faceMilling);
            operationTool.setTool(faceMill);

            operationToolRepository.save(operationTool);
        }

        if (drill != null
                && operationToolRepository
                        .findByOperationId(drilling.getId())
                        .isEmpty()) {

            OperationTool operationTool = new OperationTool();
            operationTool.setOperation(drilling);
            operationTool.setTool(drill);

            operationToolRepository.save(operationTool);
        }

        if (endMill != null
                && operationToolRepository
                        .findByOperationId(pocketMilling.getId())
                        .isEmpty()) {

            OperationTool operationTool = new OperationTool();
            operationTool.setOperation(pocketMilling);
            operationTool.setTool(endMill);

            operationToolRepository.save(operationTool);
        }
    }
}