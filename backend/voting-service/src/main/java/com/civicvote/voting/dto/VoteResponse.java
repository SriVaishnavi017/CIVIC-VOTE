package com.civicvote.voting.dto;

public class VoteResponse {
    private Long voteId;
    private Long electionId;
    private Long candidateId;
    private String ballotReference;
    private String message;

    public VoteResponse() {
    }

    public VoteResponse(Long voteId, Long electionId, Long candidateId, String ballotReference, String message) {
        this.voteId = voteId;
        this.electionId = electionId;
        this.candidateId = candidateId;
        this.ballotReference = ballotReference;
        this.message = message;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
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

    public String getBallotReference() {
        return ballotReference;
    }

    public void setBallotReference(String ballotReference) {
        this.ballotReference = ballotReference;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
