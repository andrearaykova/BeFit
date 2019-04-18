package org.softuni.befit.repository;

import org.softuni.befit.domain.entitites.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, String> {
    Optional<MuscleGroup> findByName(String name);
}
