package com.anylyze.gamification.data;

import com.anylyze.gamification.model.ChallengeStatus;

import java.io.Serializable;

public class ChallengeData implements Serializable {
    private Long challengeId;
    private ChallengeStatus status;


    public ChallengeData() {
    }

    public ChallengeData(Long challengeId, ChallengeStatus status) {
        this.challengeId = challengeId;
        this.status = status;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public ChallengeStatus getStatus() {
        return status;
    }

    public void setStatus(ChallengeStatus status) {
        this.status = status;
    }

}
