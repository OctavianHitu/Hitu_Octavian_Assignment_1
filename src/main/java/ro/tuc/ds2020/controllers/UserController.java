package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.UserDto;
import ro.tuc.ds2020.services.UserService;

import javax.validation.Valid;
import javax.websocket.MessageHandler;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value="/user")
public class UserController {

        private final UserService userService;

        @Autowired
        public  UserController(UserService userService){
            this.userService=userService;
        }

        @GetMapping()
        public ResponseEntity<List<UserDto>> getAllUsers(){
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        }
        @PostMapping()
        public ResponseEntity<UUID> insertUser(@RequestBody @Valid UserDto userDto){
                UUID userID = userService.insert(userDto);
                return new ResponseEntity<>(userID,HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        public  ResponseEntity<?> deleteUser(@PathVariable("id") UUID id){
                userService.delete(id);
                return new ResponseEntity<>("Deleted user with id "+id, HttpStatus.OK);
        }

        @PostMapping("/update")
        public  ResponseEntity<?> update(@RequestBody @Valid UserDto userDto){
                userService.update(userDto);
                return new ResponseEntity<>("Updated user with id "+userDto.getId(), HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> getCarById(@PathVariable("id") UUID id){
                return new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
        }



}
