package com.lhh.demo.util.MQBean;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;


/**
 * Created by lai.huihui on 2020/5/22.
 */
@Component
public class MQProducer {
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(fixedDelay = 2000)
    public void send() {
//        this.jmsMessagingTemplate.convertAndSend(this.queue, "queue消息");
    }

    @Scheduled(fixedDelay = 10000)
    public void topicSend() {
//        this.jmsMessagingTemplate.convertAndSend(this.topic, "topic消息");
    }
}
