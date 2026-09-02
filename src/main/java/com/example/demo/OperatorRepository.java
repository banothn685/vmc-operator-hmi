package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepository extends JpaRepository<Operator, String> {

    Operator findByUsernameAndPassword(String username, String password);

}