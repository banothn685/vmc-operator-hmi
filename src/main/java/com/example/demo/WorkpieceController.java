package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workpieces")
public class WorkpieceController {

    private final WorkpieceService workpieceService;

    public WorkpieceController(WorkpieceService workpieceService) {
        this.workpieceService = workpieceService;
    }

    @GetMapping("/{id}")
    public Workpiece getWorkpiece(@PathVariable Integer id) {
        return workpieceService.getWorkpiece(id);
    }

    @PutMapping("/{id}/setup/{setupType}")
    public Workpiece updateSetupStatus(
            @PathVariable Integer id,
            @PathVariable String setupType,
            @RequestParam String status) {

        return workpieceService.updateSetupStatus(
                id,
                setupType,
                status
        );
    }
}