package org.softuni.befit.integration.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.befit.domain.entitites.MuscleGroup;
import org.softuni.befit.domain.models.service.ExerciseServiceModel;
import org.softuni.befit.domain.models.service.MuscleGroupServiceModel;
import org.softuni.befit.repository.MuscleGroupRepository;
import org.softuni.befit.service.MuscleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MuscleGroupServiceTests {

    @Autowired
    private MuscleGroupService service;

    @MockBean
    private MuscleGroupRepository mockMuscleGroupRepository;

    @Test
    public void saveMuscleGroup_whenValid() {
        MuscleGroupServiceModel muscleGroupServiceModel = new MuscleGroupServiceModel();
        when(mockMuscleGroupRepository.save(any()))
                .thenReturn(new MuscleGroup());

        service.save(muscleGroupServiceModel);
        verify(mockMuscleGroupRepository)
                .save(any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveMuscleGroup_whenNull_throw() {
        service.save(null);
        verify(mockMuscleGroupRepository)
                .save(any());
    }
}

