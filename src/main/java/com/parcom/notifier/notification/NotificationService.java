package com.parcom.notifier.notification;

import com.parcom.asyncdto.NotificationDto;
import org.springframework.scheduling.annotation.Async;

public interface NotificationService {
    @Async
    void forwardToAgents(NotificationDto notificationDto);



}
