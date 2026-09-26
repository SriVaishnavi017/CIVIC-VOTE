package com.civicvote.voting.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes", uniqueConstraints = {
    @UniqueConstraint(name = "uk_voter_election", columnNames = {"voterId", "electionId"})
})
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long electionId;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private Long voterId;

    @Column(nullable = false)
    private String ballotReference;

    @Column(nullable = false)
    private String previousHash;

    @Column(nullable = false)
    private String currentHash;

    @Column(nullable = false)
    private LocalDateTime castAt;

    public Vote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public String getBallotReference() {
        return ballotReference;
    }

    public void setBallotReference(String ballotReference) {
        this.ballotReference = ballotReference;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }

    public LocalDateTime getCastAt() {
        return castAt;
    }

    public void setCastAt(LocalDateTime castAt) {
        this.castAt = castAt;
    }
}
