package org.softuni.befit.domain.entitites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "muscle_groups")
public class MuscleGroup extends BaseEntity {

    private String name;
    private List<Exercise> exercises;

    public MuscleGroup() {
        this.exercises = new ArrayList<>();
    }

    @Column(nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Exercise.class)
    public List<Exercise> getExercises() {
        return this.exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
