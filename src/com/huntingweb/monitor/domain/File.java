package com.huntingweb.monitor.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class File {
	private String id;
	private String name;
	private Integer size;
	private String type;
	private String url;
	private String description;

	public File() {

	}

	public File(String id, String name, int size, String type, String url, String description) {
		this.id = id;
		this.name = name;
		this.size = size;
		this.type = type;
		this.url = url;
		this.setDescription(description);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getSize() {
		return size;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (!(object instanceof File))
			return false;
		if (this.url == null || ((File) object).getUrl() == null)
			return false;
		return this.url.equals(((File) object).getUrl());
	}

	public int hashCode() {
		return this.url.hashCode();
	}

}
