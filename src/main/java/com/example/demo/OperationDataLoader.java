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
                operationRepository.findById(1).orElse(null);

        Operation drilling =
                operationRepository.findById(2).orElse(null);

        Operation pocketMilling =
                operationRepository.findById(3).orElse(null);

        Tool faceMill =
                toolRepository.findByToolNumber("T01");

        Tool drill =
                toolRepository.findByToolNumber("T02");

        Tool endMill =
                toolRepository.findByToolNumber("T03");

        if (faceMilling != null
                && faceMill != null
                && operationToolRepository.findByOperationId(1).isEmpty()) {

            OperationTool operationTool = new OperationTool();
            operationTool.setOperation(faceMilling);
            operationTool.setTool(faceMill);

            operationToolRepository.save(operationTool);
        }

        if (drilling != null
                && drill != null
                && operationToolRepository.findByOperationId(2).isEmpty()) {

            OperationTool operationTool = new OperationTool();
            operationTool.setOperation(drilling);
            operationTool.setTool(drill);

            operationToolRepository.save(operationTool);
        }

        if (pocketMilling != null
                && endMill != null
                && operationToolRepository.findByOperationId(3).isEmpty()) {

            OperationTool operationTool = new OperationTool();
            operationTool.setOperation(pocketMilling);
            operationTool.setTool(endMill);

            operationToolRepository.save(operationTool);
        }
    }
}