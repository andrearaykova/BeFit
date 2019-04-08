package org.softuni.befit.domain.models.binding;

import java.util.List;

public class MuscleGroupBindingModel {

    private String description;
    private List<String> exercises;

    public MuscleGroupBindingModel() {
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getExercises() {
        return this.exercises;
    }

    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }
}
