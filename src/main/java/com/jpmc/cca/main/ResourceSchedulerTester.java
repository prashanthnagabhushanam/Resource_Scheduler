package com.jpmc.cca.main;

import com.jpmc.cca.impl.MessageConsumer;
import com.jpmc.cca.impl.MessageProducer;
import com.jpmc.cca.util.MessageComparator;
import com.jpmc.cca.util.MessageQueue;

/**
 * This is a main Class to verify, the Complete Flow
 * 
 * @author Prashanth N
 *
 */
public class ResourceSchedulerTester {
	public static void main(String[] args) {
		MessageQueue msgQueue = new MessageQueue(5, new MessageComparator());
		final int MESSAGE_COUNT = 8;
		Runnable run1 = new MessageProducer(msgQueue);
		//Runnable run2 = new MessageProducer(msgQueue, MESSAGE_COUNT);
		Runnable run3 = new MessageConsumer(msgQueue);

		Thread thread1 = new Thread(run1);
		//Thread thread2 = new Thread(run2);
		Thread thread3 = new Thread(run3);

		thread1.start();
		//thread2.start();
		thread3.start();
	}
}
