package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MachineCheckDataLoader implements CommandLineRunner {

    private final MachineCheckRepository machineCheckRepository;

    public MachineCheckDataLoader(
            MachineCheckRepository machineCheckRepository) {

        this.machineCheckRepository = machineCheckRepository;
    }

    @Override
    public void run(String... args) {

        if (machineCheckRepository.count() == 0) {

            MachineCheck check1 = new MachineCheck();
            check1.setCheckName("Power / Control Available");
            check1.setStatus("PENDING");

            MachineCheck check2 = new MachineCheck();
            check2.setCheckName("E-Stop Released");
            check2.setStatus("PENDING");

            MachineCheck check3 = new MachineCheck();
            check3.setCheckName("Guard / Door Closed");
            check3.setStatus("PENDING");

            MachineCheck check4 = new MachineCheck();
            check4.setCheckName("No Active Alarm");
            check4.setStatus("PENDING");

            MachineCheck check5 = new MachineCheck();
            check5.setCheckName("Lubrication / Coolant Ready");
            check5.setStatus("PENDING");

            MachineCheck check6 = new MachineCheck();
            check6.setCheckName("Reference / Return Complete");
            check6.setStatus("PENDING");

            machineCheckRepository.save(check1);
            machineCheckRepository.save(check2);
            machineCheckRepository.save(check3);
            machineCheckRepository.save(check4);
            machineCheckRepository.save(check5);
            machineCheckRepository.save(check6);
        }
    }
}