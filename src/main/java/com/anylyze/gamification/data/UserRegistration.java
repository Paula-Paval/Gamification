package com.anylyze.gamification.data;

import java.io.Serializable;

public class UserRegistration implements Serializable {
    private String name;
    private String email;
    private Long credits;
    private Long experience;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        if (credits != null)
            this.credits = credits;
        else
            this.credits = 0L;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        if (experience != null)
            this.experience = experience;
        else
            this.experience = 0L;
    }
}
