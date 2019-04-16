package org.softuni.befit.domain.models.view;

public class NoteViewModel {

    private String name;
    private String description;
    private ExerciseViewModel exercises;

    public NoteViewModel() {
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

    public ExerciseViewModel getExercises() {
        return this.exercises;
    }

    public void setExercises(ExerciseViewModel exercises) {
        this.exercises = exercises;
    }
}
