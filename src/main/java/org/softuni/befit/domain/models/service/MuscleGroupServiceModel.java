package org.softuni.befit.domain.models.service;

import java.util.List;

public class MuscleGroupServiceModel extends BaseServiceModel {

    private String id;
    private String name;


    public MuscleGroupServiceModel() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
