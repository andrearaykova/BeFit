package org.softuni.befit.web.controllers;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.models.binding.CommentBindingModel;
import org.softuni.befit.domain.models.service.CommentServiceModel;
import org.softuni.befit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentApiController extends BaseController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentApiController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{id}")
    public boolean registerComment(@PathVariable String id, HttpServletRequest request, @ModelAttribute CommentBindingModel commentBindingModel) throws IOException {

        String comment = request.getParameter("comment");

        commentBindingModel = new CommentBindingModel(comment,id);

        return this.commentService.saveComment(this.modelMapper.map(commentBindingModel, CommentServiceModel.class));
    }

    @GetMapping(value = "/{id}")
    public String showPaginatedPeakHome(
            @PathVariable("id") String id) {

        List<CommentServiceModel> comments = this.commentService.findAll(id);

        return new Gson().toJson(comments);
    }

}
