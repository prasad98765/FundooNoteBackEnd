package com.bridgelabz.fundoonotesapi.fundoonotesapi.exception;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = FundooException.class)
    public ResponseEntity<Response> FundooException(FundooException ex){
        Response response = new Response(ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
