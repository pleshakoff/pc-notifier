package com.parcom.notifier.user;

import com.parcom.network.Network;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private  final Network network;


    @Override
    public List<User> allInGroup(){
        User[] body  = network.callGet("classroom",User[].class,"users","all").getBody();
        return Arrays.asList(Objects.requireNonNull(body)) ;
    }









}