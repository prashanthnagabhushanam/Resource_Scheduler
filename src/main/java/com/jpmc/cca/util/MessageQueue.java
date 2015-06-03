package com.jpmc.cca.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import com.jpmc.cca.bean.Group;
import com.jpmc.cca.bean.Message;

/**
 * Queue class which maintains information about all the messages to be
 * processed in Queue, implements {@link PriorityQueue}.
 * 
 * During Retrival of next {@link Message}, compares the Group ID from
 * {@link Group} in the Queue, if any Message is available for the same Group,
 * then that Message is Retrived and sent.
 * 
 * @author prashanthn
 *
 */
public class MessageQueue extends PriorityQueue<com.jpmc.cca.bean.Message> {

	private int _size;

	private int _previousGroupId = -1;

	public MessageQueue(int size, Comparator comparator) {
		super(size, null);
		_size = size;
	}

	@Override
	public synchronized boolean add(Message message) {
		while (size() == _size) {
			try {
				System.out.println("Queue is full ");
				wait();
			} catch (InterruptedException e) {
			}
		}
		notifyAll();
		// Cancellation
		// 	It should be possible to tell the scheduler that a group of messages
		// 	has now been cancelled.
		// 	Once cancelled, no further messages from that group should sent to
		// 	the Gateway.
		//Termination Messages
		// 	When a Termination Message is received, 
		//	that means that it is the last Message in that group (not all groups have the same number of messages).
		// 	If further Messages belonging to that group are received, an error should be raised.
		if (!message.getGroup().isCancelled()) {
			return super.add(message);
		}
		return Boolean.TRUE;
	}

	/**
	 * 
	 * Prioritising If there are messages belonging to multiple groups in the
	 * queue, as resources become available, we want to prioritise messages from
	 * groups already started.
	 * 
	 * For a single resource, messages received: message1 (group2) message2
	 * (group1) message3 (group2) message4 (group3) message1 (group2) was
	 * received first so will be processed first as messages complete, the order
	 * they are sent to the gateway should be: message1 message3 (it's part of
	 * group2, which is already "in-progress") message2 message4
	 */
	@Override
	public synchronized Message poll() {
		while (isEmpty()) {
			try {
				System.out.println("Queue is empty waiting for message");
				wait();
			} catch (InterruptedException e) {
			}
		}
		notifyAll();
		System.out.println("Queue Structure " + toString());
		Message message = null;
		// create iterator from the queue
		Iterator<Message> it = iterator();
		while (it.hasNext()) {
			message = it.next();
			if (message.getGroup().getId() == _previousGroupId) {
				remove(message);
				_previousGroupId = message.getGroup().getId();
				return message;
			}
		}
		message = super.poll();
		_previousGroupId = message.getGroup().getId();
		return message;
	}
}
