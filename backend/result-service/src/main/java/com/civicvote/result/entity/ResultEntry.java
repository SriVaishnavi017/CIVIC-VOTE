package com.civicvote.result.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "result_entries")
public class ResultEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long electionId;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private String candidateName;

    @Column(nullable = false)
    private Long voteCount = 0L;

    public ResultEntry() {
    }

    public ResultEntry(Long electionId, Long candidateId, String candidateName) {
        this.electionId = electionId;
        this.candidateId = candidateId;
        this.candidateName = candidateName;
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

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}
