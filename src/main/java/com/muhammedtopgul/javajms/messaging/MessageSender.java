package com.muhammedtopgul.javajms.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muhammedtopgul.javajms.configuration.JmsConfig;
import com.muhammedtopgul.javajms.model.MyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

/**
 * @author muhammed-topgul
 * @since 25.02.2022 17:02
 */

@Component
@RequiredArgsConstructor
public class MessageSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

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

    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {
        MyMessage myMessage = MyMessage
                .builder()
                .id(UUID.randomUUID())
                .content("Hello...")
                .build();

        Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RECEIVE_QUEUE, session -> {
            try {
                Message message = session.createTextMessage(objectMapper.writeValueAsString(myMessage));
                message.setStringProperty("_type", "com.muhammedtopgul.javajms.model.MyMessage");
                System.out.println("\nSending Hello...");
                return message;
            } catch (JsonProcessingException e) {
                throw new JMSException("Boom!!!");
            }
        });

        System.out.println(receivedMessage.getBody(String.class));
    }
}
