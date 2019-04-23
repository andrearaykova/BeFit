package org.softuni.befit.repository;

import org.softuni.befit.domain.entitites.Note;
import org.softuni.befit.domain.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {
    List<Note> findByAuthor(User user);
}
