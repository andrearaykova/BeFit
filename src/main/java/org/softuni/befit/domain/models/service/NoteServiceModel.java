package org.softuni.befit.domain.models.service;

public class NoteServiceModel extends BaseServiceModel {

    private String name;
    private String description;

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

}
