package com.jpmc.cca.external;

import com.jpmc.cca.bean.Message;

public interface Resource {
	
	/**
	 * 
	 * @param message
	 */
	public abstract void send(Message message);
	
	/**
	 * Returns an integer representing the speed of the resource
	 * @return
	 */
	public abstract int getProcessingSpeed();
	
	/**
	 * @return the availability of the resource
	 */
	public abstract boolean isAvailable();

	/**
	 * @return the information about the resource to register in Gateway
	 */
	public String getInfo();
	
	public boolean isClosed();
}
