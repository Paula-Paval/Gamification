package com.anylyze.gamification.data;

import com.anylyze.gamification.model.ChallengeStatus;

import java.io.Serializable;

public class ChallengeStatusData implements Serializable {
    private ChallengeStatus status;

    public ChallengeStatusData() {
    }

    public ChallengeStatusData(ChallengeStatus status) {
        this.status = status;
    }

    public ChallengeStatus getStatus() {
        return status;
    }

    public void setStatus(ChallengeStatus status) {
        this.status = status;
    }
}
