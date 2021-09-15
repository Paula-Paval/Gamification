package com.anylyze.gamification.service;

import com.anylyze.gamification.dto.ChallengeDto;
import com.anylyze.gamification.dto.ChallengeWithIdDto;
import com.anylyze.gamification.model.Challenge;
import com.anylyze.gamification.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;


    public List<ChallengeWithIdDto> getAll() {
        List<ChallengeWithIdDto> challenges = challengeRepository.findAll()
                .stream()
                .map(this::convertToChallengeDto)
                .collect(Collectors.toList());
        return challenges;
    }

    public ChallengeWithIdDto createChallenge(ChallengeDto challengeDto) {
        Challenge challenge = convertToChallenge(challengeDto);
        challenge = challengeRepository.save(challenge);
        ChallengeWithIdDto challengeWithIdDto = convertToChallengeDto(challenge);
        return challengeWithIdDto;
    }

    public ChallengeDto updateChallenge(Long id, ChallengeDto challengeDto) {
        Challenge challenge = challengeRepository.getOne(id);
        challenge.setTitle(challengeDto.getTitle());
        challenge.setCredits(challengeDto.getCredits());
        challenge.setDescription(challengeDto.getDescription());
        challenge.setExperience(challengeDto.getExperience());
        challengeRepository.save(challenge);
        return challengeDto;
    }

    public void deleteChallenge(Long id) {
        challengeRepository.deleteById(id);
    }

    private ChallengeWithIdDto convertToChallengeDto(Challenge challenge) {
        ChallengeWithIdDto challengeDto = new ChallengeWithIdDto();
        challengeDto.setId(challenge.getId());
        challengeDto.setTitle(challenge.getTitle());
        challengeDto.setCredits(challenge.getCredits());
        challengeDto.setDescription(challenge.getDescription());
        challengeDto.setExperience(challenge.getExperience());
        return challengeDto;
    }

    private Challenge convertToChallenge(ChallengeDto challengeDto) {
        Challenge challenge = new Challenge();
        challenge.setTitle(challengeDto.getTitle());
        challenge.setCredits(challengeDto.getCredits());
        challenge.setDescription(challengeDto.getDescription());
        challenge.setExperience(challengeDto.getExperience());
        return challenge;
    }
}
