package org.softuni.befit.repository;

import org.softuni.befit.domain.entitites.Comment;
import org.softuni.befit.domain.entitites.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findAllByExercise(Exercise exercise);
}
