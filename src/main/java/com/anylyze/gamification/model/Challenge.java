package com.anylyze.gamification.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "challenges")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "credits")
    private Long credits;
    @Column(name = "experience")
    private Long experience;

    public Challenge() {
    }

    public Challenge(String title, String description, Long credits, Long experience) {
        this.title = title;
        this.description = description;
        this.credits = credits;
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return Objects.equals(id, challenge.id) &&
                Objects.equals(title, challenge.title) &&
                Objects.equals(description, challenge.description) &&
                Objects.equals(credits, challenge.credits) &&
                Objects.equals(experience, challenge.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, credits, experience);
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", credits=" + credits +
                ", experience=" + experience +
                '}';
    }
}
