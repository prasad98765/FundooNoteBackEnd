package com.bridgelabz.fundoonotesapi.fundoonotesapi.controller;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.NoteDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.Response;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notes")
@Slf4j
public class NoteController {

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(NoteController.class);
    @Autowired
    private NoteService noteService;

    @PostMapping("/saveNote")
    public ResponseEntity saveNote(@Valid @RequestBody NoteDTO noteDTO, @RequestParam("token") String token, BindingResult result){
        if(result.hasErrors()){
            throw new FundooException(FundooException.ExceptionType.INVALID_DATA,"INVALID DATA");
        }
        noteService.saveNote(noteDTO,token);
        Response response = new Response("Note_Added_Successfully");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/updateNote")
    public ResponseEntity updateNote(@RequestHeader("token") String token , @RequestBody NoteDTO noteDTO){
        String message = noteService.updateNote(noteDTO);
        Response response = new Response(message);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping("/pinUnpinNote/{noteId}")
    public ResponseEntity updatePin(@RequestHeader("token") String token , @PathVariable Long noteId,  @RequestBody boolean isPined ){
        String message = noteService.updatePin(noteId,isPined);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/ArchivedUnArchivedNote/{noteId}")
    public ResponseEntity updateArchived(@RequestHeader("token") String token,@PathVariable Long noteId , @RequestBody boolean isArchived ){
        String message = noteService.updateArchived(noteId,isArchived);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/colorNote/{noteId}")
    public ResponseEntity<Response> updateColor(@RequestHeader("token") String token ,@PathVariable Long noteId, @RequestBody String color ){
        String message = noteService.updateColor(noteId,color);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/trashNote/{noteId}")
    public ResponseEntity updateTrashNote(@RequestHeader("token") String token, @PathVariable Long noteId , @RequestBody boolean isDeleted){
        String message = noteService.updateTrashNote(noteId,isDeleted);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/deleteForeverNote/{noteId}")
    public ResponseEntity deleteForeverNote(@RequestHeader("token") String token,@PathVariable Long noteId){
        String message = noteService.deleteForeverNote(noteId);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/allNoteList")
    public ResponseEntity<Response> noteList(@RequestParam("token") String token){
        List noteList = noteService.getNoteList(token);
        Response response = new Response(noteList);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/trashNoteList")
    public ResponseEntity<Response> trashNoteList(@RequestParam("token") String token){
        List message = noteService.trashNoteList(token);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/archivedNoteList")
    public ResponseEntity archivedNoteList(@RequestParam("token") String token){
        List message = noteService.archivedNoteList(token);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/pinNoteList")
    public ResponseEntity pinNoteList(@RequestParam("token") String token){
        List message = noteService.pinNoteList(token);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
