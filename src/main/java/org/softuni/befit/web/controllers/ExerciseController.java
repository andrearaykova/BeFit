package org.softuni.befit.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.models.binding.ExerciseBindingModel;
import org.softuni.befit.domain.models.service.ExerciseServiceModel;
import org.softuni.befit.domain.models.service.MuscleGroupServiceModel;
import org.softuni.befit.domain.models.view.ExerciseViewModel;
import org.softuni.befit.service.ExerciseService;
import org.softuni.befit.service.MuscleGroupService;
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
    private final MuscleGroupService muscleGroupService;

    @Autowired
    public ExerciseController(ModelMapper modelMapper, ExerciseService exerciseService, MuscleGroupService muscleGroupService) {
        this.modelMapper = modelMapper;
        this.exerciseService = exerciseService;
        this.muscleGroupService = muscleGroupService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Exercise")
    public ModelAndView exercise(ModelAndView modelAndView, @PathVariable("id") String id) {

        List<ExerciseServiceModel> exerciseServiceModels = exerciseService.findAll();
        List<ExerciseViewModel> exerciseViewModels = exerciseServiceModels.stream()
                .map(ex -> this.modelMapper.map(ex, ExerciseViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("model", exerciseViewModels);
        return view("exercise", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView exerciseDescription(ModelAndView modelAndView, @PathVariable("id") String id) {

        ExerciseServiceModel exerciseServiceModel = exerciseService.findById(id);
        ExerciseViewModel exerciseViewModel = this.modelMapper.map(exerciseServiceModel,ExerciseViewModel.class);
        modelAndView.addObject("model",exerciseViewModel);
        return view("exercise-details",modelAndView);
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Exercise")
    public ModelAndView showExerciseView(ModelAndView modelAndView) {
        List<MuscleGroupServiceModel> muscleGroupServiceModels = muscleGroupService.findAll();
        List<String> muscleGroupName = muscleGroupServiceModels.stream().map(MuscleGroupServiceModel::getName).collect(Collectors.toList());

        modelAndView.addObject("model", muscleGroupName);
        return view("add-exercise", modelAndView);
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
