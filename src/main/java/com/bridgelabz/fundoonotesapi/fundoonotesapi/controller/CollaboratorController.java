package com.bridgelabz.fundoonotesapi.fundoonotesapi.controller;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.Response;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collaborator")
public class CollaboratorController {
    @Autowired
    CollaboratorService collaboratorService;

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers(){
        List<UserDetails> details = collaboratorService.getAllUsers();
        Response response = new Response(details);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
