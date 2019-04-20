package org.softuni.befit.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.models.binding.NoteBindingModel;
import org.softuni.befit.domain.models.service.ExerciseServiceModel;
import org.softuni.befit.domain.models.service.NoteServiceModel;
import org.softuni.befit.domain.models.view.NoteViewModel;
import org.softuni.befit.service.ExerciseService;
import org.softuni.befit.service.NoteService;
import org.softuni.befit.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/note")
public class NoteController extends BaseController {

    private final ModelMapper modelMapper;
    private final NoteService noteService;
    private final ExerciseService exerciseService;

    @Autowired
    public NoteController(ModelMapper modelMapper, NoteService noteService, ExerciseService exerciseService) {
        this.modelMapper = modelMapper;
        this.noteService = noteService;
        this.exerciseService = exerciseService;
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Note")
    public ModelAndView showAllNote(ModelAndView modelAndView, Principal principal) {

        List<NoteServiceModel> noteServiceModels = noteService.findByAuthorName(principal.getName());
        List<NoteViewModel> noteViewModels = noteServiceModels.stream()
                .map(n -> this.modelMapper.map(n, NoteViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("model", noteViewModels);
        return view("note", modelAndView);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Note")
    public ModelAndView noteDescription(ModelAndView modelAndView, @PathVariable("id") String id) {

       NoteServiceModel noteServiceModel = noteService.findById(id);
       NoteViewModel noteViewModel = this.modelMapper.map(noteServiceModel,NoteViewModel.class);
        modelAndView.addObject("model",noteViewModel);
        return view("note-details",modelAndView);
    }


    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Note")
    public ModelAndView showNoteView(ModelAndView modelAndView) {
        return view("add-note");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addNote(@ModelAttribute @Valid NoteBindingModel noteBindingModel, Errors errors, Principal principal) {

        NoteServiceModel noteServiceModel = this.modelMapper.map(noteBindingModel, NoteServiceModel.class);
        noteServiceModel.setAuthorName(principal.getName());
        boolean isSaved = noteService.save(noteServiceModel);

        if (errors.hasErrors() || !isSaved) {
            return redirect("/note/add");
        }

        return redirect("/note");
    }


}
