package com.parcom.notifier.notification;

import com.parcom.notifier.agents.AgentService;
import com.parcom.notifier.agents.NotificationAgentDto;
import com.parcom.notifier.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.parcom.notifier.notification.NotificationReceiverType.GROUP;

@Service
@RequiredArgsConstructor
@Slf4j
class NotificationServiceImpl implements NotificationService {

    private final UserService userService;
    private final AgentService agentService;



    @Override
    public void forwardToAgents(NotificationInDto notificationDto) {

        log.info("Forward notification. Type \"{}\", id {} ", notificationDto.getNotificationType(), notificationDto.getIdObjectSender());
        switch (notificationDto.getNotificationReceiverType()) {
            case GROUP:
                toGroup(notificationDto);
                break;
            case USER:
                toUser(notificationDto);
                break;
            case CUSTOM:
                toCustom(notificationDto);
                break;
        }


        toGroup(notificationDto);
    }

    private void toGroup(NotificationInDto notificationDto) {
        userService.allInGroup().forEach(user -> {
                    log.info("For user: \"{} {} {}\" ", user.getId(), user.getFirstName(), user.getFamilyName());

                    NotificationAgentDto notificationAgentDto = new NotificationAgentDto(notificationDto.getNotificationType(),
                            notificationDto.getTitle(),
                            notificationDto.getMessage(),
                            notificationDto.getIdObjectSender(),
                            notificationDto.getIdUserSender(),
                            user.getId());

                    agentService.forwardToAgents(notificationAgentDto);

                }

        );
    }


    private void toUser(NotificationInDto notificationDto) {
        throw  new UnsupportedOperationException();
    }


    private void toCustom(NotificationInDto notificationDto) {
        throw  new UnsupportedOperationException();
    }

}
