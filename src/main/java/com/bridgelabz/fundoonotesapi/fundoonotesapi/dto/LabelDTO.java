package com.bridgelabz.fundoonotesapi.fundoonotesapi.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
public class LabelDTO {
    @NotNull
    public  String labelName;
}
