package org.softuni.befit.domain.models.binding;

import java.util.List;

public class MuscleGroupBindingModel {

    private String name;
    private List<String> exercises;

    public MuscleGroupBindingModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getExercises() {
        return this.exercises;
    }

    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }
}
