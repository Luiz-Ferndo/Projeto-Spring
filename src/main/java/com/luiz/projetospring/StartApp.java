package com.luiz.projetospring;

import com.luiz.projetospring.model.User;
import com.luiz.projetospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class StartApp implements CommandLineRunner {
    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("luiz");
        user.setPassword("luiz123");

        repository.save();
    }
}
