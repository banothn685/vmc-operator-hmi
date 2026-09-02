package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OperationToolService {

    private final OperationToolRepository operationToolRepository;

    public OperationToolService(
            OperationToolRepository operationToolRepository) {

        this.operationToolRepository = operationToolRepository;
    }

    public List<OperationTool> getRequiredTools(Integer operationId) {

        return operationToolRepository.findByOperationId(operationId);
    }
}