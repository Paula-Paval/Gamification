package com.anylyze.gamification.model;

import javax.persistence.*;

@Entity
@Table(name = "accepted_challenges")
public class AcceptedChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "challenge_id")
    private Long challengeId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ChallengeStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public AcceptedChallenge() {
    }

    public AcceptedChallenge(Long challengeId, ChallengeStatus status) {
        this.challengeId = challengeId;
        this.status = status;
    }

    public AcceptedChallenge(Long challengeId, ChallengeStatus status, User user) {
        this.challengeId = challengeId;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
