package com.civicvote.voting.service;

import com.civicvote.voting.client.ResultServiceClient;
import com.civicvote.voting.dto.VoteRequest;
import com.civicvote.voting.dto.VoteResponse;
import com.civicvote.voting.entity.Vote;
import com.civicvote.voting.entity.VoterParticipation;
import com.civicvote.voting.repository.VoteRepository;
import com.civicvote.voting.repository.VoterParticipationRepository;
import com.civicvote.voting.security.HashChainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VotingService {

    private final VoteRepository voteRepository;
    private final VoterParticipationRepository participationRepository;
    private final HashChainService hashChainService;
    private final ResultServiceClient resultServiceClient;

    @Value("${result.service.key:dev-result-service-key}")
    private String resultServiceKey;

    public VotingService(VoteRepository voteRepository,
                        VoterParticipationRepository participationRepository,
                        HashChainService hashChainService,
                        ResultServiceClient resultServiceClient) {
        this.voteRepository = voteRepository;
        this.participationRepository = participationRepository;
        this.hashChainService = hashChainService;
        this.resultServiceClient = resultServiceClient;
    }

    @Transactional
    public VoteResponse castVote(Long voterId, VoteRequest request) {
        if (voteRepository.existsByVoterIdAndElectionId(voterId, request.getElectionId())) {
            throw new IllegalStateException("Voter has already voted in this election");
        }

        Vote lastVote = voteRepository.findTopByOrderByIdDesc().orElse(null);
        String previousHash = lastVote != null ? lastVote.getCurrentHash() : hashChainService.getInitialPreviousHash();
        String ballotReference = UUID.randomUUID().toString();
        String currentHash = hashChainService.computeHash(previousHash, request.getElectionId(), request.getCandidateId(), voterId, ballotReference);

        Vote vote = new Vote();
        vote.setElectionId(request.getElectionId());
        vote.setCandidateId(request.getCandidateId());
        vote.setVoterId(voterId);
        vote.setBallotReference(ballotReference);
        vote.setPreviousHash(previousHash);
        vote.setCurrentHash(currentHash);
        vote.setCastAt(LocalDateTime.now());

        Vote savedVote = voteRepository.save(vote);

        VoterParticipation participation = new VoterParticipation(voterId, request.getElectionId(), LocalDateTime.now());
        participationRepository.save(participation);

        resultServiceClient.refreshResult(request.getElectionId(), resultServiceKey);

        return new VoteResponse(
            savedVote.getId(),
            savedVote.getElectionId(),
            savedVote.getCandidateId(),
            savedVote.getBallotReference(),
            "Vote recorded successfully"
        );
    }

    public boolean hasVoterAlreadyVoted(Long voterId, Long electionId) {
        return voteRepository.existsByVoterIdAndElectionId(voterId, electionId);
    }
}
