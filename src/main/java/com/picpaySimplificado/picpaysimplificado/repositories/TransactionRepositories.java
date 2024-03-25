package com.picpaySimplificado.picpaysimplificado.repositories;

import com.picpaySimplificado.picpaysimplificado.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepositories extends JpaRepository<Transaction , Long> {
    // reponsavel por fazer a manipulaçao das transaçoes
}
