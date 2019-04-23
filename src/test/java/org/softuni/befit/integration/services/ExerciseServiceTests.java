package org.softuni.befit.integration.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.softuni.befit.domain.entitites.Exercise;
import org.softuni.befit.domain.models.service.ExerciseServiceModel;
import org.softuni.befit.repository.ExerciseRepository;
import org.softuni.befit.repository.MuscleGroupRepository;
import org.softuni.befit.service.ExerciseService;
import org.softuni.befit.service.UserService;
import org.softuni.befit.validation.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExerciseServiceTests {

    @Autowired
    ExerciseService service;

    @MockBean
    ExerciseRepository mockExerciseRepository;

    @MockBean
    UserValidationService mockUserValidation;

    @MockBean
    UserService mockUserService;

    @MockBean
    MuscleGroupRepository muscleGroupRepository;


    private List<Exercise> exercises;

    @Before
    public void setupTest() {
        exercises = new ArrayList<>();
        when(mockExerciseRepository.findAll())
                .thenReturn(exercises);
    }

    @Test
    public void findAllExercise_whenNoExercise() {
        exercises.clear();
        var result = service.findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    public void findAllExercise_when1Oexercise() {
        String name = "";
        String videoURL = "http://video.url";
        String description = "description";

        Exercise exercise = new Exercise();

        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setVideoURL(videoURL);

        exercises.add(exercise);

        var result = service.findAll();
        ExerciseServiceModel exerciseServiceModel = result.get(0);

        assertEquals(1, result.size());
        assertEquals(name, exerciseServiceModel.getName());
        assertEquals(videoURL, exerciseServiceModel.getVideoURL());
        assertEquals(description, exerciseServiceModel.getDescription());
    }



}
