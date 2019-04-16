package org.softuni.befit.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.models.binding.ExerciseBindingModel;
import org.softuni.befit.domain.models.service.ExerciseServiceModel;
import org.softuni.befit.domain.models.view.ExerciseViewModel;
import org.softuni.befit.service.ExerciseService;
import org.softuni.befit.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/exercise")
public class ExerciseController extends BaseController {

    private final ModelMapper modelMapper;
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ModelMapper modelMapper, ExerciseService exerciseService) {
        this.modelMapper = modelMapper;
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Exercise")
    public ModelAndView exercise(ModelAndView modelAndView,@PathVariable("id") String id) {

        List<ExerciseServiceModel> exerciseServiceModels = exerciseService.findAll();
        List<ExerciseViewModel> exerciseViewModels = exerciseServiceModels.stream()
                .map(ex -> this.modelMapper.map(ex, ExerciseViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("model", exerciseViewModels);
        return view("exercise", modelAndView);
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showExerciseView() {
        return view("add-exercise");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addExercise(@ModelAttribute @Valid ExerciseBindingModel exerciseBindingModel, Errors errors) {

        boolean isSaved = exerciseService.save(this.modelMapper.map(exerciseBindingModel, ExerciseServiceModel.class));

        if (errors.hasErrors() || !isSaved) {
            return redirect("/exercise/add");
        }

        return redirect("/exercise");
    }

}
