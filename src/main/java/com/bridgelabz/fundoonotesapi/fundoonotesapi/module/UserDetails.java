package com.bridgelabz.fundoonotesapi.fundoonotesapi.module;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class UserDetails implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String service;
    public Date createdDate;
    public boolean isVerified;


    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public UserDetails() {

    }

    public UserDetails(UserDTO userDTO){
        this.firstName = userDTO.firstName;
        this.lastName = userDTO.lastName;
        this.email = userDTO.email;
        this.createdDate = new Date();
        this.password = userDTO.password;
        this.service = userDTO.service;
        this.isVerified = false;
    }
}
