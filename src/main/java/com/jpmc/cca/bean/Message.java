package com.jpmc.cca.bean;

/**
 * Complete information about the message, which has reference to {@link Group}.
 * 
 * @author Prashanth N
 *
 */
public class Message implements Comparable<Message> {

	private Group group;
	private String name;
	private int index;
	
	public Message() {
		super();
	}

	public Message(int index, Group group) {
		this.group = group;
		this.name = index +"";
		this.index = index;
	}

	public synchronized void completed() {
		notify();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the name
	 */
	public int getPosition() {
		return index;
	}
	
	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}
	
	@Override
	public String toString() {
		return name + " - " + group.getId() + " Group name " + group.getName();
	}

	public int compareTo(Message message) {
		int groupCompare = message.getGroup().getId() - getGroup().getId();
		if(groupCompare != 0)
		{
			return groupCompare;
		}
		return message.getPosition() - getPosition();
	}
}
