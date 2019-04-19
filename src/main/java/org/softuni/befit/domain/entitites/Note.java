package org.softuni.befit.domain.entitites;

import javax.persistence.*;
import java.text.DateFormat;

@Entity
@Table(name = "notes")
public class Note extends BaseEntity {

    private String name;
    private String description;
    private Exercise exercise;

    public Note() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(targetEntity = Exercise.class)
    public Exercise getExercise() {
        return this.exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
