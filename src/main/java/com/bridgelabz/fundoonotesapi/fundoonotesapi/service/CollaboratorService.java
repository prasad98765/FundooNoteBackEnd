package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaboratorService implements CollaboratorServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public List getAllUsers() {
        List<UserDetails> userDetails = userRepository.findAll();
        return userDetails;
    }
}
