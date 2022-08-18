package home.springboot.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import home.springboot.kafka.model.User;

/**
 * with this EnableKafka spring boot will kepe on eye on the configuration
 * @author shjena
 *
 */
@EnableKafka
@Configuration
public class KafkaConfiguration {

	/**
	 * Below is the consumer Factory 
	 * this is where we are providing all details where kafka is running and 
	 * providing the StringDeserializer which it can use while converting the message from kafka Topic.
	 * @return  it will  handle String type data as input from producer and will consume it.
	 */
	@Bean
	public ConsumerFactory<String, String> consumerFactory(){
		
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<String,String>(config);
	}
	
	/*
	 * Here we are setting ConsumerFactory to KafkaListner and "ConcurrentKafkaListenerContainerFactory" listener 
	 * is required for spring boot so that Spring boot can inject that to required class. 
	 */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> KafkaListnerContainerFactory(){
		
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
/**
 * Lets create 	userConsumerFactory and userKafkaListnerFactory respectively to handle JSON data from topic "jsonTopic"
 */
	/*
	 * 
	 */
	@Bean
	public ConsumerFactory<String, User> userConsumerFactory(){
		
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<String,User>(config,new StringDeserializer(), new JsonDeserializer<>(User.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, User> userKafkaListnerContainerFactory(){
		
		ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userConsumerFactory());
		return factory;
	}
}
