package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<Tool, Integer> {

    Tool findByToolNumber(String toolNumber);
}