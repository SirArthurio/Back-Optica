package com.vaxi.spring_boot_optica.Service;

import com.vaxi.spring_boot_optica.Model.AuthResponse;
import com.vaxi.spring_boot_optica.Model.DTO.LoginDto;
import com.vaxi.spring_boot_optica.Model.DTO.RegisterDto;
import com.vaxi.spring_boot_optica.Model.User;
import com.vaxi.spring_boot_optica.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.vaxi.spring_boot_optica.Model.Role.PACIENTE;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse Login(LoginDto login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
        );
        UserDetails userDetails = userRepository.findUserName(login.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String token = jwtService.getToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .build();
    }


    public AuthResponse Register(RegisterDto register) {

        User user = new User();
        user.setNombre(register.getNombre());
        user.setApellido(register.getApellido());
        user.setTelefono(register.getTelefono());
        user.setCedula(register.getCedula());
        user.setRol(PACIENTE);
        user.setDireccion(register.getDireccion());
        user.setFecha_creacion(LocalDateTime.now());
        user.setUsername(register.getUsername());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        userService.addUser(user);
        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }


}
