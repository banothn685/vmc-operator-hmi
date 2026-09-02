package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationToolRepository extends JpaRepository<OperationTool, Integer> {

    List<OperationTool> findByOperationId(Integer operationId);
}