package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operations")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/{id}")
    public Operation getOperation(@PathVariable Integer id) {
        return operationService.getOperation(id);
    }

    @PutMapping("/{id}/status")
    public Operation updateStatus(
            @PathVariable Integer id,
            @RequestParam String status) {

        return operationService.updateStatus(id, status);
    }
}