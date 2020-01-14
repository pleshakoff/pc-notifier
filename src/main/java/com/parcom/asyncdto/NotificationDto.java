package com.parcom.asyncdto;

import com.parcom.notifier.notification.NotificationReceiverType;
import com.parcom.notifier.notification.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class NotificationDto {

    @NotNull
    private NotificationType notificationType;

    @NotNull
    private NotificationReceiverType notificationReceiverType;


    @NotNull
    private String  title;

    private String  message;

    private @NotNull String idObjectSender;

    @NotNull
    private Long idUserSender;

    private Long idUserReceiver;

    private List<Long> customUsers;

}
