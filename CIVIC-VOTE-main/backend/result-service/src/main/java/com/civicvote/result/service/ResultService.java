package com.civicvote.result.service;

import com.civicvote.result.dto.ResultItem;
import com.civicvote.result.dto.ResultSummary;
import com.civicvote.result.entity.ResultEntry;
import com.civicvote.result.repository.ResultEntryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    private final ResultEntryRepository resultEntryRepository;

    public ResultService(ResultEntryRepository resultEntryRepository) {
        this.resultEntryRepository = resultEntryRepository;
    }

    @Transactional
    public void refreshForElection(Long electionId) {
        resultEntryRepository.deleteByElectionId(electionId);
        List<ResultEntry> entries = new ArrayList<>();
        entries.add(new ResultEntry(electionId, 1L, "Candidate A"));
        entries.add(new ResultEntry(electionId, 2L, "Candidate B"));
        entries.add(new ResultEntry(electionId, 3L, "Candidate C"));

        for (ResultEntry entry : entries) {
            entry.setVoteCount(0L);
            resultEntryRepository.save(entry);
        }
    }

    public ResultSummary getResults(Long electionId) {
        List<ResultEntry> entries = resultEntryRepository.findByElectionId(electionId);
        if (entries.isEmpty()) {
            throw new EntityNotFoundException("No results available for election " + electionId);
        }

        long totalVotes = entries.stream().mapToLong(ResultEntry::getVoteCount).sum();
        List<ResultItem> items = entries.stream().map(entry -> {
            ResultItem item = new ResultItem();
            item.setCandidateId(entry.getCandidateId());
            item.setCandidateName(entry.getCandidateName());
            item.setVoteCount(entry.getVoteCount());
            return item;
        }).toList();

        ResultSummary summary = new ResultSummary();
        summary.setElectionId(electionId);
        summary.setStatus("CLOSED");
        summary.setTotalVotes(totalVotes);
        summary.setResults(items);
        return summary;
    }
}
