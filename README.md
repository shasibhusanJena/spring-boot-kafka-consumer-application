# spring-boot-kafka-consumer-application

upcoming Dev plan

- Here we are going to process Historical trade data as (String or Json) as input.
- and will calculate avg Price of the stock.
- store AVG of each stock in the DB.

## Commands to start Zookeeper and Kafka

## _Start the ZooKeeper application using below command in ..bin\windows folder_
	- .\zookeeper-server-start.bat C:\kafka\kafka_2.13-3.2.1\config\zookeeper.properties
	
## _Here we are processing the input data from the kafka Producer  ..bin\windows folder_
	- and we read all events from command "kafka-server-start.bat C:\kafka\kafka_2.13-3.2.1\config\server.properties"
	- ..bin\windows>kafka-topics.bat --create --topic jsonTopic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 4
	- ..\bin\windows>kafka-topics.bat --create --topic stringTopic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 4

	- kafka-console-producer.bat --broker-list localhost:9092 --topic stringTopic
	- kafka-console-producer.bat --broker-list localhost:9092 --topic jsonTopic

## List all topic Names
	===================
	kafka-topics.bat --list --bootstrap-server localhost:9092

## create a producer and consumer
	==========================
	C:\kafka\kafka_2.13-3.2.1\bin\windows>kafka-console-producer.bat --broker-list localhost:9092 --topic topicOne
	here for us spark is the consumer.
	C:\kafka\kafka_2.13-3.2.1\bin\windows>kafka-console-producer.bat --broker-list localhost:9092 --topic topicOne
	if at any point of time spark is not consuming any messages, to troubleshoot we can run using Kafka-consumer and test if msges are getting read which are send from Spark-producer.
