package home.springboot.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import home.springboot.kafka.model.User;
import kafka.utils.Json;

@Service
public class KafkaConsumer {

	@KafkaListener(topics="stringTopic",groupId="group_id")
	public void consumeStringMessage(String message) {
		System.out.println("Consumer --String-- Message:"+message);
	}
	
	@KafkaListener(topics="jsonTopic",groupId="group_id_JSON", containerFactory="userKafkaListnerContainerFactory")
	public void consumeJsonMessage(User user) {
		System.out.println("Consumer --Json-- Message:"+user);
	}
}
