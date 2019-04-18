package org.softuni.befit.service;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.entitites.Exercise;
import org.softuni.befit.domain.models.service.ExerciseServiceModel;
import org.softuni.befit.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final MuscleGroupService muscleGroupService;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, MuscleGroupService muscleGroupService, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.muscleGroupService = muscleGroupService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExerciseServiceModel> findAll() {

        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream().map(exercise -> this.modelMapper
                .map(exercise, ExerciseServiceModel.class)).collect(Collectors.toList());

    }

    @Override
    public boolean save(ExerciseServiceModel exerciseServiceModel) {
        Exercise exercise = this.modelMapper.map(exerciseServiceModel, Exercise.class);


        try {
            exerciseRepository.save(exercise);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
