package org.softuni.befit.domain.models.service;

import java.util.List;

public class MuscleGroupServiceModel extends BaseServiceModel {

    private String name;
    private List<ExerciseServiceModel> exercises;

    public MuscleGroupServiceModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseServiceModel> getExercises() {
        return this.exercises;
    }

    public void setExercises(List<ExerciseServiceModel> exercises) {
        this.exercises = exercises;
    }
}
