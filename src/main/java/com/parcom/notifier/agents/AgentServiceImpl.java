package com.parcom.notifier.agents;

import com.parcom.asyncdto.NotificationAgentDto;
import com.parcom.network.Network;
import com.parcom.security_client.UserUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Getter
@Service
@Slf4j
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {


    private final KafkaTemplate<String, NotificationAgentDto> notificationAgentDtoKafkaTemplate;

    @Value("${parcom.kafka.topic.notificationAgent}")
    private String notificationAgentTopic;


    @Override
    public void forwardToAgents(NotificationAgentDto notificationAgentDto){
        sendToKafka(notificationAgentDto);
    }

    
    private void sendToKafka(NotificationAgentDto notificationAgentDto) {

        Message<NotificationAgentDto> message = MessageBuilder
                .withPayload(notificationAgentDto)
                .setHeader(KafkaHeaders.TOPIC, notificationAgentTopic)
                .setHeader(UserUtils.X_AUTH_TOKEN, UserUtils.getToken())
                .build();


        ListenableFuture<SendResult<String, NotificationAgentDto>> future =
                notificationAgentDtoKafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, NotificationAgentDto>>() {

            @Override
            public void onSuccess(SendResult<String, NotificationAgentDto> result) {
                log.info("Send notification for user {}",notificationAgentDto.getIdUserReceiver());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(String.format("Send notification failure. User %s",notificationAgentDto.getIdUserReceiver()),ex);
            }
        });
    }



}


