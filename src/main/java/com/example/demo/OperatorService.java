package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class OperatorService {

    private final OperatorRepository operatorRepository;

    public OperatorService(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    public Operator login(String username, String password) {

        return operatorRepository.findByUsernameAndPassword(username, password);
    }
}