package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MachineCheckService {

    private final MachineCheckRepository machineCheckRepository;

    public MachineCheckService(MachineCheckRepository machineCheckRepository) {
        this.machineCheckRepository = machineCheckRepository;
    }

    public List<MachineCheck> getAllChecks() {
        return machineCheckRepository.findAll();
    }

    public MachineCheck updateCheck(Integer id, String status) {

        MachineCheck check = machineCheckRepository.findById(id).orElse(null);

        if (check != null) {
            check.setStatus(status);
            return machineCheckRepository.save(check);
        }

        return null;
    }
}