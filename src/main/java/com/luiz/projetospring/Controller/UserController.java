package com.luiz.projetospring.Controller;

import com.luiz.projetospring.model.User;
import com.luiz.projetospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping //Lista todos os usuários
    public List<User> listUsers(){
        return service.listUser();
    }

    @GetMapping("/{id}") //Procura o usuário por ID
    public ResponseEntity<User> findUserByID(@PathVariable Integer id){
        Optional<User> user = service.findUserByID(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        if (service.usernameExists(user.getUsername())){
            return ResponseEntity.badRequest().body(null);
        }
        User createdUser = service.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}") //Atualiza um usuário
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user){
        if (service.findUserByID(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        User updatedUser = service.saveUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        if (service.findUserByID(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
