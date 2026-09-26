package com.civicvote.election.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civicvote.election.dto.CandidateRequest;
import com.civicvote.election.dto.CandidateResponse;
import com.civicvote.election.dto.ElectionRequest;
import com.civicvote.election.dto.ElectionResponse;
import com.civicvote.election.entity.Candidate;
import com.civicvote.election.entity.Election;
import com.civicvote.election.entity.ElectionStatus;
import com.civicvote.election.repository.ElectionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ElectionService {

    private final ElectionRepository electionRepository;

    public ElectionService(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    @Transactional
    public ElectionResponse createElection(ElectionRequest request, String createdBy) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("Election title cannot be empty");
        }
        if (request.getStartTime() == null || request.getEndTime() == null) {
            throw new IllegalArgumentException("Start time and end time are required");
        }
        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        Election election = new Election();
        election.setTitle(request.getTitle());
        election.setDescription(request.getDescription());
        election.setStartTime(request.getStartTime());
        election.setEndTime(request.getEndTime());
        election.setStatus(ElectionStatus.DRAFT);
        election.setCreatedBy(createdBy);

        return ElectionResponse.fromEntity(electionRepository.save(election));
    }

    public List<ElectionResponse> getAllElections() {
        return electionRepository.findAll().stream().map(ElectionResponse::fromEntity).toList();
    }

    public ElectionResponse getElection(Long id) {
        Election election = electionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Election not found with id: " + id));
        return ElectionResponse.fromEntity(election);
    }

    @Transactional
    public CandidateResponse addCandidate(Long electionId, CandidateRequest request) {
        Election election = electionRepository.findById(electionId)
            .orElseThrow(() -> new EntityNotFoundException("Election not found with id: " + electionId));

        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Candidate name cannot be empty");
        }

        Candidate candidate = new Candidate(election, request.getName(), request.getDescription());
        election.getCandidates().add(candidate);
        electionRepository.save(election);
        return CandidateResponse.fromEntity(candidate);
    }

    @Transactional
    public ElectionResponse activateElection(Long electionId) {
        Election election = electionRepository.findById(electionId)
            .orElseThrow(() -> new EntityNotFoundException("Election not found with id: " + electionId));

        if (election.getCandidates() == null || election.getCandidates().isEmpty()) {
            throw new IllegalArgumentException("Election must have at least one candidate before activation");
        }

        election.setStatus(ElectionStatus.ACTIVE);
        return ElectionResponse.fromEntity(electionRepository.save(election));
    }

    @Transactional
    public ElectionResponse closeElection(Long electionId) {
        Election election = electionRepository.findById(electionId)
            .orElseThrow(() -> new EntityNotFoundException("Election not found with id: " + electionId));

        election.setStatus(ElectionStatus.CLOSED);
        return ElectionResponse.fromEntity(electionRepository.save(election));
    }

    public boolean isElectionActive(Long electionId) {
        return electionRepository.findById(electionId)
            .map(election -> election.getStatus() == ElectionStatus.ACTIVE)
            .orElse(false);
    }

    public boolean isElectionOpenForVoting(Long electionId) {
        Election election = electionRepository.findById(electionId).orElse(null);
        if (election == null) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        return election.getStatus() == ElectionStatus.ACTIVE && !now.isBefore(election.getStartTime()) && !now.isAfter(election.getEndTime());
    }
}
