package org.softuni.befit.domain.models.binding;

public class CommentBindingModel {

    private String comment;
    private String exerciseId;

    public CommentBindingModel(String comment, String exerciseId) {
        this.comment = comment;
        this.exerciseId = exerciseId;
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
