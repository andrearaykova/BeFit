package org.softuni.befit.service;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.entitites.Exercise;
import org.softuni.befit.domain.entitites.MuscleGroup;
import org.softuni.befit.domain.models.service.ExerciseServiceModel;
import org.softuni.befit.domain.models.service.MuscleGroupServiceModel;
import org.softuni.befit.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        MuscleGroupServiceModel m = muscleGroupService.findByName(exerciseServiceModel.getMuscleGroup())
                .orElseThrow(() -> new NoSuchElementException("Muscle group not found"));
        MuscleGroup muscleGroup = this.modelMapper.map(m, MuscleGroup.class);

        Exercise exercise = this.modelMapper.map(exerciseServiceModel, Exercise.class);
        exercise.setMuscleGroup(this.modelMapper.map(muscleGroup, MuscleGroup.class));


        try {
            exerciseRepository.save(exercise);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public ExerciseServiceModel findById(String id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such note found"));
        return this.modelMapper.map(exercise, ExerciseServiceModel.class);
    }

    @Override
    public List<ExerciseServiceModel> findByMuscleGroup(String muscleGroupId) {
        MuscleGroupServiceModel m = muscleGroupService.findById(muscleGroupId);

        List<Exercise> exercises = exerciseRepository.findAllByMuscleGroup(this.modelMapper.map(m, MuscleGroup.class));

        return exercises
                .stream()
                .map(e -> this.modelMapper.map(e, ExerciseServiceModel.class))
                .collect(Collectors.toList());
    }

}
