package com.muhammedtopgul.javajms.messaging;

import com.muhammedtopgul.javajms.configuration.JmsConfig;
import com.muhammedtopgul.javajms.model.MyMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

/**
 * @author muhammed-topgul
 * @since 25.02.2022 17:16
 */

@Component
public class MessageListener {

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload MyMessage myMessage, @Headers MessageHeaders headers, Message message) {
        System.out.println("MessageListener got a message!");

        System.out.println(myMessage);
    }
}
