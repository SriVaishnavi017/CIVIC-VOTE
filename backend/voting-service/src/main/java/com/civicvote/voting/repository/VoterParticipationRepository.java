package com.civicvote.voting.repository;

import com.civicvote.voting.entity.VoterParticipation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterParticipationRepository extends JpaRepository<VoterParticipation, Long> {
    boolean existsByVoterIdAndElectionId(Long voterId, Long electionId);
    Optional<VoterParticipation> findByVoterIdAndElectionId(Long voterId, Long electionId);
}
