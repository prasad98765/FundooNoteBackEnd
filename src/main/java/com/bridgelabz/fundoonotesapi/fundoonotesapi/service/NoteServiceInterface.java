package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.NoteDTO;

import java.util.List;

public interface NoteServiceInterface {
    void saveNote(NoteDTO noteDTO, String token);
    List getNoteList(String token);
    String updateNote(NoteDTO noteDTO);
    String updatePin(NoteDTO noteDTO);
    List pinNoteList(String token);
    String updateArchived(NoteDTO noteDTO);
    List archivedNoteList(String token);
    String updateColor(NoteDTO noteDTO);
    String updateTrashNote(NoteDTO noteDTO);
    List trashNoteList(String token);
    String deleteForeverNote(NoteDTO noteDTO);
}
