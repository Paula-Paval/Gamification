package com.anylyze.gamification.repository;

import com.anylyze.gamification.model.AcceptedChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptedChallengeRepository extends JpaRepository<AcceptedChallenge, Long> {
}
