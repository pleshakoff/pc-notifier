package com.parcom.notifier.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
//@AllArgsConstructor
@Setter
public class NotificationInDto {

    @NotNull
    private  NotificationType notificationType;

    @NotNull
    private  NotificationReceiverType notificationReceiverType;


    @NotNull
    private String  title;

    private String  message;

    private @NotNull String idObjectSender;

    @NotNull
    private Long idUserSender;

    private Long idUserReceiver;

    private List<Long> customUsers;

}
