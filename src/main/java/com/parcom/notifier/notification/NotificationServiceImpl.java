package com.parcom.notifier.notification;

import com.parcom.notifier.agents.AgentService;
import com.parcom.notifier.agents.NotificationAgentDto;
import com.parcom.notifier.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class NotificationServiceImpl implements NotificationService {

    private final UserService userService;
    private final AgentService agentService;

    @Override
    public void forwardForGroup(NotificationInDto notificationDto) {

        log.info("Forward notification. Type \"{}\", id {} ",notificationDto.getNotificationType(),notificationDto.getIdObjectSender() );
        userService.allInGroup().forEach(user -> {
                    log.info("For user: \"{} {} {}\" ", user.getId(), user.getFirstName(),user.getFamilyName());

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



}
