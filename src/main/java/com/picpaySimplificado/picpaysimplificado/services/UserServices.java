package com.picpaySimplificado.picpaysimplificado.services;

import com.picpaySimplificado.picpaysimplificado.domain.dtos.UserDTO;
import com.picpaySimplificado.picpaysimplificado.domain.user.User;
import com.picpaySimplificado.picpaysimplificado.domain.user.UserType;
import com.picpaySimplificado.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServices {
    public List<User> getAllUsers;
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender , BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("USUARIO DO TIPO LOJISTA NAO AUTORIZADO PARA REALIZAR TRANSAÇOES");
        }
        if (sender.getBalance().compareTo(amount)<0){
            throw new Exception("SALDO INSUFICIENTE");
        }
    }
    public User findUserById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("usuario nao encontrado"));
    }
    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public void saveUser(User user ){
        this.repository.save(user);
    }
    public List<User> getAllUsers(){
        return this.repository.findAll();

    }

}
