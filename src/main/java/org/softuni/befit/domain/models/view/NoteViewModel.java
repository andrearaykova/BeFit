package org.softuni.befit.domain.models.view;

public class NoteViewModel {

    private String description;
    private ExerciseViewModel exercises;

    public NoteViewModel() {
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExerciseViewModel getExercises() {
        return this.exercises;
    }

    public void setExercises(ExerciseViewModel exercises) {
        this.exercises = exercises;
    }
}
