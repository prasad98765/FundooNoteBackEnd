package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.LabelDTO;

import java.util.List;

public interface LabelServiceInterface {
    String saveLabel(String token, LabelDTO labelDTO);

    List noteLabelList(String token);
}
