package com.civicvote.election.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civicvote.election.dto.CandidateRequest;
import com.civicvote.election.dto.CandidateResponse;
import com.civicvote.election.dto.ElectionRequest;
import com.civicvote.election.dto.ElectionResponse;
import com.civicvote.election.service.ElectionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/elections")
public class ElectionController {

    private final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ElectionResponse> createElection(@Valid @RequestBody ElectionRequest request,
                                                         @RequestHeader(value = "X-User-Name", required = false) String username) {
        String createdBy = username != null ? username : "admin";
        return ResponseEntity.ok(electionService.createElection(request, createdBy));
    }

    @GetMapping
    public ResponseEntity<List<ElectionResponse>> getAllElections() {
        return ResponseEntity.ok(electionService.getAllElections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectionResponse> getElection(@PathVariable Long id) {
        return ResponseEntity.ok(electionService.getElection(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/candidates")
    public ResponseEntity<CandidateResponse> addCandidate(@PathVariable Long id,
                                                        @Valid @RequestBody CandidateRequest request) {
        return ResponseEntity.ok(electionService.addCandidate(id, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/activate")
    public ResponseEntity<ElectionResponse> activateElection(@PathVariable Long id) {
        return ResponseEntity.ok(electionService.activateElection(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/close")
    public ResponseEntity<ElectionResponse> closeElection(@PathVariable Long id) {
        return ResponseEntity.ok(electionService.closeElection(id));
    }
}
