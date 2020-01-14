package com.parcom.notifier.notification;

import com.parcom.asyncdto.NotificationDto;
import com.parcom.notifier.agents.AgentService;
import com.parcom.asyncdto.NotificationAgentDto;
import com.parcom.notifier.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class NotificationServiceImpl implements NotificationService {

    private final UserService userService;
    private final AgentService agentService;



    @Override
    public void forwardToAgents(NotificationDto notificationDto) {

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
   }

    private void toGroup(NotificationDto notificationDto) {
        userService.allInGroup().forEach(user -> {
                    log.info("Get user: \"{} {} {}\" ", user.getId(), user.getFirstName(), user.getFamilyName());

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


    private void toUser(NotificationDto notificationDto) {
        throw  new UnsupportedOperationException();
    }


    private void toCustom(NotificationDto notificationDto) {
        throw  new UnsupportedOperationException();
    }

}
