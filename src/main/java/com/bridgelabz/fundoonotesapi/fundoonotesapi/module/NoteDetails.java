package com.bridgelabz.fundoonotesapi.fundoonotesapi.module;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.NoteDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "noteDetails")
public class NoteDetails implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long note_Id;
    public String title;
    public Boolean isPined;
    public String description;
    public String color;
    public Boolean isArchived;
    public Boolean isDeleted;
    public Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userDetails_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserDetails userDetails;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "LabelDetails_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<LabelDetails> labelDetail;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "userDetails_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<UserDetails> collaborators;

    public void collaboratorsDetails(UserDetails userDetails) {
        if(userDetails == null){
            collaborators = new ArrayList<>();
        }
        collaborators.add(userDetails);
    }

    public void removeCollaboratorsDetail(UserDetails userDetails) {
        collaborators.remove(userDetails);
    }


    public void LabelDetails(LabelDetails labelDetails) {
        if(labelDetails == null){
            labelDetail = new ArrayList<>();
        }
        labelDetail.add(labelDetails);
    }

    public void removeLabelDetails(LabelDetails labelDetails) {
        labelDetail.remove(labelDetails);
    }


    public List<LabelDetails> getLabelDetail() {
        return labelDetail;
    }

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
