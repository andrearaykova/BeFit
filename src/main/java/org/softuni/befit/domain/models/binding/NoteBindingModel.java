package org.softuni.befit.domain.models.binding;

import org.softuni.befit.domain.entitites.Exercise;

public class NoteBindingModel {

    private String name;
    private String description;
    private ExerciseBindingModel exercises;

    public NoteBindingModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExerciseBindingModel getExercises() {
        return this.exercises;
    }

    public void setExercises(ExerciseBindingModel exercises) {
        this.exercises = exercises;
    }
}
