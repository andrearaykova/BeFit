package org.softuni.befit.domain.models.service;

public class ExerciseServiceModel extends BaseServiceModel {

    private String name;
    private String description;
    private String videoURL;

    public ExerciseServiceModel() {
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
}
