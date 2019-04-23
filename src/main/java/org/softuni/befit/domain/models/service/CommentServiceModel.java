package org.softuni.befit.domain.models.service;

public class CommentServiceModel {

    private String comment;
    private String exerciseId;

    public CommentServiceModel() {
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getExercise() {
        return this.exerciseId;
    }

    public void setExercise(String exerciseId) {
        this.exerciseId = exerciseId;
    }
}
