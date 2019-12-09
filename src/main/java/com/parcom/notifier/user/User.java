package com.parcom.notifier.user;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private final Long id;
    private final String firstName;
    private final String middleName;
    private final String familyName;
    private final String email;
    private final String phone;

    @JsonCreator
    public User(Long id, String firstName, String middleName, String familyName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.familyName = familyName;
        this.email = email;
        this.phone = phone;
    }
}