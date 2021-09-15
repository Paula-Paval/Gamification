package com.anylyze.gamification.endpoints;

import com.anylyze.gamification.data.ChallengeData;
import com.anylyze.gamification.data.ChallengeStatusData;
import com.anylyze.gamification.service.AcceptedChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestController
public class AcceptedChallengeEndpoint {

    @Autowired
    private AcceptedChallengeService acceptedChallengeService;

    @GetMapping("/available-challenges")
    public RedirectView showAvailableChallenges() {
        return new RedirectView("/challenges");
    }

    @PostMapping("/user-challenges/{id}")
    public ResponseEntity acceptechallenge(@PathVariable Long id, @RequestBody ChallengeData challenge) {
        try {
            acceptedChallengeService.acceptedChallenge(id, challenge.getChallengeId(), challenge.getStatus());
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityExistsException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @DeleteMapping("/user-challenges/{id}")
    public ResponseEntity deleteReward(@PathVariable Long id) {
        try {
            acceptedChallengeService.declineChallenge(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user-challenges/{id}")
    public ResponseEntity getAcceptedChallenges(@PathVariable Long id) {
        return ResponseEntity.ok(acceptedChallengeService.getAcceptedChallenges(id));
    }

    @PutMapping("/user-challenges/{id}")
    public ResponseEntity updateStatus(@PathVariable Long id, @RequestBody ChallengeStatusData status) {
        acceptedChallengeService.updateStatus(id, status.getStatus());
        return ResponseEntity.ok().build();
    }
}
