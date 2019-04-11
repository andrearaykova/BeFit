package org.softuni.befit.domain.models.view;

import java.util.List;

public class MuscleGroupViewModel {

    private String name;
    private List<ExerciseViewModel> exercises;

    public MuscleGroupViewModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseViewModel> getExercises() {
        return this.exercises;
    }

    public void setExercises(List<ExerciseViewModel> exercises) {
        this.exercises = exercises;
    }
}
