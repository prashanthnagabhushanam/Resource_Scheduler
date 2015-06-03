package com.jpmc.cca.external;

import com.jpmc.cca.bean.Message;

/**
 * File System Resource, used just as a Temp File Resource.
 * 
 * @author Prashanth N
 *
 */
public class FileSystemResource implements Resource {

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
		return "FILE";
	}

	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

}
