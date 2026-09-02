package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/machine-checks")
public class MachineCheckController {

    private final MachineCheckService machineCheckService;

    public MachineCheckController(MachineCheckService machineCheckService) {
        this.machineCheckService = machineCheckService;
    }

    @GetMapping
    public List<MachineCheck> getAllChecks() {
        return machineCheckService.getAllChecks();
    }

    @PutMapping("/{id}/status")
    public MachineCheck updateCheck(
            @PathVariable Integer id,
            @RequestParam String status) {

        return machineCheckService.updateCheck(id, status);
    }
}