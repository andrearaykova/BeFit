package org.softuni.befit.service;

import org.softuni.befit.domain.models.service.NoteServiceModel;

import java.util.List;

public interface NoteService {

    List<NoteServiceModel> findAll();

    boolean save(NoteServiceModel noteServiceModel);

    List<NoteServiceModel> findByAuthorName(String name);

    NoteServiceModel findById(String id);

    boolean deletePeak(String id);
}
