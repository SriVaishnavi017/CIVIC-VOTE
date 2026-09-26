package com.civicvote.election.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.civicvote.election.entity.Election;
import com.civicvote.election.entity.ElectionStatus;

public class ElectionResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ElectionStatus status;
    private String createdBy;
    private List<CandidateResponse> candidates;

    public static ElectionResponse fromEntity(Election election) {
        ElectionResponse response = new ElectionResponse();
        response.setId(election.getId());
        response.setTitle(election.getTitle());
        response.setDescription(election.getDescription());
        response.setStartTime(election.getStartTime());
        response.setEndTime(election.getEndTime());
        response.setStatus(election.getStatus());
        response.setCreatedBy(election.getCreatedBy());
        response.setCandidates(election.getCandidates().stream().map(CandidateResponse::fromEntity).toList());
        return response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ElectionStatus getStatus() {
        return status;
    }

    public void setStatus(ElectionStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<CandidateResponse> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<CandidateResponse> candidates) {
        this.candidates = candidates;
    }
}
