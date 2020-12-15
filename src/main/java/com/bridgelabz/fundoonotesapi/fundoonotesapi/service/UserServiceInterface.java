package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.UserDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;

public interface UserServiceInterface {
    String addUser(UserDTO userdto);
    String confirmEmailAccount(String token);
    UserDetails signIn(String email, String password);
    String forgotPassword(String email);
    String resetPassword(String token);
    String changePassword(UserDTO userDTO, String token);
}
