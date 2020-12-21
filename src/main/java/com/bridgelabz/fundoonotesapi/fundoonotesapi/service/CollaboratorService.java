package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.NoteRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.UserRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaboratorService implements CollaboratorServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    JwtToken jwtToken;

    @Override
    public List getAllUsers() {
        List<UserDetails> userDetails = userRepository.findAll();
        return userDetails;
    }

    @Override
    public String addCollaboratorsNotes(int userId, Long noteId) {
        NoteDetails details = noteRepository.findByNote_Id(noteId);
        if(details == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
        }
        UserDetails userDetails = userRepository.findById(userId);
        if(userDetails == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
        }
        details.collaboratorsDetails(userDetails);
        noteRepository.save(details);
        return "Added collaborator to note";
    }

    @Override
    public String deleteCollaboratorsNotes(int userId, Long noteId) {
        NoteDetails details = noteRepository.findByNote_Id(noteId);
        if(details == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
        }
        UserDetails userDetails = userRepository.findById(userId);
        if(userDetails == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
        }
        details.removeCollaboratorsDetail(userDetails);
        noteRepository.save(details);
        return "Delete collaborator to note";
    }

    @Override
    public List<NoteDetails> getAllCollaboratornotes(String token) {
        String id = jwtToken.getDataFromToken(token);
        UserDetails users = userRepository.findByEmail(id);
        List<NoteDetails> userDetails = noteRepository.findByCollaboratorsNote_Id(users.id);
        return userDetails;
    }
}
