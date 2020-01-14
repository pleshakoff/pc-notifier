package com.parcom.notifier.notification;

import com.parcom.asyncdto.NotificationInDto;
import org.springframework.scheduling.annotation.Async;

public interface NotificationService {
    @Async
    void forwardToAgents(NotificationInDto notificationDto);



}
