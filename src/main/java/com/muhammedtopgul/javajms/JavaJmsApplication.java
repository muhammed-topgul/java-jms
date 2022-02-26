package com.muhammedtopgul.javajms;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaJmsApplication {

    public static void main(String[] args) {

//        runLocalActiveMQServer();

        SpringApplication.run(JavaJmsApplication.class, args);
    }

//    private static void runLocalActiveMQServer() {
//        try {
//            ActiveMQServer server = ActiveMQServers.newActiveMQServer(
//                    new ConfigurationImpl()
//                            .setPersistenceEnabled(false)
//                            .setJournalDirectory("target/data/journal")
//                            .setSecurityEnabled(false)
//                            .addAcceptorConfiguration("invm", "vm://0")
//            );
//            server.start();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
}
