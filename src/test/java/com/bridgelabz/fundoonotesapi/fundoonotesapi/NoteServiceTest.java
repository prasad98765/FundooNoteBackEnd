package com.bridgelabz.fundoonotesapi.fundoonotesapi;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.NoteService;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.util.JwtToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

import java.util.List;

@SpringBootTest
public class NoteServiceTest {

    @Autowired
    JwtToken jwtToken;

    @Autowired
    NoteService noteService;

    private String token;

    @BeforeEach
    public void token() throws Exception{
        this.token = jwtToken.generateToken("pnchaudhari1996@gmail.com");
    }

    @Test
    public void givenNoteDetails_whenUserTokenValid_ShouldReturnNoteList(){
            List details =  noteService.getNoteList(token);
            Assert.assertNotNull(details);
    }

    @Test
    public void givenNoteDetails_whenUserTokenNotValid_ShouldReturnException(){
        try {
            List details =  noteService.getNoteList(jwtToken.generateToken("pnchaudh@gmail.com"));
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_TOKEN,e.type);
        }
    }

    /////////////////////////////////// Pin Note //////////////////////////////////////////////////////

    @Test
    public void givenPinNoteDetails_whenPinNoteUpdate_shouldReturnSuccessMessage(){
        String message = noteService.updatePin(13L,true);
        Assert.assertEquals(message,"PinNote Updated");
    }

    @Test
    public void givenPinNoteDetails_whenPinNoteNotFound_shouldReturnException(){
        try {
            String message = noteService.updatePin(43L,true);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_NOTE,e.type);
        }
    }

    @Test
    public void givenUserToken_whenPinNoteList_shouldReturnPinNoteList(){
        List details = noteService.pinNoteList(token);
        Assert.assertNotNull(details);
    }

    @Test
    public void givenUserToken_whenPinNoteListNotFound_shouldException(){
        try{
            List details = noteService.pinNoteList(token);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_TOKEN,e.type);
        }
    }

    ///////////////////////////////// Archived Note /////////////////////////////////////////////

    @Test
    public void givenArchivedNoteDetails_whenArchiveNoteUpdate_shouldReturnSuccessMessage(){
        String message = noteService.updateArchived(13L,true);
        Assert.assertEquals(message,"Archived Note Updated");
    }

    @Test
    public void givenArchivedNoteDetails_whenArchiveNoteNotUpdate_shouldReturnException(){
        try {
            String message = noteService.updateArchived(43L,true);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_NOTE,e.type);
        }
    }

    @Test
    public void givenUserToken_whenArchiveNoteList_shouldReturnPinNoteList(){
        List details = noteService.archivedNoteList(token);
        Assert.assertNotNull(details);
    }

    @Test
    public void givenUserToken_whenArchiveNoteNotList_shouldException(){
        try{
            List details = noteService.archivedNoteList(token);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_TOKEN,e.type);
        }
    }

    //////////////////////////// Trash Note //////////////////////////////////////////////

    @Test
    public void givenArchivedNoteDetails_whenTrashNoteUpdate_shouldReturnSuccessMessage(){
        String message = noteService.updateTrashNote(13L,true);
        Assert.assertEquals(message,"Deleted Note Updated");
    }

    @Test
    public void givenArchivedNoteDetails_whenTrashNoteNotUpdate_shouldReturnException(){
        try {
            String message = noteService.updateTrashNote(43L,true);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_NOTE,e.type);
        }
    }

    @Test
    public void givenUserToken_whenTrashNoteList_shouldReturnPinNoteList(){
        List details = noteService.trashNoteList(token);
        Assert.assertNotNull(details);
    }

    @Test
    public void givenUserToken_whenTrashNoteNotList_shouldException(){
        try{
            List details = noteService.trashNoteList(token);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_TOKEN,e.type);
        }
    }

    ////////////////////////////// Update Color //////////////////////////////////

    @Test
    public void givenUserDetails_whenColorNoteUpdate_shouldReturnSuccessMessage(){
        String message = noteService.updateColor(13L,"black");
        Assert.assertEquals(message,"Note Color Updated");
    }

    @Test
    public void givenNoteDetails_whenColorNoteNotUpdate_shouldReturnException(){
        try {
            String message = noteService.updateColor(43L,"true");
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_NOTE,e.type);
        }
    }

    //////////////////////////// Delete Forever ////////////////////////////////

    @Test
    public void givenUserDetails_whenDeleteForeverNote_shouldReturnSuccessMessage(){
        String message = noteService.deleteForeverNote(17L);
        Assert.assertEquals(message,"Note Deleted");
    }

    @Test
    public void givenNoteDetails_whenNoteNotDeleted_shouldReturnException(){
        try {
            String message = noteService.deleteForeverNote(43L);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_NOTE,e.type);
        }
    }
}
