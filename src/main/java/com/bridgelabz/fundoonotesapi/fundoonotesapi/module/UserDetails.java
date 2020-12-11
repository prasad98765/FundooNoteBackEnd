package com.bridgelabz.fundoonotesapi.fundoonotesapi.module;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class UserDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    @JsonIgnore
    public String password;
    @JsonIgnore
    public String service;
    @JsonIgnore
    public Date createdDate;
    @JsonIgnore
    public boolean isVerified;


    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public UserDetails() {

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails(UserDTO userDTO){
        this.firstName = userDTO.firstName;
        this.lastName = userDTO.lastName;
        this.email = userDTO.email;
        this.createdDate = new Date();
        this.password = new BCryptPasswordEncoder().encode(userDTO.password);
        this.service = userDTO.service;
        this.isVerified = false;
    }
}
