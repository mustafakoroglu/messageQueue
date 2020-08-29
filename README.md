# messageQueue

Developer Guide For MessageQueue

1.	Common
1.1.	Enums
Exception codes and actionTypes are implemented as enum. Moving these objects to DB will be considered
1.2.	Exception
Business and runtime exceptions implemented. For now all exceptions are logged when created. It will be parametrized
1.3.	Serializer
Dto serializer implemented for Kafka.
1.4.	Util
Static util methods implemented in this package
1.5.	Validator
Message and property validators.

2.	Dto
2.1.	MessageDTO
MessageDTO implemented as described in sds.
3.	Manager
1.1.	Consumer
ConsumerManager implemented with init and helper methods.
3configurable threadpool works for sms, email and push
1.2.	Producer
Two producer manager implemented for inMemoryQueue and Kafka. Managing from properties
1.3.	messageSender
Managers implemented for sms, email and push. Managing by a factory with respect to actionType of event.
4.	Service
1.1.	Consumer
Service for start consumers.
1.2.	Producer
Service for send event to producer.

5.	Properties
Kafka consumer producer conf, logging conf, threadpools conf are handled by property files. They can moved to DB.

6.	Tests
Unit tests are implemented in Test packages. Integration and automation test will be implemented.
7.	Dependencies
Project is started as maven. Jdk 1.8 is needed for kafka client 2.6
Apache.common, kafka, slf4j, jackson are needed.
For working with kafka on local machines, zookeeper and kafka servers are needed.
8.	Repository
9.	Open issues
9.1.	DB
Properties and enums can be moved to db.
Audit data needed for sent/not sent messages
9.2.	Logging
Logging will be refactored.
9.3.	Versioning
Versioning is not considered for now.



