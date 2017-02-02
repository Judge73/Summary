package ua.nure.kotkov.SummaryTask4.db.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable{
	
	private static final long serialVersionUID = -3865702457872119060L;
	
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
