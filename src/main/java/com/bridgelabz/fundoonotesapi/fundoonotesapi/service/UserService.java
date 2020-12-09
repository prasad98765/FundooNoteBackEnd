package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.UserDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.UserRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private JwtToken jwtToken;


    public String addUser(UserDTO userdto){
        try{
            UserDetails userDetails = new UserDetails(userdto);
            UserDetails userDetails1 =  userRepository.save(userDetails);
            String Token = jwtToken.generateToken(userDetails1.id);
            sendEmail.sendEmail(userDetails1.email,Token);
            return "Your Account Created Successfully";
        } catch (Exception e){
            throw new FundooException(FundooException.ExceptionType.INVALID_DATA,"INVALID DATA");
        }

    }

    public String confirmEmailAccount(String token) {
        try{
            String id = jwtToken.getDataFromToken(token);
            UserDetails users = userRepository.findById(id);
            users.setVerified(true);
            userRepository.save(users);
            return "User Email Account is Verified";
        }catch (Exception e){
            throw new FundooException(FundooException.ExceptionType.INVALID_LINK,"INVALID LINK");
        }

    }

    public UserDetails signIn(String email, String password) {
        UserDetails userDetails = userRepository.findByEmail(email);
        if(new BCryptPasswordEncoder().matches(password,userDetails.password)){
            return userDetails;
        }else{
            throw new FundooException(FundooException.ExceptionType.INVALID_PASSWORD,"INVALID PASSWORD");
        }
    }
}
