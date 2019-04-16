package org.softuni.befit.domain.models.view;

public class ExerciseViewModel {

    private String name;
    private String description;
    private String videoURL;
    private String muscleGroup;

    public ExerciseViewModel() {
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

    public String getVideoURL() {
        return this.videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getMuscleGroup() {
        return this.muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
