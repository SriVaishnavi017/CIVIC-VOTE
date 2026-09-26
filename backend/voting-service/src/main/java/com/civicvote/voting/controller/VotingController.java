package com.civicvote.voting.controller;

import com.civicvote.voting.dto.VoteRequest;
import com.civicvote.voting.dto.VoteResponse;
import com.civicvote.voting.service.VotingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/votes")
public class VotingController {

    private final VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PreAuthorize("hasRole('VOTER')")
    @PostMapping
    public ResponseEntity<VoteResponse> castVote(@Valid @RequestBody VoteRequest request,
                                               Authentication authentication) {
        Long voterId = Long.parseLong(authentication.getName());
        return ResponseEntity.ok(votingService.castVote(voterId, request));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("VOTING-SERVICE OK");
    }
}
