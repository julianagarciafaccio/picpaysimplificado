package com.picpaySimplificado.picpaysimplificado.domain.user;

import com.picpaySimplificado.picpaysimplificado.domain.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of  = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String emmail;

    private String passWord;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User (UserDTO data){
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.balance = data.balance();
        this.userType = data.userType();
        this.passWord = data.passWord();
        this.emmail = data.emmail();
        this.document = data.document();
    }


}
