package com.luiz.projetospring.service;
import com.luiz.projetospring.model.User;
import com.luiz.projetospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;


    public List<User> listUser(){
        return repository.findAll();
    }

    public Optional<User> findUserByID(Integer id){
        return repository.findById(id);
    }

    public User saveUser(User user){
        return repository.save(user);
    }

    public void deleteUser(Integer id){
        repository.deleteById(id);
    }

    public boolean usernameExists(String username){
        return repository.findByUsername(username).isPresent();
    }

    public User createUser(String username, String password, String name){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        return saveUser(user);
    }
}
