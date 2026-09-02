package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping
    public List<Tool> getAllTools() {
        return toolService.getAllTools();
    }

    @PutMapping("/{id}/status")
    public Tool updateToolStatus(
            @PathVariable Integer id,
            @RequestParam String status) {

        return toolService.updateToolStatus(id, status);
    }
}