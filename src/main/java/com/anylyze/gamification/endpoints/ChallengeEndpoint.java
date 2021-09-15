package com.anylyze.gamification.endpoints;

import com.anylyze.gamification.dto.ChallengeDto;
import com.anylyze.gamification.dto.ChallengeWithIdDto;
import com.anylyze.gamification.model.Challenge;
import com.anylyze.gamification.service.ChallengeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeEndpoint {

    @Autowired
    private ChallengeService challengeService;


    @GetMapping
    public List<ChallengeWithIdDto> getAll() {
        return challengeService.getAll();
    }

    @PostMapping
    public ChallengeWithIdDto createChallenge(@RequestBody ChallengeDto challengeDto) {

        return challengeService.createChallenge(challengeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteChallenge(@PathVariable Long id) {
        try {
            challengeService.deleteChallenge(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateChallenge(@PathVariable Long id, @RequestBody ChallengeDto challengeDto) {
        try {
            return ResponseEntity.ok(challengeService.updateChallenge(id, challengeDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

