package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.NoteDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.NoteRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.UserRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List getNoteList(String token) {
        String id = jwtToken.getDataFromToken(token);
        if(id == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_TOKEN,"Invalid Token");
        }
        UserDetails users = userRepository.findByEmail(id);
        List<NoteDetails> noteList = noteRepository.findByUserDetailsId(users.id);
        return noteList;
    }


    public String updateNote(NoteDTO noteDTO) {
        NoteDetails details = noteRepository.findByNote_Id(noteDTO.noteId);
        if(details == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
        }
        details.setTitle(noteDTO.title);
        details.setDescription(noteDTO.description);
        noteRepository.save(details);
        return "Note Updated";
    }

    public String updatePin(NoteDTO noteDTO) {
        NoteDetails details = noteRepository.findByNote_Id(noteDTO.noteId);
        if(details == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
        }
        details.setPined(noteDTO.isPined);
        noteRepository.save(details);
        return "PinNote Updated";
    }

    public List pinNoteList(String token) {
        String id = jwtToken.getDataFromToken(token);
        if(id == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_TOKEN,"Invalid Token");
        }
        UserDetails users = userRepository.findByEmail(id);
        List<NoteDetails> noteList = noteRepository.findByUserDetailsIdAndIsPinedTrue(users.id);
        return noteList;
    }
}

