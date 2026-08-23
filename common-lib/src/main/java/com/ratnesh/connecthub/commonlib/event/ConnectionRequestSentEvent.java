package com.ratnesh.connecthub.commonlib.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionRequestSentEvent {
    private Long senderUserId;
    private Long receiverUserId;
}
