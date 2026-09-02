package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {

    Operation findByOperationName(String operationName);
}