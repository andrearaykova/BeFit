package org.softuni.befit.domain.models.view;

public class NoteViewModel {

    private String name;
    private String description;
    private String exercise;

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

    public String getExercise() {
        return this.exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
}
