package com.bridgelabz.fundoonotesapi.fundoonotesapi;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.UserDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
public class UserServiceTest {

    private UserDTO userDTO;

    @Autowired
    UserService userService;

    @Test
    public void givenUserDetails_shouldReturnSuccessfullyMessage(){
        userDTO = new UserDTO("Nikhil","Nikhil","nikhil@gmail.com","nikhil","abc");
        String message = userService.addUser(userDTO);
        Assert.assertEquals(message, "Your Account Created Successfully");
    }

    @Test
    public void givenNullUserDetails_shouldReturnUnsuccessfullyMessage(){
        try{
            userDTO = new UserDTO("Nikhil","","","","");
            String message = userService.addUser(userDTO);
            Assert.assertEquals(message, "Your Account Created Successfully");
        } catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_DATA,e.type);
        }
    }
    @Test
    public void givenSameUserDetails_shouldReturnUserAlreadyStoreMessage(){
        try{
            userDTO = new UserDTO("Nikhil","Nikhil","nikhil@gmail.com","nikhil","abc");
            String message = userService.addUser(userDTO);
            Assert.assertEquals(message, "User Already Registered");
        } catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_DATA,e.type);
        }
    }
}
