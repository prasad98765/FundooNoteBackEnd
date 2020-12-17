package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.LabelDTO;

import java.util.List;

public interface LabelServiceInterface {
    String saveLabel(String token, LabelDTO labelDTO);

    List noteLabelList(String token);

    String updatelabel(Long noteId, String labelName);

    String addLabelToNotes(Long noteId, Long labelId);

    String removeLabelToNotes(Long noteId, Long labelId);

    String deleteNoteLabel(Long labelId);
}
