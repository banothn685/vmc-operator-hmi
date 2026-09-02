package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class OperatorDataLoader implements CommandLineRunner {

    private final OperatorRepository operatorRepository;

    public OperatorDataLoader(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @Override
    public void run(String... args) {

        Operator operator = operatorRepository.findByUsername("adimn");

        if (operator == null) {

            operator = new Operator();

            operator.setUsername("adimn");
            operator.setPassword("adimn12345");

            operatorRepository.save(operator);
        }
    }
}