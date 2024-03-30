package com.picpaySimplificado.picpaysimplificado.domain.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value , Long senderId , Long recieverId) {
}
