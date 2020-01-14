package com.parcom.notifier.agents;

import com.parcom.asyncdto.NotificationAgentDto;

public interface AgentService {
    void forwardToAgents(NotificationAgentDto notificationAgentDto);
}
