package com.jpmc.cca.impl;

import java.util.PriorityQueue;

import com.jpmc.cca.bean.Message;
import com.jpmc.cca.util.MessageGenerator;

/**
 * Testing class to produce necessary {@link Message}
 * @author Prashanth N
 *
 */
public class MessageProducer implements Runnable {

	private final PriorityQueue<Message> sharedQueue;

	public MessageProducer(PriorityQueue<Message> msgQueue) {
		this.sharedQueue = msgQueue;
	}

	public synchronized void run() {
		try {
			while (true) {
				// wait if queue is full
				sharedQueue.add(MessageGenerator.getMessage());
				Thread.sleep(100);
			}
		} catch (InterruptedException exception) {
		}
	}
}
