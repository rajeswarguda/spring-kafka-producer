package com.spring.kafka.producer.spring_kafka_producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.kafka.producer.spring_kafka_producer.kafka.Greeting;
import com.spring.kafka.producer.spring_kafka_producer.kafka.KafkaProducer;

@RestController
@RequestMapping("kafka-producer")
public class KafkaController {
	
	@Autowired
	KafkaProducer kafkaProducer;
	
	@GetMapping(path = "/message")
	public void sendMessage(@RequestParam("msg") String message) {
		
		kafkaProducer.sendMessage(message);
	
	}
	
	@PostMapping(path = "/greeting")
	public void sendGreeting(@RequestBody Greeting greeting) {
		
		kafkaProducer.sendGreetingMessage(greeting);
	
	}

}
