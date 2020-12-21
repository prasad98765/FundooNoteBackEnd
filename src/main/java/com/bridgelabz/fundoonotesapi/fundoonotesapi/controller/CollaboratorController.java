package com.bridgelabz.fundoonotesapi.fundoonotesapi.controller;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.Response;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addCollaboratorsNotes/{userId}/{noteId}")
    public ResponseEntity addCollaboratorsNotes(@PathVariable int userId, @PathVariable Long noteId ){
        String message = collaboratorService.addCollaboratorsNotes(userId,noteId);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/removeCollaboratorsNotes/{userId}/{noteId}")
    public ResponseEntity deleteCollaboratorsNotes(@PathVariable int userId, @PathVariable Long noteId ){
        String message = collaboratorService.deleteCollaboratorsNotes(userId,noteId);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/getAllCollaboratornotes")
    public ResponseEntity getAllCollaboratornotes(@RequestParam("token") String token){
        List<NoteDetails> details = collaboratorService.getAllCollaboratornotes(token);
        Response response = new Response(details);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
