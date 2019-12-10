package com.parcom.notifier.notification;


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

    private  final  NotificationService notificationService;

    @PostMapping("/group")
    @ApiOperation(value = "Send notification to all users in the group")
    public String  sendToGroup(@RequestBody NotificationInDto notificationDto,
                            BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        notificationService.forwardForGroup(notificationDto);
        return "{\"result\": \"done\"}";
    }


    @PostMapping("/user")
    @ApiOperation(value = "Send notification to user")
    public String sendToUser(@RequestBody NotificationInDto notificationDto,
                       BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        throw  new UnsupportedOperationException();
    }

    @PostMapping("/custom")
    @ApiOperation(value = "Send notification to custom list of users")
    public String sendToCustom(@RequestBody NotificationInDto notificationDto,
                           BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        throw  new UnsupportedOperationException();
    }




}
