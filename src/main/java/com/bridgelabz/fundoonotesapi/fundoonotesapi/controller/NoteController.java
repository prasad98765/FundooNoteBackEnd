package com.bridgelabz.fundoonotesapi.fundoonotesapi.controller;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.NoteDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.Response;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/saveNote")
    public Response saveNOte(@Valid @RequestBody NoteDTO noteDTO, @RequestParam("token") String token, BindingResult result){
        if(result.hasErrors()){
            throw new FundooException(FundooException.ExceptionType.INVALID_DATA,"INVALID DATA");
        }
        noteService.saveNote(noteDTO,token);
        Response response = new Response("Note_Added_Successfully",HttpStatus.OK.value());
        return response;
    }

    @GetMapping("/allNoteList")
    public Response noteList(@RequestParam("token") String token){
        List noteList = noteService.getNoteList(token);
        Response response = new Response(noteList,HttpStatus.OK.value());
        return response;
    }

    @PostMapping("/updateNote")
    public Response updateNote(@RequestParam("token") String token , @RequestBody NoteDTO noteDTO ){
        String message = noteService.updateNote(noteDTO);
        Response response = new Response(message,HttpStatus.OK.value());
        return response;
    }

    @PostMapping("/pinUnpinNote")
    public Response updatePin(@RequestParam("token") String token , @RequestBody NoteDTO noteDTO ){
        String message = noteService.updatePin(noteDTO);
        Response response = new Response(message,HttpStatus.OK.value());
        return response;
    }

    @GetMapping("/pinNoteList")
    public Response pinNoteList(@RequestParam("token") String token){
        List message = noteService.pinNoteList(token);
        Response response = new Response(message,HttpStatus.OK.value());
        return response;
    }

    @PostMapping("/ArchivedUnArchivedNote")
    public Response updateArchived(@RequestParam("token") String token , @RequestBody NoteDTO noteDTO ){
        String message = noteService.updateArchived(noteDTO);
        Response response = new Response(message,HttpStatus.OK.value());
        return response;
    }

    @GetMapping("/archivedNoteList")
    public Response archivedNoteList(@RequestParam("token") String token){
        List message = noteService.archivedNoteList(token);
        Response response = new Response(message,HttpStatus.OK.value());
        return response;
    }

    @PostMapping("/colorNote")
    public Response updateColor(@RequestParam("token") String token , @RequestBody NoteDTO noteDTO ){
        String message = noteService.updateColor(noteDTO);
        Response response = new Response(message,HttpStatus.OK.value());
        return response;
    }


}
