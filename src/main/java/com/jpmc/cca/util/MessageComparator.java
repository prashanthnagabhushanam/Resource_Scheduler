package com.jpmc.cca.util;

import java.util.Comparator;

import com.jpmc.cca.bean.Message;

/**
 * Customized comparator to prioritize the queue based on Group ID.
 * 
 * @author Prashanth N
 *
 */
public class MessageComparator implements Comparator<Message> {

	/**
	 *Alternative Message Prioritisation
	 *		It should be possible to use different Message prioritisation algorithms to select the next Message from the queue. 
	 *		Invent a new strategy and allow the resource scheduler to be run with this or the original algorithm easily.
	 */
	public int compare(Message o1, Message o2) {
		return o1.compareTo(o2);
	}
}
