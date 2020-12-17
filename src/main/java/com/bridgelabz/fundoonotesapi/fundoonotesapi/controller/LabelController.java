package com.bridgelabz.fundoonotesapi.fundoonotesapi.controller;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.LabelDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.Response;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/noteLabel")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @PostMapping("/addLabel")
    public ResponseEntity saveLabel(@RequestParam("token") String token, @Valid @RequestBody LabelDTO labelDTO){
        String message = labelService.saveLabel(token,labelDTO);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/getNoteLabelList")
    public ResponseEntity noteLabelList(@RequestParam("token") String token){
        List noteList = labelService.noteLabelList(token);
        Response response = new Response(noteList);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/updateNoteLabel/{id}")
    public ResponseEntity updateLabel(@RequestParam("token") String token,@PathVariable Long id, @RequestBody String labelName ){
        String message = labelService.updatelabel(id,labelName);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/addLabelToNotes/{noteId}/{labelId}")
    public ResponseEntity addLabelToNotes(@RequestParam("token") String token,@PathVariable Long noteId, @PathVariable Long labelId ){
        String message = labelService.addLabelToNotes(noteId,labelId);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/removeLabelToNotes/{noteId}/{labelId}")
    public ResponseEntity removeLabelToNotes(@RequestParam("token") String token,@PathVariable Long noteId, @PathVariable Long labelId ){
        String message = labelService.removeLabelToNotes(noteId,labelId);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
