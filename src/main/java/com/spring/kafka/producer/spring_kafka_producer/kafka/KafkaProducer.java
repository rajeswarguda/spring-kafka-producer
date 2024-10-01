package com.spring.kafka.producer.spring_kafka_producer.kafka;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private KafkaTemplate<String, Greeting> greetingKafkaTemplate;

	@Value(value = "${message.topic.name}")
	private String topicName;

	@Value(value = "${greeting.topic.name}")
	private String greetingTopicName;

	public void sendMessage(String message) {

		CompletableFuture<SendResult<String, String>> send = kafkaTemplate.send(topicName, message);

		send.whenComplete((result, ex) -> {
			if (ex == null) {
				System.out.println(
						"Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			} else {
				System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
			}

		});

	}
	
	public void sendGreetingMessage(Greeting greeting) {
        greetingKafkaTemplate.send(greetingTopicName, greeting);
    }

}
