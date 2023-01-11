# spring-boot-kafka-consumer-application

##Project Overview::
	steps to follow are do data cleaning ,pre processing, apply some business logic and store in simple format.
	- initially we are professing (String or Json) as input from kafka producer and will consume using Spring-boot-Kafka
	- this Product will process input events which could be ex: StockData or click events for a company etc and will process it as per business need.
	- once we are doing all the pre processing of data using spark, then will save data into different tables for further usages.
	- Mostly then cleaned data can be used by Data Analyst and they can query to the cassandra( new tables) for there use case.
	- we will have one -to-one mapping for the queue (producer and consumer) for each different input types.

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

# _Next development Plan_
================================
- Add Spark and cassandra Dependencies.
- Read events into the spark.
- Do required calculation with Spark Streaming.
- Store Data into cassandra table.
