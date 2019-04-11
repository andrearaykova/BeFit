package org.softuni.befit.repository;

import org.softuni.befit.domain.entitites.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, String> {

}
