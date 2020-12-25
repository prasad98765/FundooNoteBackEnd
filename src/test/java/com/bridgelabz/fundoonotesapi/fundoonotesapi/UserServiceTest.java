package com.bridgelabz.fundoonotesapi.fundoonotesapi;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.dto.UserDTO;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.exception.FundooException;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.module.UserDetails;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.service.UserService;
import com.bridgelabz.fundoonotesapi.fundoonotesapi.util.JwtToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserDTO userDTO;

    @Autowired
    UserService userService;

    @Autowired
    JwtToken jwtToken;

    private String token;

    @BeforeEach
    public void token() throws Exception{
       this.token = jwtToken.generateToken("pnchaudhari1996@gmail.com");
        System.out.println(token);
    }

    @Test
    public void givenUserDetails_shouldReturnSuccessfullyMessage(){
        userDTO = new UserDTO("Nikhil","Nikhil","nikhil@gmail.com","nikhil","abc");
        String message = userService.addUser(userDTO);
        Assert.assertEquals(message, "Your Account Created Successfully");
    }

    @Test
    public void givenNullUserDetails_shouldReturnUnsuccessfullyMessage(){
        try{
            userDTO = new UserDTO("Nikhil","","nikhil@gmail.com","","");
            String message = userService.addUser(userDTO);
        } catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_DATA,e.type);
        }
    }
    @Test
    public void givenSameUserDetails_shouldReturnUserAlreadyStoreMessage(){
        try{
            userDTO = new UserDTO("Nikhil","Nikhil","nikhil@gmail.com","nikhil","abc");
            String message = userService.addUser(userDTO);
        } catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.USER_ALREADY_REGISTERED,e.type);
        }
    }

    @Test
    public void givenConfirmEmailAccount_shouldReturnSentLink(){
        String message = userService.confirmEmailAccount(token);
        Assert.assertEquals(message, "User Email Account is Verified");
    }

    @Test
    public void givenConfirmInvalidEmailAccount_shouldReturnException(){
        try{
            String message = userService.confirmEmailAccount("token");
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_LINK,e.type);
        }
    }

    @Test
    public void givenValidEmailAndPassword_shouldReturnSuccessfullyMessage(){
        UserDetails details = userService.signIn("pnchaudhari1996@gmail.com","prasad");
        Assert.assertNotNull(details);
    }

    @Test
    public void givenInvalidEmailAndPassword_shouldReturnException(){
        try{
            UserDetails details = userService.signIn("pnchaud@gmail.com","prasad");
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_EMAIL,e.type);
        }
    }

    @Test
    public void givenEmailAndInvalidPassword_shouldReturnException(){
        try{
            UserDetails details = userService.signIn("pnchaudhari1996@gmail.com","pra");
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_PASSWORD,e.type);
        }
    }

    @Test
    public void givenValidEmail_shouldReturnSentForgetPasswordLink(){
        String message = userService.forgotPassword("pnchaudhari1996@gmail.com");
        Assert.assertEquals(message,"Reset Password Link Sent to your Email Id");
    }

    @Test
    public void givenInvalidEmail_shouldReturnException(){
        try{
            String message = userService.forgotPassword("pnchaudhari1996@gmail.com");
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_EMAIL,e.type);
        }
    }

    @Test
    public void givenValidValid_shouldReturnValidToken(){
        String message = userService.resetPassword(token);
        Assert.assertEquals(message,"Valid Token");
    }

    @Test
    public void givenInvalidValid_shouldReturnInvalidToken(){
        try{
            String message = userService.resetPassword("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwbmNoYXVkaGFyaTE5OTZAZ21haWwuY29tIiwiZXhwIjoxNjA4NjU2MTUwLCJpYXQiOjE2MDg2MzgxNTB9.Mn4U1GYGL6qw1OlXG1Nh_fi5Nc4Awp7P8grIPyOV2yVNwrSxCrGl7_KSCmL1WSy5BTL5E5X6LhG7rlNNEB1tJg");
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_TOKEN,e.type);
        }
    }

    @Test
    public void givenValidTokenAndValidUserDetails_shouldReturnPasswordChange(){
        userDTO = new UserDTO("Prasad","Chaudhari","pnchaudhari1996@gmail.com","prasad","abc");
        String message = userService.changePassword(userDTO,token);
        System.out.println("message"+message);
        Assert.assertEquals(message, "Password Change Successfully");
    }

    @Test
    public void givenInValidTokenAndValidUserDetails_shouldReturnPasswordChange(){
        try{
            userDTO = new UserDTO("Prasad","Chaudhari","pnchaudhari1996@gmail.com","prasad","abc");
            String message = userService.changePassword(userDTO,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwbmNoYXVkaGFyaTE5OTZAZ21haWwuY29tIiwiZXhwIjoxNjA4NjU2MTUwLCJpYXQiOjE2MDg2MzgxNTB9.Mn4U1GYGL6qw1OlXG1Nh_fi5Nc4Awp7P8grIPyOV2yVNwrSxCrGl7_KSCmL1WSy5BTL5E5X6LhG7rlNNEB1tJg");
        }catch (FundooException e){
            Assert.assertEquals(FundooException.ExceptionType.INVALID_DATA,e.type);
        }

    }
}
