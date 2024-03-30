package com.picpaySimplificado.picpaysimplificado.services;

import com.picpaySimplificado.picpaysimplificado.domain.dtos.TransactionDTO;
import com.picpaySimplificado.picpaysimplificado.domain.transaction.Transaction;
import com.picpaySimplificado.picpaysimplificado.domain.user.User;
import com.picpaySimplificado.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
@Service
public class TransactionService {
    @Autowired
    private UserServices userServices;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userServices.findUserById(transaction.senderId());
        User receiver = this.userServices.findUserById(transaction.recieverId());
        userServices.validateTransaction(sender , transaction.value());
        boolean isAutorized = this.authorizeTransaction(sender , transaction.value());
        if (!isAutorized){
            throw new Exception("TRANSAÇAO NAO AUTORIZADA");
        }
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(transaction.value());
        transaction1.setSender(sender);
        transaction1.setReceiver(receiver);
        transaction1.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));
        this.transactionRepository.save(new Transaction());
        this.userServices.saveUser(sender);
        this.userServices.saveUser(receiver);

        this.notificationService.sendNotification(sender ,"Transaçao realizada com sucesso");
        this.notificationService.sendNotification(receiver , "Recebimento realizado com sucesso");
        return new Transaction();
    }

    public boolean authorizeTransaction(User sender , BigDecimal value){
        ResponseEntity<Map> autorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc" , Map.class);
        if (autorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) autorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }
        else return false;
    }

}
