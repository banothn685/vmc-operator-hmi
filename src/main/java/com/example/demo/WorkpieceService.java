package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class WorkpieceService {

    private final WorkpieceRepository workpieceRepository;

    public WorkpieceService(WorkpieceRepository workpieceRepository) {
        this.workpieceRepository = workpieceRepository;
    }

    public Workpiece getWorkpiece(Integer id) {
        return workpieceRepository.findById(id).orElse(null);
    }

    public Workpiece updateSetupStatus(
            Integer id,
            String setupType,
            String status) {

        Workpiece workpiece =
                workpieceRepository.findById(id).orElse(null);

        if (workpiece == null) {
            return null;
        }

        setupType = setupType.toUpperCase();

        switch (setupType) {

            case "FIXTURE":
                workpiece.setFixtureStatus(status);
                break;

            case "ORIENTATION":
                workpiece.setOrientationStatus(status);
                break;

            case "CLAMPING":
                workpiece.setClampingStatus(status);
                break;

            case "MATERIAL":
                workpiece.setMaterialStatus(status);
                break;

            case "WORK_OFFSET":
                workpiece.setWorkOffsetStatus(status);
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid setup type: " + setupType
                );
        }

        if ("CONFIRMED".equals(workpiece.getFixtureStatus())
                && "CONFIRMED".equals(workpiece.getOrientationStatus())
                && "CONFIRMED".equals(workpiece.getClampingStatus())
                && "CONFIRMED".equals(workpiece.getMaterialStatus())
                && "CONFIRMED".equals(workpiece.getWorkOffsetStatus())) {

            workpiece.setStatus("CONFIRMED");

        } else {

            workpiece.setStatus("PENDING");
        }

        return workpieceRepository.save(workpiece);
    }
}