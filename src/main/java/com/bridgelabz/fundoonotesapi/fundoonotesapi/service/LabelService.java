package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.LabelDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.LabelDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.repository.LabelRepository;
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
}
