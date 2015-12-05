package com.huntingweb.monitor.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Access(AccessType.FIELD)
public class Project extends Model {
	private String name;
	private Long deadline;
	private Integer progress;
	private Integer value;
	private String type;
	private Integer submitNum;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<File> files;
	@ElementCollection(fetch = FetchType.EAGER)
	@Lob
	@CollectionTable(name = "ProjectAttributes")
	@MapKeyColumn(name = "ProId")
	@Column(name = "ProAttr")
	private Map<String, String> attributes;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.MERGE }, orphanRemoval = true)
	private List<Progress> progresses;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.MERGE }, orphanRemoval = true)
	private List<Message> messages;

	public Project() {

	}

	public Project(String id) {
		this.id = id;
		attributes = new HashMap<>();
		progress = 0;
		this.files = new ArrayList<>();
		this.progresses = new ArrayList<>();
		this.messages = new ArrayList<>();
		this.submitNum = 0;
	}

	public String getId() {
		return this.id;
	}

	public int getSubmitNum() {
		return this.submitNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? this.name : name;
	}

	public Long getDeadline() {
		return deadline;
	}

	public void setDeadline(Long deadline) {
		this.deadline = deadline == null ? this.deadline : deadline;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress == null ? this.progress : progress;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value == null ? this.value : value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? this.type : type;
	}

	public List<File> getFiles() {
		return files;
	}

	public void addFile(File file) {
		this.files.add(file);
	}

	public void removeFile(File file) {
		this.files.remove(file);
	}

	public void removeFile(String id) {
		int size = this.files.size();
		int index = 0;
		for (int i = 0; i < size; i++) {
			if (this.files.get(i).getId().equals(id)) {
				index = i;
				break;
			}
		}
		this.files.remove(index);
	}

	public void setFiles(List<File> files) {
		this.files.addAll(files);
	}

	public String getAttribute(String name) {
		return this.attributes.get(name);
	}

	public void addAttributes(Map<String, String> attributes) {
		this.attributes.putAll(attributes);
	}

	public Map<String, String> getAttributes() {
		return this.attributes;
	}

	public List<Progress> getProgresses() {
		return progresses;
	}

	public void addProgress(Progress progress) {
		this.progresses.add(progress);
		this.submitNum++;
	}

	public void removeProgress(Progress progress) {
		Hibernate.initialize(this.progresses);
		this.progresses.remove(progress);
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void addMessage(Message message) {
		this.messages.add(message);
	}

	@SuppressWarnings("unchecked")
	public void update(Map<String, Object> params) {
		this.addAttributes((Map<String, String>) params.get("attributes"));
		this.setName((String) params.get("name"));
		this.setValue((Integer) params.get("value"));
		this.setProgress((Integer) params.get("progress"));
		this.setDeadline((Long) params.get("deadline"));
		this.setType((String) params.get("type"));
		List<Map<String, Object>> files = (List<Map<String, Object>>) params.get("files");
		for (Map<String, Object> file : files)
			this.addFile(new File((String) file.get("id"), (String) file.get("name"), (int) file.get("size"),
					(String) file.get("type"), (String) file.get("url"), (String) file.get("description")));
	}

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		if (other == null)
			return false;
		if (!(other instanceof Project))
			return false;
		if (this.id == null || ((Project) other).id == null)
			return false;
		if (!(((Project) other).id.equals(this.id)))
			return false;
		return true;
	}
}
