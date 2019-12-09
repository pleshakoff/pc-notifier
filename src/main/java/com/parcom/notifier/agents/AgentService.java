package com.parcom.notifier.agents;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
@Slf4j
@RequiredArgsConstructor
public class AgentService {

    private final AgentProps agentProps;

    public void forwardToAgents(NotificationAgentDto notificationAgentDto){
        agentProps.getAgents().forEach(agent -> send(agent,notificationAgentDto));
    }

    private void send(Agent agent, NotificationAgentDto notificationAgentDto)
    {
        log.info("Send to agent: \"'{}' {}:{}\"",agent.getName(),agent.getHost(),agent.getPort());
    }
}


