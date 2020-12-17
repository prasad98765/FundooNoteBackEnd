package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.LabelDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.LabelDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.LabelRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.NoteRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.UserRepository;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService implements LabelServiceInterface {

    @Autowired
    LabelRepository labelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    private JwtToken jwtToken;

    @Override
    public String saveLabel(String token,LabelDTO labelDTO) {
        try{
            String id = jwtToken.getDataFromToken(token);
            UserDetails users = userRepository.findByEmail(id);
            LabelDetails labelDetails = new LabelDetails(labelDTO,users);
            labelRepository.save(labelDetails);
            return "Label Added Successfully";
        }catch (Exception e){
            throw new FundooException(FundooException.ExceptionType.INVALID_DATA,"INVALID DATA");
        }
    }

    @Override
    public List noteLabelList(String token) {
        String id = jwtToken.getDataFromToken(token);
        if(id == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_TOKEN,"Invalid Token");
        }
        UserDetails users = userRepository.findByEmail(id);
        List<LabelDetails> noteList = labelRepository.findByUserDetailsId(users.id);
        return noteList;
    }

    @Override
    public String updatelabel(Long noteId, String labelName) {
        try{
            LabelDetails details = labelRepository.findById(noteId);
            if(details == null){
                throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
            }
            details.setLabelName(labelName);
            labelRepository.save(details);
            return "Label Updated Successfully";
        }catch (Exception e){
            throw new FundooException(FundooException.ExceptionType.INVALID_DATA,"INVALID DATA");
        }
    }

    @Override
    public String addLabelToNotes(Long noteId, Long labelId) {
        try{
            NoteDetails details = noteRepository.findByNote_Id(noteId);
            if(details == null){
                throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
            }
            LabelDetails labeldetails = labelRepository.findById(labelId);
            if(labeldetails == null){
                throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Label");
            }
            details.LabelDetails(labeldetails);
            noteRepository.save(details);
            return "Added Label to Note";
        }catch (Exception e){
            throw new FundooException(FundooException.ExceptionType.INVALID_DATA,"INVALID DATA");
        }
    }

    @Override
    public String removeLabelToNotes(Long noteId, Long labelId) {
            NoteDetails details = noteRepository.findByNote_Id(noteId);
            if(details == null){
                throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Note");
            }
            LabelDetails labeldetails = labelRepository.findById(labelId);
            if(labeldetails == null){
                throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Label");
            }
            details.removeLabelDetails(labeldetails);
            noteRepository.save(details);
            return "Deleted Label to Note";
    }

    @Override
    public String deleteNoteLabel(Long labelId) {
        if(labelId == null){
            throw new FundooException(FundooException.ExceptionType.INVALID_NOTE,"Invalid Label ID");
        }
        labelRepository.deleteById(labelId);
        return "Label Deleted Successfully";
    }
}
