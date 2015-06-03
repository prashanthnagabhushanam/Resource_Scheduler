package com.jpmc.cca.external;

import com.jpmc.cca.bean.Message;

/**
 * Database Resource just used as one of the External resource.
 * 
 * @author Prashanth N
 *
 */
public class DatabaseResource implements Resource{

	public void send(Message message) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getProcessingSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getInfo() {
		return "DATABASE";
	}

	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

}
