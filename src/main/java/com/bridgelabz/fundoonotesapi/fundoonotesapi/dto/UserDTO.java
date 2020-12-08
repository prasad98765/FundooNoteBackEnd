package com.bridgelabz.fundoonotesapi.fundoonotesapi.dto;
import lombok.*;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull
    public String firstName;
    @NotNull
    public String lastName;
    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    public String email;
    @NotNull
    public String password;
    @NotNull
    public String service;

}
