package com.jpmc.cca.bean;

/**
 * Group Information, provides the group ID and name.
 * 
 * @author Prashanth N
 *
 */
public class Group {

	private String _name;
	private int _id;
	private boolean _isCancelled=Boolean.FALSE;
	
	public boolean isCancelled() {
		return _isCancelled;
	}

	public void cancel(boolean cancel) {
		this._isCancelled = cancel;
	}

	public Group(String name, int id) {
		_name = name;
		_id = id;
	}

	public String getName() {
		return _name;
	}

	public int getId() {
		return _id;
	}

}
