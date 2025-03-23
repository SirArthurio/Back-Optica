package com.vaxi.spring_boot_optica.Service;

import com.vaxi.spring_boot_optica.Model.DTO.UserDto;
import com.vaxi.spring_boot_optica.Model.User;
import com.vaxi.spring_boot_optica.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(new UserDto(user));
        }

        return userDtos;
    }

    public User editUser(String _id) {

        return userRepository.findById(_id).orElse(null);
    }

    public UserDto getUser(String cedula) {

        return  new UserDto(userRepository.findPacienteByCedula(cedula));
    }

    public void deleteUser(String _id) {
        userRepository.deleteById(_id);
    }

    public boolean existUserById(String _id) {

        return userRepository.existsById(_id);
    }
    public boolean existUserByUsuario(String usuario) {
        userRepository.findUserName(usuario);
        return true;
    }
}
