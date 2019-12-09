package com.parcom.notifier.agents;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
class Agent {

    private final String name;
    private final String host;
    private final String port;

}
