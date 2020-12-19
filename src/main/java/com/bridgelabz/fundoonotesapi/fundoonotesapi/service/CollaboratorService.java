package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.NoteRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaboratorService implements CollaboratorServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NoteRepository noteRepository;

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
}
