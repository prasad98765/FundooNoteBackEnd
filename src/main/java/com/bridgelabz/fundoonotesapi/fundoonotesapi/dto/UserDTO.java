package com.bridgelabz.fundoonotesapi.fundoonotesapi.dto;
import lombok.*;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Component
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

    public UserDTO(@NotNull String firstName, @NotNull String lastName, @NotNull @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$") String email, @NotNull String password, @NotNull String service) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.service = service;
    }
}
