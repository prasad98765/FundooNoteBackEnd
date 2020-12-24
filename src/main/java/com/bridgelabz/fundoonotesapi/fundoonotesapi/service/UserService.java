package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.UserDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.UserRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private JwtToken jwtToken;


    @Override
    public String addUser(UserDTO userdto){
            Optional<UserDetails> details = userRepository.findByEmail(userdto.email);
            if(details.isPresent()){
                throw new FundooException(FundooException.ExceptionType.USER_ALREADY_REGISTERED,"User Already Registered");
            }
            UserDetails userDetails = new UserDetails(userdto);
            UserDetails userDetails1 =  userRepository.save(userDetails);
            String Token  = jwtToken.generateToken(userDetails1.email);
            sendEmail.sendEmail(userDetails1.email,Token);
            return "Your Account Created Successfully";
    }

    @Override
    public String confirmEmailAccount(String token) {
        System.out.println("abababa"+token);
        try{
            String id = jwtToken.getDataFromToken(token);
            Optional<UserDetails>  users = userRepository.findByEmail(id);
            users.get().setVerified(true);
            userRepository.save(users.get());
            return "User Email Account is Verified";
        }catch (Exception e){
            throw new FundooException(FundooException.ExceptionType.INVALID_LINK,"INVALID LINK");
        }

    }

    @Override
    public UserDetails signIn(String email, String password) {
        Optional<UserDetails>  userDetails = userRepository.findByEmail(email);
        if(userDetails == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_EMAIL,"INVALID EMAIL");
        }
        System.out.println(userDetails);
        if(new BCryptPasswordEncoder().matches(password,userDetails.get().password)){
            return userDetails.get();
        }else{
            throw new FundooException(FundooException.ExceptionType.INVALID_PASSWORD,"INVALID PASSWORD");
        }
    }


    @Override
    public String forgotPassword(String email) {
        Optional<UserDetails>  userDetails = userRepository.findByEmail(email);
        if(userDetails == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_EMAIL,"Invalid Email");
        }
        String Token = jwtToken.generateToken(userDetails.get().email);
        System.out.println(Token);
        sendEmail.forgotPasswordEmail(email,Token);
        return "Reset Password Link Sent to your Email Id";
    }

    @Override
    public String resetPassword(String token) {
        String id = jwtToken.getDataFromToken(token);
        if(id == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_TOKEN,"INVALID TOKEN");
        }
        Optional<UserDetails>  userDetails = userRepository.findByEmail(id);
        if(jwtToken.validateToken(token,userDetails.get().email)) {
            return "Valid Token";
        }else{
            throw new FundooException(FundooException.ExceptionType.INVALID_LINK,"Invalid Link");
        }

    }

    @Override
    public String changePassword(UserDTO userDTO, String token) {
        try{
            String id = jwtToken.getDataFromToken(token);
            Optional<UserDetails>  users = userRepository.findByEmail(id);
            if(users == null){
                throw new FundooException(FundooException.ExceptionType.INVALID_USER,"Invalid User");
            }

            String password = new BCryptPasswordEncoder().encode(userDTO.password);
            users.get().setPassword(password);
            userRepository.save(users.get());
            return "Password Change Successfully";
        }catch (Exception e){
            throw new FundooException(FundooException.ExceptionType.INVALID_DATA,"INVALID LINK");
        }

    }
}
