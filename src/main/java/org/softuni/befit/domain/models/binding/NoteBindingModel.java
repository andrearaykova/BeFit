package org.softuni.befit.domain.models.binding;

import org.softuni.befit.domain.entitites.Exercise;

public class NoteBindingModel {

    private String name;
    private String description;

    public NoteBindingModel() {
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
