package com.bridgelabz.fundoonotesapi.fundoonotesapi.dto;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import org.springframework.http.HttpStatus;

import java.util.List;

public class Response {
    public String message;
    public int status;
    public UserDetails userDetails;
    public List data;
    public Response(String message, int status){
        this.message = message;
        this.status = status;
    }

    public Response(UserDetails userDetails, String message,int status) {
        this.userDetails = userDetails;
        this.message = message;
        this.status = status;
    }

    public Response(String message){
        this.message = message;
    }

    public Response(List allRecords, int status){
        this.data = allRecords;
        this.status = status;
    }

}
