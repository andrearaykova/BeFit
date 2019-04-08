package org.softuni.befit.domain.models.view;

import java.util.List;

public class MuscleGroupViewModel {

    private String description;
    private List<ExerciseViewModel> exercises;

    public MuscleGroupViewModel() {
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExerciseViewModel> getExercises() {
        return this.exercises;
    }

    public void setExercises(List<ExerciseViewModel> exercises) {
        this.exercises = exercises;
    }
}
