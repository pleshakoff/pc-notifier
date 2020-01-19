package com.parcom.notifier.notification;

import com.parcom.asyncdto.NotificationDto;
import com.parcom.security_client.AsyncUserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
class NotificationListener {

    private final NotificationService notificationService;


    @KafkaListener(topics = "${parcom.kafka.topic.notification}", groupId = "${parcom.kafka.group.notifier}")
    public void listen(@Payload NotificationDto notificationDto, @Header("X-Auth-Token") String token) {
        log.info("Get message from broker");
         AsyncUserUtils.authByToken(token);
        notificationService.forwardToAgents(notificationDto);
    }


}
