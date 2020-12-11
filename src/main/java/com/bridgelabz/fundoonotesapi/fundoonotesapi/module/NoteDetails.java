package com.bridgelabz.fundoonotesapi.fundoonotesapi.module;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.NoteDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import java.util.Random;


@ToString
@Getter
@Setter
@Entity
@Table(name = "noteDetails")
public class NoteDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String note_Id;
    public String title;
    public Boolean isPined;
    public String description;
    public String color;
    public Boolean isArchived;
    public Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userDetails_id",nullable = false)
    private UserDetails userDetails;

    public NoteDetails() {
    }

    public NoteDetails(NoteDTO noteDTO,UserDetails userDetails){
        this.createdDate = new Date();
        this.title = noteDTO.title;
        this.description = noteDTO.description;
        this.isPined = noteDTO.isPined;
        this.isArchived = noteDTO.isArchived;
        this.color = noteDTO.color;
        this.userDetails = userDetails;
    }

}
