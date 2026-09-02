package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WorkpieceDataLoader implements CommandLineRunner {

    private final WorkpieceRepository workpieceRepository;

    public WorkpieceDataLoader(WorkpieceRepository workpieceRepository) {
        this.workpieceRepository = workpieceRepository;
    }

    @Override
    public void run(String... args) {

        if (workpieceRepository.count() == 0) {

            Workpiece workpiece = new Workpiece();

            workpiece.setMaterial("Aluminium");
            workpiece.setFixture("VMC Standard Fixture");
            workpiece.setOrientation("Top Face Up");
            workpiece.setWorkOffset("G54");

            workpiece.setStatus("PENDING");

            workpiece.setFixtureStatus("PENDING");
            workpiece.setOrientationStatus("PENDING");
            workpiece.setClampingStatus("PENDING");
            workpiece.setMaterialStatus("PENDING");
            workpiece.setWorkOffsetStatus("PENDING");

            workpieceRepository.save(workpiece);
        }
    }
}