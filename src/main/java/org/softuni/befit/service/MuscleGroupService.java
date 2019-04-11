package org.softuni.befit.service;

import org.softuni.befit.domain.models.service.MuscleGroupServiceModel;

import java.util.List;

public interface MuscleGroupService {

    List<MuscleGroupServiceModel> findAll();

    boolean save(MuscleGroupServiceModel muscleGroupServiceModel);
}
