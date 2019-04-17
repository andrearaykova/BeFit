package org.softuni.befit.service;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.entitites.MuscleGroup;
import org.softuni.befit.domain.models.service.MuscleGroupServiceModel;
import org.softuni.befit.repository.MuscleGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MuscleGroupServiceImpl implements MuscleGroupService {

    private final MuscleGroupRepository muscleGroupRepository;
    private final ModelMapper modelMapper;

    public MuscleGroupServiceImpl(MuscleGroupRepository muscleGroupRepository, ModelMapper modelMapper) {
        this.muscleGroupRepository = muscleGroupRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MuscleGroupServiceModel> findAll() {

        List<MuscleGroup> muscleGroups = muscleGroupRepository.findAll();
        return muscleGroups.stream().map(muscleGroup -> this.modelMapper
                .map(muscleGroup, MuscleGroupServiceModel.class)).collect(Collectors.toList());

    }

    @Override
    public boolean save(MuscleGroupServiceModel muscleGroupServiceModel) {
        MuscleGroup muscleGroup = this.modelMapper.map(muscleGroupServiceModel, MuscleGroup.class);

        try {
            muscleGroupRepository.save(muscleGroup);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public MuscleGroupServiceModel findById(String id) {
        MuscleGroup muscleGroup = muscleGroupRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Muscle group not found"));
        return this.modelMapper.map(muscleGroup, MuscleGroupServiceModel.class);

    }

    @Override
    public MuscleGroupServiceModel editMuscleGroup(String id, MuscleGroupServiceModel muscleGroupServiceModel) {
//        MuscleGroup muscleGroup = this.muscleGroupRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("Muscle group with the given id was not found!"));
//
//
//        muscleGroup.setName(muscleGroupServiceModel.getName());
//
//return this.modelMapper.map(this.muscleGroupRepository.saveAndFlush(muscleGroup), MuscleGroupServiceModel.class);
        return null;
    }

    @Override
    public boolean deleteMuscleGroup(String id) {
        try {
            this.muscleGroupRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


}
