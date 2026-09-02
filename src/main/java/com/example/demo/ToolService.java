package com.example.demo;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ToolService {

    private final ToolRepository toolRepository;

    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    public Tool updateToolStatus(Integer id, String status) {

        Tool tool = toolRepository.findById(id).orElse(null);

        if (tool != null) {
            tool.setStatus(status);
            return toolRepository.save(tool);
        }

        return null;
    }
}