package com.huntingweb.monitor.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.MapKeyColumn;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

@Entity
@Access(AccessType.FIELD)
public class Message extends Model {
	@Lob
	private String content;
	private String publisher;
	private Long dateTime;
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(nullable = true, name = "fileId")),
			@AttributeOverride(name = "name", column = @Column(nullable = true)),
			@AttributeOverride(name = "size", column = @Column(nullable = true)),
			@AttributeOverride(name = "type", column = @Column(nullable = true)),
			@AttributeOverride(name = "url", column = @Column(nullable = true)) })
	private File file;
	@ElementCollection(fetch = FetchType.EAGER)
	@Lob
	@CollectionTable(name = "MessageAttributes")
	@MapKeyColumn(name = "MesId")
	@Column(name = "MesAttr")
	private Map<String, String> attributes;

	public Message() {

	}

	public Message(String id) {
		this.id = id;
		this.attributes = new HashMap<>();
	}

	public String getId() {
		return this.id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? this.content : content;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher == null ? this.publisher : publisher;
	}

	public Long getDateTime() {
		return dateTime;
	}

	public void setDateTime(Long dateTime) {
		this.dateTime = dateTime == null ? this.dateTime : dateTime;
	}

	public void setFile(Map<String, Object> file) {
		this.file = file == null ? this.file
				: new File((String) file.get("id"), (String) file.get("name"), (int) file.get("size"),
						(String) file.get("type"), (String) file.get("url"), (String) file.get("description"));
	}

	public File getFile() {
		return this.file;
	}

	public void addAttributes(Map<String, String> attributes) {
		this.attributes.putAll(attributes);
	}

	public Map<String, String> getAttributes() {
		return this.attributes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Map<String, Object> params) {
		// TODO Auto-generated method stub
		this.setContent((String) params.get("content"));
		this.setPublisher((String) params.get("publisher"));
		this.setDateTime((Long) params.get("dateTime"));
		this.setFile((Map<String, Object>) params.get("file"));
		this.addAttributes((Map<String, String>) params.get("attributes"));
	}

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		if (other == null)
			return false;
		if (!(other instanceof Message))
			return false;
		if (this.id == null || ((Message) other).id == null)
			return false;
		if (!((Message) other).id.equals(this.id))
			return false;
		return true;
	}

}
