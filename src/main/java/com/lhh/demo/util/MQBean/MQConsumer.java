package com.lhh.demo.util.MQBean;

import com.lhh.demo.service.UserInfoService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import java.time.LocalDateTime;

/**
 * Created by lai.huihui on 2020/5/22.
 */
@Component
public class MQConsumer {

    @Autowired
    private UserInfoService userInfoService;

    @JmsListener(destination = "test",containerFactory = "queueListenerContainerFactory")
    public void receiveQueue(String consumer) {
        System.out.println(consumer + "消息已消费" + LocalDateTime.now());
    }

    @JmsListener(destination = "test",containerFactory = "queueListenerContainerFactory")
    public void receiveQueue2(String consumer) {
        System.out.println(consumer + "receiveQueue2消息已消费" + LocalDateTime.now());
    }

    @JmsListener(destination = "testTopic", containerFactory = "topicListenerContainerFactory")
    public void receiveTopic(String consumer) {
        System.out.println(consumer + "消息已消费" + LocalDateTime.now());
    }

    @JmsListener(destination = "testTopic", containerFactory = "topicListenerContainerFactory")
    public void receiveTopic2(String consumer) {
        System.out.println(consumer + "receiveTopic2消息已消费" + LocalDateTime.now());
    }
}
