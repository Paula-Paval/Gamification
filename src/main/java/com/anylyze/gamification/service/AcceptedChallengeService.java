package com.anylyze.gamification.service;

import com.anylyze.gamification.model.AcceptedChallenge;
import com.anylyze.gamification.model.Challenge;
import com.anylyze.gamification.model.ChallengeStatus;
import com.anylyze.gamification.model.User;
import com.anylyze.gamification.repository.AcceptedChallengeRepository;
import com.anylyze.gamification.repository.ChallengeRepository;
import com.anylyze.gamification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcceptedChallengeService {
    @Autowired
    private AcceptedChallengeRepository acceptedChallengeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;

    public void acceptedChallenge(Long userId, Long challengeId, ChallengeStatus status) {
        if (challengeAlreadyExists(challengeId) && userAlreadyExists(userId))
            throw new EntityExistsException();
        else {
            User user = userRepository.getOne(userId);
            Optional<Challenge> challenge = challengeRepository.findById(challengeId);
//            if (challenge.isEmpty()) {
//                throw new EntityNotFoundException("Challenge not found!");
//            } else {
//                user.getChallenges().add(new AcceptedChallenge(challengeId, status, user));
//                userRepository.save(user);
//            }
        }

    }

    private boolean challengeAlreadyExists(Long challengeId) {
        return !(acceptedChallengeRepository.findAll()
                .stream()
                .filter(item -> (item.getChallengeId().equals(challengeId)))
                .collect(Collectors.toList())
                .isEmpty());
    }

    private boolean userAlreadyExists(Long userId) {
        return !(acceptedChallengeRepository.findAll()
                .stream()
                .filter(item -> item.getUser().getId().equals(userId))
                .collect(Collectors.toList())
                .isEmpty());
    }

    public void declineChallenge(Long acceptedChallengeId) {
        acceptedChallengeRepository.deleteById(acceptedChallengeId);
    }

    public List<Challenge> getAcceptedChallenges(Long userId) {
        List<Long> acceptedChallenges = acceptedChallengeRepository.findAll()
                .stream()
                .filter(item -> item.getUser().getId().equals(userId))
                .map(AcceptedChallenge::getChallengeId)
                .collect(Collectors.toList());
        List<Challenge> challenges = acceptedChallenges.stream()
                .map(id -> challengeRepository.findById(id).get())
                .collect(Collectors.toList());
        return challenges;
    }

    public void updateStatus(Long userChallengeId, ChallengeStatus status) {
        acceptedChallengeRepository.findById(userChallengeId).ifPresent(acceptedChallenge -> {
            acceptedChallenge.setStatus(status);
            acceptedChallengeRepository.save(acceptedChallenge);
            if (status.equals(ChallengeStatus.VALIDATED)) {
                User user = acceptedChallenge.getUser();
                user.setCredits(user.getCredits() + challengeRepository.getOne(acceptedChallenge.getChallengeId()).getCredits());
                user.setExperience(user.getExperience() + challengeRepository.getOne(acceptedChallenge.getChallengeId()).getExperience());
                userRepository.save(user);
            }

        });
    }
}
