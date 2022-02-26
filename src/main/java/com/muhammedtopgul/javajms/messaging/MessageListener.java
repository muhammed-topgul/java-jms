package com.muhammedtopgul.javajms.messaging;

import com.muhammedtopgul.javajms.configuration.JmsConfig;
import com.muhammedtopgul.javajms.model.MyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

/**
 * @author muhammed-topgul
 * @since 25.02.2022 17:16
 */

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload MyMessage myMessage, @Headers MessageHeaders headers, Message message) {
        System.out.println("MessageListener got a message!");

        System.out.println(myMessage);
    }

    @JmsListener(destination = JmsConfig.MY_SEND_RECEIVE_QUEUE)
    public void listenForSendAndReceive(@Payload MyMessage myMessage, @Headers MessageHeaders headers, Message message) throws JMSException {
        MyMessage payloadMessage = MyMessage
                .builder()
                .id(UUID.randomUUID())
                .content("World...")
                .build();
        System.out.println("\nSending World...");
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMessage);
    }
}
