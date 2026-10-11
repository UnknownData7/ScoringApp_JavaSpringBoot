package com.example.demo;


import com.example.demo.model.LoanApplication;
import com.example.demo.model.ScoringResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scoring")
public class ScoringController {

    private final ScoringService scoringService;

    public ScoringController(ScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @PostMapping
    public ScoringResult score(@RequestBody LoanApplication application) {
        return scoringService.score(application);
    }
}
