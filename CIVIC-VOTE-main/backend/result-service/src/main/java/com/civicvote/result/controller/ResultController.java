package com.civicvote.result.controller;

import com.civicvote.result.dto.ResultSummary;
import com.civicvote.result.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    @Value("${result.service.key:dev-result-service-key}")
    private String resultServiceKey;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestParam Long electionId,
                                          @RequestHeader("X-Service-Key") String serviceKey) {
        if (!resultServiceKey.equals(serviceKey)) {
            return ResponseEntity.status(403).body("Invalid service credentials");
        }
        resultService.refreshForElection(electionId);
        return ResponseEntity.ok("Results refreshed for election " + electionId);
    }

    @GetMapping("/{electionId}")
    public ResponseEntity<ResultSummary> getResults(@PathVariable Long electionId) {
        return ResponseEntity.ok(resultService.getResults(electionId));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("RESULT-SERVICE OK");
    }
}
