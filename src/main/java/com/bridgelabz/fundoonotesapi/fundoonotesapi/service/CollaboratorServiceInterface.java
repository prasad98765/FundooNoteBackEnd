package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.NoteDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;

import java.util.List;

public interface CollaboratorServiceInterface {
    List getAllUsers();

    String addCollaboratorsNotes(int userId, Long noteId);

    String deleteCollaboratorsNotes(int userId, Long noteId);

    List<NoteDetails> getAllCollaboratornotes(String token);

}
