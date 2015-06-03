package com.jpmc.cca.impl;

import java.util.PriorityQueue;

import com.jpmc.cca.bean.Message;
import com.jpmc.cca.util.Gateway;

/**
 * 
 * This is a Message Consumer which will be used to maintian all the necessary information
 * about the message which needs to be passed to the {@link Gateway}.
 * 
 * This also takes care of
 * 
 * Forwarding
 * 			The class you write must forward Messages via the Gateway interface when resources are available:
 * 			For a single resource, when one message is received, that message is sent to the gateway 
 * 			For two resources, when two messages are received, both messages are sent to the gateway
 * 
 * When no resources are available, messages should not be sent to the Gateway
 * 		For a single resource, when two messages are received, only the first message is sent to the gateway
 * 
 * As messages are completed, if there are queued messages, they should be processed
 * 		Same as the queuing above, but after the first message is completed, the second message is sent to the gateway
 * 
 * @author Prashanth N
 *
 */
public class MessageConsumer implements Runnable {

	private final PriorityQueue<Message> sharedQueue;

	public MessageConsumer(PriorityQueue<Message> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	public synchronized void run() {
		while (true) {
			try {
				Gateway gateway = Gateway.getGateway();
				Message message = sharedQueue.poll();
				synchronized (gateway) {
					while (!gateway.isResourceAvailable()) {
						try {
							System.out.println("Resource is not available so wait");
							gateway.wait();
						} catch (InterruptedException e) {
						}
					}
				}
				gateway.push(message);
				Thread.sleep(100);
			} catch (InterruptedException exception) {
			}
		}
	}

}