package com.civicvote.voting.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voter_participation", uniqueConstraints = {
    @UniqueConstraint(name = "uk_participation_voter_election", columnNames = {"voterId", "electionId"})
})
public class VoterParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long voterId;

    @Column(nullable = false)
    private Long electionId;

    @Column(nullable = false)
    private LocalDateTime votedAt;

    public VoterParticipation() {
    }

    public VoterParticipation(Long voterId, Long electionId, LocalDateTime votedAt) {
        this.voterId = voterId;
        this.electionId = electionId;
        this.votedAt = votedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public LocalDateTime getVotedAt() {
        return votedAt;
    }

    public void setVotedAt(LocalDateTime votedAt) {
        this.votedAt = votedAt;
    }
}
