package com.bridgelabz.fundoonotesapi.fundoonotesapi.dto;

import org.springframework.http.HttpStatus;

public class Response {
    public String message;
    public HttpStatus status;

    public Response(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }
}
