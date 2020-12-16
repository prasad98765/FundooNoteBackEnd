package com.bridgelabz.fundoonotesapi.fundoonotesapi.module;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.NoteDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Boolean isDeleted;
    public Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userDetails_id",nullable = false)
    private UserDetails userDetails;


    public NoteDetails() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPined(Boolean pined) {
        isPined = pined;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public NoteDetails(NoteDTO noteDTO, UserDetails userDetails){
        this.createdDate = new Date();
        this.title = noteDTO.title;
        this.description = noteDTO.description;
        this.isPined = noteDTO.isPined;
        this.isArchived = noteDTO.isArchived;
        this.color = noteDTO.color;
        this.isDeleted = noteDTO.isDeleted;
        this.userDetails = userDetails;
    }

}
