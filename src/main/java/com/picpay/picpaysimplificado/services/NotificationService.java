package com.picpay.picpaysimplificado.services;

import com.picpay.picpaysimplificado.domain.user.User;
import com.picpay.picpaysimplificado.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    public RestTemplate restTemplate;

    public void sendNotification(User user, String message)throws Exception{
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email,message);

       ResponseEntity<String> notificationResponse = restTemplate.postForEntity("",notificationRequest,String.class);

       if(!(notificationResponse.getStatusCode()== HttpStatus.OK)) {
           System.out.println("Erro ao enviar");
           throw new Exception("Servico de notificação fora do ar");
       }
    }
}
