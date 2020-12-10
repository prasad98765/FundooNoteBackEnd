package com.bridgelabz.fundoonotesapi.fundoonotesapi.module;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.NoteDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@ToString
@Getter
@Setter
@Entity
public class NoteDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;
    public String title;
    public Boolean isPined;
    public String description;
    public String color;
    public Boolean isArchived;
    public Date createdDate;

    public NoteDetails() {
    }

    public NoteDetails(NoteDTO noteDTO){
        this.createdDate = new Date();
        this.title = noteDTO.title;
        this.description = noteDTO.description;
        this.isPined = noteDTO.isPined;
        this.isArchived = noteDTO.isArchived;
        this.color = noteDTO.color;
    }

}
