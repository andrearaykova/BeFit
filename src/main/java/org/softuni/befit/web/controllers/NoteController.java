package org.softuni.befit.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.models.binding.ExerciseBindingModel;
import org.softuni.befit.domain.models.binding.NoteBindingModel;
import org.softuni.befit.domain.models.service.ExerciseServiceModel;
import org.softuni.befit.domain.models.service.NoteServiceModel;
import org.softuni.befit.domain.models.view.ExerciseViewModel;
import org.softuni.befit.domain.models.view.NoteViewModel;
import org.softuni.befit.service.NoteService;
import org.softuni.befit.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/note")
public class NoteController extends BaseController {

    private final ModelMapper modelMapper;
    private final NoteService noteService;

    @Autowired
    public NoteController(ModelMapper modelMapper, NoteService noteService) {
        this.modelMapper = modelMapper;
        this.noteService = noteService;
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Note")
    public ModelAndView note(ModelAndView modelAndView) {

        List<NoteServiceModel> noteServiceModels = noteService.findAll();
        List<NoteViewModel> noteViewModels = noteServiceModels.stream()
                .map(n -> this.modelMapper.map(n, NoteViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("model", noteViewModels);
        return view("note", modelAndView);
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Add Note")
    public ModelAndView showNoteView() {
        return view("add-note");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addNote(@ModelAttribute @Valid NoteBindingModel noteBindingModel, Errors errors) {

        boolean isSaved = noteService.save(this.modelMapper.map(noteBindingModel, NoteServiceModel.class));

        if (errors.hasErrors() || !isSaved) {
            return redirect("/note/add");
        }

        return redirect("/note");
    }

}
