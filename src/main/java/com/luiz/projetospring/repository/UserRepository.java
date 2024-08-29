package com.luiz.projetospring.repository;

import com.luiz.projetospring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
