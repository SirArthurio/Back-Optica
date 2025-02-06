package com.vaxi.spring_boot_optica.Controller;

import com.vaxi.spring_boot_optica.Model.DTO.UserDto;
import com.vaxi.spring_boot_optica.Model.User;
import com.vaxi.spring_boot_optica.Repository.UserRepository;
import com.vaxi.spring_boot_optica.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {

                bindingResult
                        .getAllErrors()
                        .forEach(error -> System.out.println(error.getDefaultMessage()));
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            }

            User savedUser = userService.addUser(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedUser);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser() {
        try {

            if (!userService.getAllUser().isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro los usuarios");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUser/{_id}")
    public ResponseEntity<?> getUser(@PathVariable("_id") String _id) {
        try {
            UserDto user = userService.getUser(_id);
            if (user != null) {
                return ResponseEntity
                        .status(HttpStatus.OK).body(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario " + HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteUser/{_id}")
    public ResponseEntity<?> deleteUser(@PathVariable("_id") String _id) {
        try {
            if (userService.existUserById(_id)) {
                userService.deleteUser(_id);
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .build();
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("no existe el usuario");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/edit/{_id}")
    public ResponseEntity<?> editUser(@PathVariable("_id") String _id,@RequestBody User user) {
        try {
            User data = userService.editUser(_id);
            if (data != null) {
                data.setNombre(user.getNombre());
                data.setApellido(user.getApellido());
                data.setDireccion(user.getDireccion());
                data.setTelefono(user.getTelefono());
                userService.addUser(data);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(data);
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario " + HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
