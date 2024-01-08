package com.matheusdelemos.crud.User;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3306")
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    IUserRepository userRepository;


    @GetMapping("/users")
    public List<UserModel> getAllUsers(@RequestParam(required = false) String users) {
        return userRepository.findAll();
    }


    @GetMapping("/users/{id}")
    public UserModel getUsersById(@PathVariable("id") UUID id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));
    }

    @PostMapping
    public ResponseEntity<UserModel> createUsers(@RequestBody UserModel user) {
        try {
            var usuario = new UserModel();
            usuario.setName(user.getName());
            usuario.setEmail(user.getEmail());
            usuario.setPassword(user.getPassword());
            usuario.setBirth_date(user.getBirth_date());
            usuario.setCreate_at(LocalDate.now());
            var _user = userRepository.save(usuario);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //@PathVariable é usado para capturar valores de variáveis diretamente da URL e usá-los como parâmetros em métodos de controlador em um aplicativo Spring.
    @PutMapping("/users/{id}")
    public UserModel updateUsers(@PathVariable("id") UUID id, @RequestBody UserModel users) throws Exception {
        var usersData = userRepository.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));

        usersData.setName(users.getName());
        //utilazar tbm no json idependente da mudança
        usersData.setEmail(users.getEmail());
        usersData.setBirth_date(users.getBirth_date());

        var usuario = userRepository.save(usersData);

        return usuario;


    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUsers(@PathVariable("id") UUID id) {

        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @GetMapping("/users/email")
    public UserModel findByEmail(@RequestBody String email)throws Exception {

        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

    }


}