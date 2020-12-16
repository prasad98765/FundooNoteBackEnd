package com.bridgelabz.fundoonotesapi.fundoonotesapi.controller;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.LabelDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.Response;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

}
