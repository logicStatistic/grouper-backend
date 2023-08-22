package com.grouper.grouper_controller;

import com.grouper.grouper_exception_control.EmailAlreadyTakenException;
import com.grouper.grouper_exception_control.EmailFailedToSendException;
import com.grouper.grouper_exception_control.IncorrectVerificationCodeException;
import com.grouper.grouper_exception_control.UserDoesNotExistException;
import com.grouper.grouper_model.GrouperRegistrationObject;
import com.grouper.grouper_model.GrouperUser;
import com.grouper.grouper_service_layer.GrouperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/controller")
public class AuthenticationController {

    @Autowired
    private final GrouperUserService userService;

    @Autowired
    public AuthenticationController(GrouperUserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> handlerMessage(){
        return new ResponseEntity<String>("Email taken", HttpStatus.CONFLICT );
    }

    @PostMapping("/register")
    public GrouperUser registerNewUser(@RequestBody GrouperRegistrationObject regObject){
        return userService.registerNewUser(regObject);
    }

    @ExceptionHandler ({UserDoesNotExistException.class})
    public ResponseEntity<String> handlerMSG(){
        return new ResponseEntity<String>("This user does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update_phone")
    public GrouperUser updatePhoneNumber(@RequestBody LinkedHashMap<String, String> body){

        String username = body.get("username");
        String phone = body.get("phone");

        GrouperUser users = userService.findByUsername(username);
        users.setPhone(phone);

        return userService.updateUser(users);
    }
   @ExceptionHandler({EmailFailedToSendException.class})
    public ResponseEntity<String> HandlerMSSG(){
        return new ResponseEntity<String>("Email failed to send",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/verify_code")
    public ResponseEntity<String> createEmailVerification(@RequestBody LinkedHashMap<String, String> body) throws Exception {
        userService.generateVerificationCode(body.get("username"));

       return new ResponseEntity<String>("Verification code sent to your email successfully", HttpStatus.OK);
    }

    @ExceptionHandler({IncorrectVerificationCodeException.class})
    public ResponseEntity<String> IncorrectCodeMsg(){
        return new ResponseEntity<String>("Incorrect verification code ", HttpStatus.CONFLICT);
    }

    @PostMapping("/verify_email")
    public GrouperUser verifyEmail(@RequestBody LinkedHashMap<String, String> body){

        Long code = Long.parseLong(body.get("code"));
        String username = body.get("username");

        return userService.verifyEmail(username, code);
    }

    @PutMapping("/update_password")
    public GrouperUser updatePassword(@RequestBody LinkedHashMap<String, String> body){
           String username = body.get("username");
           String password = body.get("password");

           return userService.updatePassword(username, password);
    }
}
