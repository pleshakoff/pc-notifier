package com.parcom.notifier.agents;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@ConfigurationProperties(prefix = "parcom.notifier.agents")
public class AgentProps {
    private List<Agent> agents =  new ArrayList<>();


}


