package com.bridgelabz.fundoonotesapi.fundoonotesapi.controller;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.Response;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.UserDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signUp")
    public Response signUp(@Valid @RequestBody UserDTO userDTO, BindingResult result){
        if(result.hasErrors()){
            throw new FundooException(FundooException.ExceptionType.INVALID_DATA,"INVALID DATA");
        }
        String message = userService.addUser(userDTO);
        Response response = new Response(message, HttpStatus.OK);
        return response;
    }

    @GetMapping("/confirm-account")
    public Response confirEmailAccont(@RequestParam("token") String token){
        String message = userService.confirmEmailAccount(token);
        Response response = new Response(message, HttpStatus.OK);
        return response;
    }
}
