package com.parcom.notifier.notification;


import com.parcom.asyncdto.NotificationInDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/send",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@Api(tags="Notifier")
@RequiredArgsConstructor
public class NotificationController {

    private  final NotificationService notificationService;

    @PostMapping("")
    @ApiOperation(value = "Send notification")
    public String  sendToGroup(@RequestBody NotificationInDto notificationDto,
                            BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        notificationService.forwardToAgents(notificationDto);
        return "{\"result\": \"done\"}";
    }

}
