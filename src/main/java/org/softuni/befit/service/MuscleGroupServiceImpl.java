package org.softuni.befit.service;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.entitites.MuscleGroup;
import org.softuni.befit.domain.models.service.MuscleGroupServiceModel;
import org.softuni.befit.repository.MuscleGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

}
