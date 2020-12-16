package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.NoteDTO;

import java.util.List;

public interface NoteServiceInterface {
    void saveNote(NoteDTO noteDTO, String token);
    List getNoteList(String token);
    String updateNote(NoteDTO noteDTO);
    String updatePin(Long noteId, boolean isPined);
    List pinNoteList(String token);
    String updateArchived(Long noteId, boolean isArchived);
    List archivedNoteList(String token);
    String updateColor(Long noteId, String color);
    String updateTrashNote(Long noteId, boolean isDeleted);
    List trashNoteList(String token);
    String deleteForeverNote(Long noteId);

}
