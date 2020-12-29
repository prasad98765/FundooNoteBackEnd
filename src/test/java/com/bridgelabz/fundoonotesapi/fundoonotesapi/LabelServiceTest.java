package com.bridgelabz.fundoonotesapi.fundoonotesapi;

import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.LabelDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.LabelService;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.util.JwtToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

import java.util.List;

@SpringBootTest
public class LabelServiceTest {

    @Autowired
    LabelDTO labelDTO;

    @Autowired
    JwtToken jwtToken;

    @Autowired
    LabelService labelService;

    private String token;

    @BeforeEach
    public void token() throws Exception{
        this.token = jwtToken.generateToken("pnchaudhari1996@gmail.com");
        System.out.println(token);
    }

    @Test
    public void givenLabelDetails_whenBabelSave_shouldReturnLabelDetails(){
        labelDTO = new LabelDTO("yyy");
        String message = labelService.saveLabel(token,labelDTO);
        Assert.assertEquals(message,"Label Added Successfully");
    }

    @Test
    public void givenLabelDetails_whenUserNotValid_shouldReturnException(){
        try{
            labelDTO = new LabelDTO("yyy");
            String message = labelService.saveLabel(jwtToken.generateToken("pnchaudhar6@gmail.com"),labelDTO);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_DATA,e.type);
        }
    }

    @Test
    public void givenLabelDetails_whenLabelNotSave_shouldReturnException(){
        try{
            labelDTO = new LabelDTO("yyy");
            String message = labelService.saveLabel(jwtToken.generateToken("pn@gmail.com"),labelDTO);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_DATA,e.type);
        }
    }

    @Test
    public void givenUserToken_whenLabelNoteList_shouldReturnListOfLabel(){
        List details = labelService.noteLabelList(token);
        Assert.assertNotNull(details);
    }

    @Test
    public void givenUserToken_whenTokenInvalid_shouldReturnException(){
        try{
            List details = labelService.noteLabelList(jwtToken.generateToken("pn@gmail.com"));
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_TOKEN,e.type);
        }
    }

    @Test
    public void givenLabelDetails_whenLabelUpdate_shouldReturnSuccessMessage(){
        String message = labelService.updatelabel(3L,"ppp");
        Assert.assertEquals(message,"Label Updated Successfully");
    }

    @Test
    public void givenLabelDetails_whenLabelNotFound_shouldReturnException(){
        try{
            String message = labelService.updatelabel(3L,"ppp");
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_NOTE,e.type);
        }
    }

    @Test
    public void givenLabelDetails_whenAddLabelToNotes_shouldReturnSuccessMessage(){
            String message = labelService.addLabelToNotes(17L,2L);
            Assert.assertEquals(message,"Added Label to Note");
    }

    @Test
    public void givenLabelDetails_whenRemoveLabelToNote_shouldReturnSuccessMessage(){
        String message = labelService.removeLabelToNotes(17L,2L);
        Assert.assertEquals(message,"Deleted Label to Note");
    }

    @Test
    public void givenLabelDetails_whenWrongNoteId_shouldReturnException(){
        try{
        String message = labelService.addLabelToNotes(88L,3L);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_NOTE,e.type);
        }
    }

    @Test
    public void givenLabelDetails_whenWrongLabelId_shouldReturnException(){
        try{
            String message = labelService.addLabelToNotes(17L,37L);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_LABEL,e.type);
        }
    }

    @Test
    public void givenLabelDetails_whenLabelDelete_ShouldReturnSuccessMessage(){
        String message = labelService.deleteNoteLabel(3L);
        Assert.assertEquals(message,"Label Deleted Successfully");
    }

    @Test
    public void givenLabelDetails_whenLabelNotDelete_ShouldReturnSuccessMessage(){
        try{
            String message = labelService.deleteNoteLabel(83L);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_LABEL,e.type);
        }
    }

    @Test
    public void givenLabelDetails_whenGetLabelNote_shouldReturnLabelNoteList(){
        List details = labelService.getLabelNotes(token,2L);
        Assert.assertNotNull(details);
    }


    @Test
    public void givenLabelDetails_whenNotGetLabelNote_shouldReturnException(){
        try{
            List message = labelService.getLabelNotes(token,83L);
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_NOTE,e.type);
        }
    }
}
