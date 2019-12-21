package com.parcom.notifier.user;

import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface UserService {
    @Secured({"ROLE_ADMIN","ROLE_MEMBER"})
    List<User> allInGroup();
}
