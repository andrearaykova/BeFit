package org.softuni.befit.repository;

import org.softuni.befit.domain.entitites.Exercise;
import org.softuni.befit.domain.entitites.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, String> {
    List<Exercise> findAllByMuscleGroup(MuscleGroup muscleGroup);
}
