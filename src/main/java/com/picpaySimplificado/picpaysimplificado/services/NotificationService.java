package com.picpaySimplificado.picpaysimplificado.services;

import com.picpaySimplificado.picpaysimplificado.domain.dtos.NotificationDTO;
import com.picpaySimplificado.picpaysimplificado.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;
    public void sendNotification(User user , String message) throws Exception {
        String email = user.getEmmail();
        NotificationDTO notificationRequest = new NotificationDTO(email , message);
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest , String.class);

        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            System.out.println("Erro ao enviar notificaçao");
            throw new Exception("Serviço de notificaçao fora do ar");
        }
        System.out.println("Notificaçao enviada para o usuario");

    }

}
