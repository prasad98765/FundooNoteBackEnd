package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.NoteDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.NoteRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.UserRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtToken jwtToken;

    public void saveNote(NoteDTO noteDTO, String token) {
        String id = jwtToken.getDataFromToken(token);
        UserDetails users = userRepository.findByEmail(id);
        NoteDetails noteDetails = new NoteDetails(noteDTO,users);
        noteRepository.save(noteDetails);
    }
}
