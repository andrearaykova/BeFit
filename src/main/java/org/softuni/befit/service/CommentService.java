package org.softuni.befit.service;

import org.softuni.befit.domain.models.service.CommentServiceModel;

import java.util.List;

public interface CommentService {
    boolean saveComment(CommentServiceModel commentServiceModel);

    List<CommentServiceModel> findAll(String exerciseId);
}
