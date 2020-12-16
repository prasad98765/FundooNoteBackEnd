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

@Service
public class NoteService implements NoteServiceInterface {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtToken jwtToken;

    @Override
    public void saveNote(NoteDTO noteDTO, String token) {
        String id = jwtToken.getDataFromToken(token);
        UserDetails users = userRepository.findByEmail(id);
        NoteDetails noteDetails = new NoteDetails(noteDTO,users);
        noteRepository.save(noteDetails);
    }

    @Override
    public List getNoteList(String token) {
        String id = jwtToken.getDataFromToken(token);
        if(id == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_TOKEN,"Invalid Token");
        }
        UserDetails users = userRepository.findByEmail(id);
        List<NoteDetails> noteList = noteRepository.findByUserDetailsId(users.id);
        return noteList;
    }
    @Override
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

    @Override
    public String updatePin(Long noteId, boolean isPined) {
        NoteDetails details = noteRepository.findByNote_Id(noteId);
        if(details == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
        }
        details.setPined(isPined);
        noteRepository.save(details);
        return "PinNote Updated";
    }

    @Override
    public List pinNoteList(String token) {
        String id = jwtToken.getDataFromToken(token);
        if(id == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_TOKEN,"Invalid Token");
        }
        UserDetails users = userRepository.findByEmail(id);
        List<NoteDetails> noteList = noteRepository.findByUserDetailsIdAndIsPinedTrue(users.id);
        return noteList;
    }

    @Override
    public String updateArchived(Long noteId, boolean isArchived) {
        NoteDetails details = noteRepository.findByNote_Id(noteId);
        if(details == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
        }
        details.setArchived(isArchived);
        noteRepository.save(details);
        return "Archived Note Updated";
    }

    @Override
    public List archivedNoteList(String token) {
        String id = jwtToken.getDataFromToken(token);
        if(id == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_TOKEN,"Invalid Token");
        }
        UserDetails users = userRepository.findByEmail(id);
        List<NoteDetails> noteList = noteRepository.findByUserDetailsIdAndIsArchivedTrue(users.id);
        return noteList;
    }

    @Override
    public String updateColor(Long noteId, String color){
        NoteDetails details = noteRepository.findByNote_Id(noteId);
        if(details == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
        }
        details.setColor(color);
        noteRepository.save(details);
        return "Note Color Updated";
    }


    @Override
    public String updateTrashNote(Long noteId, boolean isDeleted) {
        NoteDetails details = noteRepository.findByNote_Id(noteId);
        if(details == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
        }
        details.setDeleted(isDeleted);
        noteRepository.save(details);
        return "Deleted Note Updated";
    }

    @Override
    public List trashNoteList(String token) {
        String id = jwtToken.getDataFromToken(token);
        if(id == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_TOKEN,"Invalid Token");
        }
        UserDetails users = userRepository.findByEmail(id);
        List<NoteDetails> noteList = noteRepository.findByUserDetailsIdAndIsDeletedTrue(users.id);
        return noteList;
    }

    @Override
    public String deleteForeverNote(Long noteId) {
        NoteDetails details = noteRepository.findByNote_Id(noteId);
        if(details == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
        }
        System.out.println(details);
        noteRepository.deleteByTitleAndIsDeletedTrue(details.title);
        return "Note Deleted";
    }

}

