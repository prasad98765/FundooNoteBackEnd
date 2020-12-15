package com.bridgelabz.fundoonotesapi.fundoonotesapi.dto;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;

import java.util.List;

public class Response {
    public String message;
    public int status;
    public UserDetails userDetails;
    public List data;

    public Response(String message){
        this.message = message;
    }

    public Response(UserDetails userDetails, String message) {
        this.userDetails = userDetails;
        this.message = message;
    }

    public Response(List allRecords){
        this.data = allRecords;
    }

}
