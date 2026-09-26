package com.civicvote.election.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civicvote.election.entity.Election;
import com.civicvote.election.entity.ElectionStatus;

public interface ElectionRepository extends JpaRepository<Election, Long> {
    List<Election> findByStatus(ElectionStatus status);
}
