package com.bridgelabz.fundoonotesapi.fundoonotesapi.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Component
public class LabelDTO {
    @NotNull
    public  String labelName;

    public LabelDTO(@NotNull String labelName) {
        this.labelName = labelName;
    }
}
