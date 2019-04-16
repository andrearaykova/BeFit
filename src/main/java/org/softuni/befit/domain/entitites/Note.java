package org.softuni.befit.domain.entitites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.text.DateFormat;

@Entity
@Table(name = "notes")
public class Note extends BaseEntity {

    private String name;
    private String description;



    public Note() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "descriptions", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
