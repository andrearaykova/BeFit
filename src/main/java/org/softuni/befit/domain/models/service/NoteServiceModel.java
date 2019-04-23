package org.softuni.befit.domain.models.service;

public class NoteServiceModel extends BaseServiceModel {

    private String id;
    private String name;
    private String description;
    private String authorName;

    public NoteServiceModel() {
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

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
