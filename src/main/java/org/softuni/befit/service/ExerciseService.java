package org.softuni.befit.service;

import org.softuni.befit.domain.models.service.ExerciseServiceModel;
import org.softuni.befit.domain.models.service.NoteServiceModel;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    List<ExerciseServiceModel> findAll();

    boolean save(ExerciseServiceModel exerciseServiceModel);

    ExerciseServiceModel findById(String id);

    List<ExerciseServiceModel> findByMuscleGroup(String muscleGroupId);

}
