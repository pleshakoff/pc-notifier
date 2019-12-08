package com.parcom.notifier.notification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class NotificationDto
{
     @NotNull
     private  final  NotificationType notificationType;

     @NotNull
     private final String  title;

     private final String  message;

     @NotNull
     private final Long idSourceObject;

}
