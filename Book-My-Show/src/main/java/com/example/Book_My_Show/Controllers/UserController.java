package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.EntryDTOs.UserDTO;
import com.example.Book_My_Show.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService=userService;
    }
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO)
    {
        try {
            String response = userService.addUser(userDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("User not added" , HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestParam ("id") int userId)
    {
        String response = userService.deleteUser(userId);
        return new ResponseEntity<>(response , HttpStatus.ACCEPTED);
    }
}
