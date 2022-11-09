package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.LoginDto;
import ro.tuc.ds2020.entities.UserOfApp;
import ro.tuc.ds2020.services.UserService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("login")
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<?> getLogin(@RequestBody @Valid LoginDto logind){

        UserOfApp user= userService.loginFunction(logind);
        String con= user.getEmail()+","+user.getRole()+","+user.getId();
        return new ResponseEntity<>(con,HttpStatus.OK);

    }

}
