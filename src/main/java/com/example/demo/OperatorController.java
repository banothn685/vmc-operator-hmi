package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OperatorController {

    private final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        Operator operator = operatorService.login(username, password);

        if (operator != null) {
            return "redirect:/machine-checks";
        }

        model.addAttribute("error", "Invalid username or password");

        return "login";
    }
    
    @GetMapping("/machine-checks")
    public String machineChecks() {
        return "machine-checks";
    }
    
    @GetMapping("/tools")
    public String tools() {
        return "tools";
    }

    @GetMapping("/workpiece")
    public String workpiece() {
        return "workpiece";
    }

    @GetMapping("/ready-review")
    public String readyReview() {
        return "ready-review";
    }
    
    @GetMapping("/operation")
    public String operation() {
        return "operation";
    }
    
    
}