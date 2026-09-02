package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operations")
public class OperationToolController {

    private final OperationToolService operationToolService;

    public OperationToolController(OperationToolService operationToolService) {
        this.operationToolService = operationToolService;
    }

    @GetMapping("/{operationId}/tools")
    public List<Map<String, Object>> getRequiredTools(
            @PathVariable Integer operationId) {

        List<OperationTool> operationTools =
                operationToolService.getRequiredTools(operationId);

        List<Map<String, Object>> response =
                new ArrayList<>();

        for (OperationTool operationTool : operationTools) {

            if (operationTool.getTool() == null) {
                continue;
            }

            Tool tool = operationTool.getTool();

            Map<String, Object> toolData =
                    new HashMap<>();

            toolData.put("id", tool.getId());
            toolData.put("toolNumber", tool.getToolNumber());
            toolData.put("toolName", tool.getToolName());
            toolData.put("status", tool.getStatus());

            Map<String, Object> operationToolData =
                    new HashMap<>();

            operationToolData.put("id", operationTool.getId());
            operationToolData.put("tool", toolData);

            response.add(operationToolData);
        }

        return response;
    }
}