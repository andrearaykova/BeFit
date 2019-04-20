package org.softuni.befit.domain.models.view;

public class CommentViewModel {
    private String comment;
    private String exerciseId;

    public CommentViewModel() {
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
