1)	Since there was no time, unable to add Test cases.
2)	Implemented Producer Consumer concept, to handle messages to be sent to Gateway.
3)	This has a main class ResourceSchedulerTester, which is the starting point of this project.
4)	During the start, we need to define the size of the Queue.
5)	This has a Gateway class, which is Singleton. This class has reference to all External resources, which can,
	be used to configure to the Gateway using the configuration file resources.properties.
6) This Gateway class has Inner Thread class to execute separate, External Resources Task independently,
	at any point of time when resources are in use, the same resources cannot be used conncurently.
7) There is a MessageConsumer class, which defines the stratergy to retrive necessary class from Queue and does following :
		Forwarding
				The class you write must forward Messages via the Gateway interface when resources are available:
				For a single resource, when one message is received, that message is sent to the gateway For two resources, 
				when two messages are received, both messages are sent to the gateway
		Queuing
				When no resources are available, messages should not be sent to the Gateway
				For a single resource, when two messages are received, only the first message is sent to the gateway
		Responding
				As messages are completed, if there are queued messages, they should be processed
				Same as the queuing above, but after the first message is completed, 
				the second message is sent to the gateway.
8) There is a MessageQueue, which keeps track of all the message which needs to be sent to Gateway, and implements :
	Prioritising
			If there are messages belonging to multiple groups in the queue, as resources become available, 
			we want to prioritise messages from groups already started.	