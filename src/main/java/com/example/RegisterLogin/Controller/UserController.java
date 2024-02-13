package com.example.RegisterLogin.Controller;

import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Response.LoginResponse;
import com.example.RegisterLogin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.RegisterLogin.Dto.UserDTO;
@CrossOrigin
@RestController
@RequestMapping("/api")

public class UserController {
        @Autowired
        private UserService userService;
        @PostMapping(path = "/save")
        public String saveUser(@RequestBody UserDTO userDTO)
        {
            String id = userService.addUser(userDTO);
            return id;
        }
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
}
