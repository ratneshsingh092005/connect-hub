package com.ratnesh.connecthub.commonlib.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConnectionRequestAcceptedEvent {
    private Long senderUserId;
    private Long receiverUserId;
}
