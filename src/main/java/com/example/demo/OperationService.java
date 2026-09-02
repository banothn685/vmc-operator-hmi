package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class OperationService {

    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Operation getOperation(Integer id) {
        return operationRepository.findById(id).orElse(null);
    }

    public Operation updateStatus(Integer id, String status) {

        Operation operation = operationRepository.findById(id).orElse(null);

        if (operation != null) {
            operation.setStatus(status);
            return operationRepository.save(operation);
        }

        return null;
    }
    public boolean isOperationReady(Integer id) {

        Operation operation =
                operationRepository.findById(id).orElse(null);

        return operation != null;
    }
}