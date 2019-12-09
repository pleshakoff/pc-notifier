package com.parcom.notifier.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
class NotificationInDto {

    @NotNull
    private  final NotificationType notificationType;

    @NotNull
    private final String  title;

    private final String  message;

    @NotNull
    private final Long idObjectSender;

    @NotNull
    private final Long idUserSender;

    private Long idUserReceiver;

    private List<Long> customUsers;

}
