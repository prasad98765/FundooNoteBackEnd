package com.bridgelabz.fundoonotesapi.fundoonotesapi.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
public class NoteDTO {



    public Long noteId;

    @NotNull
    public String title;

    @NotNull
    public String description;

    public Boolean isPined;

    public Boolean isArchived;

    public String color;

    public Boolean isDeleted;

    public NoteDTO(String title, String description, Boolean isPined, Boolean isArchived, String color, Boolean isDeleted) {
        this.title = title;
        this.description = description;
        this.isPined = isPined;
        this.isArchived = isArchived;
        this.color = color;
        this.isDeleted = isDeleted;
    }



}
