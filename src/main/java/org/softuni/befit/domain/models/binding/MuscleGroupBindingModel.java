package org.softuni.befit.domain.models.binding;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class MuscleGroupBindingModel {

    private String name;
    private List<String> exercises;

    public MuscleGroupBindingModel() {
    }

    @NotEmpty
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
