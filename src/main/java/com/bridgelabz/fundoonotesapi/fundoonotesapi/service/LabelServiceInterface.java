package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.LabelDTO;

public interface LabelServiceInterface {
    String saveLabel(String token, LabelDTO labelDTO);
}
