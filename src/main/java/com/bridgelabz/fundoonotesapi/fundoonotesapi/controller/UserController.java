package com.bridgelabz.fundoonotesapi.fundoonotesapi.controller;
import java.net.URI;
import java.util.Optional;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.Response;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.UserDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.UserRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/signUp")
    public ResponseEntity signUp(@Valid @RequestBody UserDTO userDTO, BindingResult result){
        if(result.hasErrors()){
            throw new FundooException(FundooException.ExceptionType.INVALID_DATA,"INVALID DATA");
        }
        Optional<UserDetails> userDetails = userRepository.findByEmail(userDTO.email);
        if(userDetails.isPresent()){
            throw new FundooException(FundooException.ExceptionType.USER_ALREADY_REGISTERED,"User Already Registered");
        }
            String message = userService.addUser(userDTO);
            Response response = new Response(message);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity confirEmailAccont(@RequestParam("token") String token){
        String message = userService.confirmEmailAccount(token);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity signIn(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.email);
        UserDetails userDetails  = userService.signIn(userDTO.email,userDTO.password);
        Response response = new Response(userDetails,"Login Successfully");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestParam("email") String email){
        String message = userService.forgotPassword(email);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("reset-password")
    public ResponseEntity<Object> resetPassword(@RequestParam("token") String token ){
        String message = userService.resetPassword(token);
        String redirectURL = "http://localhost:3000/confirmpassword?token=" + token;
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirectURL));
        return ResponseEntity.status(HttpStatus.FOUND)
                .headers(headers).build();
    }

    @PostMapping("change-password")
    public ResponseEntity changePassword(@RequestBody UserDTO userDTO, @RequestParam("token") String token ){
        String message = userService.changePassword(userDTO,token);
        Response response = new Response(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
