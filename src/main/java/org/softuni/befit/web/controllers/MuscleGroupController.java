package org.softuni.befit.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.models.binding.MuscleGroupBindingModel;
import org.softuni.befit.domain.models.service.MuscleGroupServiceModel;
import org.softuni.befit.domain.models.view.MuscleGroupViewModel;
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
@RequestMapping("/musclegroup")
public class MuscleGroupController extends BaseController {

    private final ModelMapper modelMapper;
    private final MuscleGroupService muscleGroupService;

    @Autowired
    public MuscleGroupController(ModelMapper modelMapper, MuscleGroupService muscleGroupService) {
        this.modelMapper = modelMapper;
        this.muscleGroupService = muscleGroupService;
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Muscle Group")
    public ModelAndView exercise(ModelAndView modelAndView) {

        List<MuscleGroupServiceModel> muscleGroupServiceModels = muscleGroupService.findAll();
        List<MuscleGroupViewModel> muscleGroupViewModels = muscleGroupServiceModels.stream()
                .map(m -> this.modelMapper.map(m, MuscleGroupViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("model", muscleGroupViewModels);
        return view("muscleGroup", modelAndView);
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Add Muscle Group")
    public ModelAndView showMuscleGroupView() {
        return view("add-muscleGroup");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addMuscleGroup(@ModelAttribute @Valid MuscleGroupBindingModel muscleGroupBindingModel, Errors errors) {

        boolean isSaved = muscleGroupService.save(this.modelMapper.map(muscleGroupBindingModel, MuscleGroupServiceModel.class));

        if (errors.hasErrors() || !isSaved) {
            return redirect("/musclegroup/add");
        }

        return redirect("/musclegroup");
    }


    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteMuscleGroupConfirm(@PathVariable String id) {
        this.muscleGroupService.deleteMuscleGroup(id);

        return super.redirect("/musclegroup");
    }

}
