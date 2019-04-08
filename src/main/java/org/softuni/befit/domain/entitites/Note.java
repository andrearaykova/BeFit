package org.softuni.befit.domain.entitites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Note extends BaseEntity {

    private String description;
    private Exercise exercises;

    public Note() {
    }


    @Column(name = "descriptions", nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(targetEntity = Exercise.class)
    public Exercise getExercises() {
        return this.exercises;
    }

    public void setExercises(Exercise exercises) {
        this.exercises = exercises;
    }
}
