package org.softuni.befit.service;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.entitites.Comment;
import org.softuni.befit.domain.entitites.Exercise;
import org.softuni.befit.domain.models.service.CommentServiceModel;
import org.softuni.befit.domain.models.service.ExerciseServiceModel;
import org.softuni.befit.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final ExerciseService exerciseService;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(ExerciseService exerciseService, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean saveComment(CommentServiceModel commentServiceModel) {
        ExerciseServiceModel exerciseServiceModel = this.exerciseService.findById(commentServiceModel.getExercise());

        String comment = commentServiceModel.getComment();
        if (!comment.equals("") && comment.length() <= 255) {
            Comment c = this.modelMapper.map(commentServiceModel, Comment.class);
            c.setExercise(this.modelMapper.map(exerciseServiceModel, Exercise.class));

            try {
                commentRepository.save(c);
            } catch (Exception e) {
                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public List<CommentServiceModel> findAll(String exerciseId) {
        ExerciseServiceModel exerciseServiceModel = exerciseService.findById(exerciseId);
        Exercise exercise = this.modelMapper.map(exerciseServiceModel, Exercise.class);

        List<Comment> comments = this.commentRepository.findAllByExercise(exercise);

        return comments
                .stream()
                .map(p -> this.modelMapper.map(p, CommentServiceModel.class))
                .collect(Collectors.toList());
    }
}
