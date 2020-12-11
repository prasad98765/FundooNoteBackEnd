package com.bridgelabz.fundoonotesapi.fundoonotesapi.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

    public String noteId;

    @NotNull
    public String title;

    @NotNull
    public String description;

    public Boolean isPined;

    public Boolean isArchived;

    public String color;

}
