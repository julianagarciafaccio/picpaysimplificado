package com.picpaySimplificado.picpaysimplificado.domain.dtos;

import com.picpaySimplificado.picpaysimplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String emmail ,String passWord, UserType userType) {
}
