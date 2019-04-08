package org.softuni.befit.domain.models.service;

import java.util.List;

public class MuscleGroupServiceModel extends BaseServiceModel {

    private String description;
    private List<ExerciseServiceModel> exercises;

    public MuscleGroupServiceModel() {
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExerciseServiceModel> getExercises() {
        return this.exercises;
    }

    public void setExercises(List<ExerciseServiceModel> exercises) {
        this.exercises = exercises;
    }
}
