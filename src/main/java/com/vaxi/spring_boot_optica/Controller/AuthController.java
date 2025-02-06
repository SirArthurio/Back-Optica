package com.vaxi.spring_boot_optica.Controller;

import com.vaxi.spring_boot_optica.Model.DTO.LoginDto;
import com.vaxi.spring_boot_optica.Model.DTO.RegisterDto;
import com.vaxi.spring_boot_optica.Service.UserService;
import com.vaxi.spring_boot_optica.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
private final UserService userService;
private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if(registerDto.getPassword() == null || registerDto.getPassword().length() < 6) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must be at least 6 characters");
        }
            return ResponseEntity.ok(authService.Register(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto login) {
        if(login.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must be at least 6 characters");
        }
       return ResponseEntity.ok(authService.Login(login));
    }

}
