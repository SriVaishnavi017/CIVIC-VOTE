package com.civicvote.result.repository;

import com.civicvote.result.entity.ResultEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultEntryRepository extends JpaRepository<ResultEntry, Long> {
    List<ResultEntry> findByElectionId(Long electionId);
    Optional<ResultEntry> findByElectionIdAndCandidateId(Long electionId, Long candidateId);
    void deleteByElectionId(Long electionId);
}
