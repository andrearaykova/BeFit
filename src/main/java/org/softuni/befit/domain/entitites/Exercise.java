package org.softuni.befit.domain.entitites;

import javax.persistence.*;

@Entity
@Table(name = "exercises")
public class Exercise extends BaseEntity {

    private String name;
    private String description;
    private String videoURL;
    private MuscleGroup muscleGroup;

    public Exercise() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "video_URL", nullable = false, unique = true, updatable = false)
    public String getVideoURL() {
        return this.videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    @ManyToOne(targetEntity = MuscleGroup.class)
    public MuscleGroup getMuscleGroup() {
        return this.muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
