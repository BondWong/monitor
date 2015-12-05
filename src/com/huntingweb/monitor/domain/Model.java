package com.huntingweb.monitor.domain;

import java.util.Map;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Model {
	@Id
	protected String id;
	
	public abstract void update(Map<String, Object> params);

	public abstract boolean equals(Object other);

	public int hashCode() {
		return this.id.hashCode();
	}
}
