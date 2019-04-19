package org.softuni.befit.service;

import org.softuni.befit.domain.models.service.NoteServiceModel;

import java.util.List;

public interface NoteService {

    List<NoteServiceModel> findAll();

    boolean save(NoteServiceModel noteServiceModel);

    NoteServiceModel findById(String id);
}
