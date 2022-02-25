package com.muhammedtopgul.javajms.messaging;

import com.muhammedtopgul.javajms.configuration.JmsConfig;
import com.muhammedtopgul.javajms.model.MyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author muhammed-topgul
 * @since 25.02.2022 17:02
 */

@Component
@RequiredArgsConstructor
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("\nMessageSender sending a message!");

        MyMessage myMessage = MyMessage
                .builder()
                .id(UUID.randomUUID())
                .content("Hello...")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, myMessage);

        System.out.println("MessageSender sent a message!\n");
    }
}
