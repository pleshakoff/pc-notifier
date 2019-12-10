package com.parcom.notifier.agents;

import com.parcom.notifier.RestTemplateUtils;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Service
@Slf4j
@RequiredArgsConstructor
public class AgentService {

    private final AgentProps agentProps;
    private final RestTemplate  restTemplate;

    public void forwardToAgents(NotificationAgentDto notificationAgentDto){
        agentProps.getAgents().forEach(agent -> send(agent,notificationAgentDto));
    }

    private void send(Agent agent, NotificationAgentDto notificationAgentDto)
    {
        log.info("Send to agent: \"'{}' {}:{}\"",agent.getName(),agent.getHost(),agent.getPort());

        URI uri = UriComponentsBuilder.newInstance().scheme("http").host(agent.getHost()).port(agent.getPort()).path("/send/").build().toUri();

        HttpEntity<NotificationAgentDto> requestBody = new HttpEntity<>(notificationAgentDto, RestTemplateUtils.getHttpHeaders());
        try {
            restTemplate.postForEntity(uri, requestBody,String.class);
        }
        catch (Exception e)
        {
            log.error("Sending error",e);
        }
    }
}


