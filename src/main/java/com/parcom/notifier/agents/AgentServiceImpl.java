package com.parcom.notifier.agents;

import com.parcom.network.Network;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Getter
@Service
@Slf4j
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentProps agentProps;
    private final Network network;

    @Override
    public void forwardToAgents(NotificationAgentDto notificationAgentDto){
        agentProps.getAgents().forEach(agent -> send(agent,notificationAgentDto));
    }

    private void send(Agent agent, NotificationAgentDto notificationAgentDto)
    {
        log.info("Send to agent: \"{}\"",agent.getName());
        try {
            network.callPost(agent.getName(),String.class,notificationAgentDto,"send");
        }
        catch (Exception e)
        {
            log.error("Forward to Agent error",e);
        }
    }
}


