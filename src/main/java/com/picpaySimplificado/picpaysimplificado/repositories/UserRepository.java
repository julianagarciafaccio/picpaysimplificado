package com.picpaySimplificado.picpaysimplificado.repositories;

import com.picpaySimplificado.picpaysimplificado.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByDocument(String document); // metodo q faz busca pelo documento
    Optional<User> findUserByDocument(Long id);

}
