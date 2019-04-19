package org.softuni.befit.service;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.entitites.Note;
import org.softuni.befit.domain.models.service.NoteServiceModel;
import org.softuni.befit.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;
    private final ExerciseService exerciseService;

    public NoteServiceImpl(NoteRepository noteRepository, ModelMapper modelMapper, ExerciseService exerciseService) {
        this.noteRepository = noteRepository;
        this.modelMapper = modelMapper;
        this.exerciseService = exerciseService;
    }


    @Override
    public List<NoteServiceModel> findAll() {

        List<Note> notes = noteRepository.findAll();
        return notes.stream().map(note -> this.modelMapper
                .map(note, NoteServiceModel.class)).collect(Collectors.toList());

    }

    @Override
    public boolean save(NoteServiceModel noteServiceModel) {
        Note note = this.modelMapper.map(noteServiceModel, Note.class);

        try {
            noteRepository.save(note);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public NoteServiceModel findById(String id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found"));
        return this.modelMapper.map(note, NoteServiceModel.class);
    }
}
