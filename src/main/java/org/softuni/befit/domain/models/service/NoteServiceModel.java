package org.softuni.befit.domain.models.service;

public class NoteServiceModel extends BaseServiceModel {

    private String description;
    private ExerciseServiceModel exercises;

    public NoteServiceModel() {
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExerciseServiceModel getExercises() {
        return this.exercises;
    }

    public void setExercises(ExerciseServiceModel exercises) {
        this.exercises = exercises;
    }
}
