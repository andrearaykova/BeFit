package org.softuni.befit.domain.models.view;


public class ExerciseViewModel {

    private String id;
    private String name;
    private String description;
    private String videoURL;
    private String muscleGroup;

    public ExerciseViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
        //https://www.youtube.com/watch?v=BplsGX5eLLo
        int lastIndex = videoURL.lastIndexOf("=");
        String videoCode = videoURL.substring(lastIndex + 1);

        videoURL = "https://www.youtube.com/embed/" + videoCode;
        this.videoURL = videoURL;
    }

    public String getMuscleGroup() {
        return this.muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
