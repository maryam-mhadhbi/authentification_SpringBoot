package com.example.RegisterLogin.Service.Implementation;

import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Entity.AppUser;
import com.example.RegisterLogin.Repository.UserRepository;
import com.example.RegisterLogin.Response.LoginResponse;
import com.example.RegisterLogin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addUser(UserDTO userDTO) {
        AppUser user1 = new AppUser (
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getAgency(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        userRepository.save(user1);
        return user1.getFirstName() +" "+user1.getLastName();

    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {

    String msg = "";
    AppUser user2 = userRepository.findByEmail(loginDTO.getEmail());
        if (user2 != null) {
        String password = loginDTO.getPassword();
        String encodedPassword = user2.getPassword();
        boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
        if (isPwdRight) {
            Optional<AppUser> user3 = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
            if (user3.isPresent()) {
                return new LoginResponse("Login Success", true);
            } else {
                return new LoginResponse("Login Failed", false);
            }
        } else {
            return new LoginResponse("password Not Match", false);
        }
    }else {
        return new LoginResponse("Email not exits", false);
    }
}
}
