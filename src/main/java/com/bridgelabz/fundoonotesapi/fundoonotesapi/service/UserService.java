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
            String Token  = jwtToken.generateToken(userDetails1.email);
            sendEmail.sendEmail(userDetails1.email,Token);
            return "Your Account Created Successfully";
        } catch (Exception e){
            throw new FundooException(FundooException.ExceptionType.INVALID_DATA,"INVALID DATA");
        }

    }

    public String confirmEmailAccount(String token) {
        try{
            String id = jwtToken.getDataFromToken(token);
            UserDetails users = userRepository.findByEmail(id);
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


    public String forgotPassword(String email) {
        UserDetails userDetails = userRepository.findByEmail(email);
        if(userDetails == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_EMAIL,"Invalid Email");
        }
        String Token = jwtToken.generateToken(userDetails.email);
        sendEmail.forgotPasswordEmail(email,Token);
        return "Reset Password Link Sent to your Email Id";
    }

    public String resetPassword(String token) {
        String id = jwtToken.getDataFromToken(token);
        UserDetails userDetails = userRepository.findByEmail(id);
        if(jwtToken.validateToken(token,userDetails.email)) {
            return "Valid Token";
        }else{
            throw new FundooException(FundooException.ExceptionType.INVALID_LINK,"Invalid Link");
        }

    }

    public String changePassword(UserDTO userDTO, String token) {
        String id = jwtToken.getDataFromToken(token);
        UserDetails users = userRepository.findById(id);
        if(users == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_USER,"Invalid User");
        }
        String password = new BCryptPasswordEncoder().encode(userDTO.password);
        users.setPassword(password);
        userRepository.save(users);
        return "Password Change Successfully";
    }
}
