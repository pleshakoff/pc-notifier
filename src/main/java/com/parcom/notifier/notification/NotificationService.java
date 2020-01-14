package com.parcom.notifier.notification;

import org.springframework.scheduling.annotation.Async;

public interface NotificationService {
    @Async
    void forwardToAgents(NotificationInDto notificationDto);



}
