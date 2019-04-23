package org.softuni.befit.service;

import org.modelmapper.ModelMapper;
import org.softuni.befit.domain.entitites.Note;
import org.softuni.befit.domain.entitites.User;
import org.softuni.befit.domain.models.service.NoteServiceModel;
import org.softuni.befit.domain.models.service.UserServiceModel;
import org.softuni.befit.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    public NoteServiceImpl(NoteRepository noteRepository, ModelMapper modelMapper, UserService userService) {
        this.noteRepository = noteRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public List<NoteServiceModel> findAll() {

        List<Note> notes = noteRepository.findAll();
        List<NoteServiceModel> u = notes.stream().map(note -> this.modelMapper
                .map(note, NoteServiceModel.class)).collect(Collectors.toList());


        return u;
    }

    @Override
    public boolean save(NoteServiceModel noteServiceModel) {

        UserServiceModel userServiceModel = userService.findUserByUserName(noteServiceModel.getAuthorName());
        Note note = this.modelMapper.map(noteServiceModel, Note.class);
        User user = this.modelMapper.map(userServiceModel, User.class);
        note.setAuthor(user);

        try {
            noteRepository.save(note);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<NoteServiceModel> findByAuthorName(String name) {
        UserServiceModel userServiceModel = userService.findUserByUserName(name);
        User user = this.modelMapper.map(userServiceModel, User.class);
        List<Note> notes = noteRepository.findByAuthor(user);

        return notes.stream().map(n -> this.modelMapper.map(n, NoteServiceModel.class)).collect(Collectors.toList());

    }

    @Override
    public NoteServiceModel findById(String id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such note found"));
        return this.modelMapper.map(note, NoteServiceModel.class);
    }

    @Override
    public boolean deletePeak(String id){
        try {
            this.noteRepository.deleteById(id);
        } catch (Exception e){
            return false;
        }

        return true;
    }
}
