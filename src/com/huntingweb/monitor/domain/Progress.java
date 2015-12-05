package com.huntingweb.monitor.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.MapKeyColumn;

@Entity
@Access(AccessType.FIELD)
public class Progress extends Model {
	private String name;
	@Lob
	private String description;
	private Long dateTime;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<File> mediaFiles;
	@ElementCollection(fetch = FetchType.EAGER)
	@Lob
	@CollectionTable(name = "ProgressAttributes")
	@MapKeyColumn(name = "ProId")
	@Column(name = "ProAttr")
	private Map<String, String> attributes;

	public Progress() {

	}

	public Progress(String id) {
		this.id = id;
		this.dateTime = System.currentTimeMillis();
		this.attributes = new HashMap<>();
		this.mediaFiles = new ArrayList<>();
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDateTime() {
		return dateTime;
	}

	public void setDateTime(Long dateTime) {
		this.dateTime = dateTime;
	}

	public List<File> getMediaFiles() {
		return mediaFiles;
	}

	public void setMediaFiles(List<Map<String, Object>> mediaFiles) {
		this.mediaFiles.clear();
		for (Map<String, Object> mediaFile : mediaFiles) {
			File file = new File((String) mediaFile.get("id"), (String) mediaFile.get("name"),
					(int) mediaFile.get("size"), (String) mediaFile.get("type"), (String) mediaFile.get("url"),
					(String) mediaFile.get("description"));
			this.mediaFiles.add(file);
		}
	}

	public void removeMediaFile(File mediaFile) {
		this.mediaFiles.remove(mediaFile);
	}

	public void removeMediaFile(String fileId) {
		for (File file : this.mediaFiles) {
			if (file.getId().equals(fileId)) {
				this.mediaFiles.remove(file);
				return;
			}
		}
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void addAttributes(Map<String, String> attributes) {
		this.attributes.putAll(attributes);
	}

	public String getAttribute(String name) {
		return this.attributes.get(name);
	}

	@SuppressWarnings("unchecked")
	public void update(Map<String, Object> params) {
		this.setDescription((String) params.get("description"));
		this.setName((String) params.get("name"));
		this.setMediaFiles((List<Map<String, Object>>) params.get("mediaFiles"));
	}

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		if (other == null)
			return false;
		if (!(other instanceof Progress))
			return false;
		if (this.id == null || ((Progress) other).id == null)
			return false;
		if (!((Progress) other).id.equals(this.id))
			return false;
		return true;
	}

}
