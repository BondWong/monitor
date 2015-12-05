package com.huntingweb.monitor.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.FIELD)
public class Client extends Model {
	private String name;
	private Long lastVisit;
	private String password;
	private String phone;
	@Embedded
	private Address address;
	@OneToOne
	private Project project;
	@ElementCollection(fetch = FetchType.EAGER)
	@Lob
	@CollectionTable(name = "ClientAttributes")
	@MapKeyColumn(name = "ClientId")
	@Column(name = "ClientAttr")
	private Map<String, String> attributes;

	public Client() {

	}

	public Client(String id) {
		this.id = id;
		this.attributes = new HashMap<>();
	}

	public String getId() {
		return this.id;
	}

	public String getProjectId() {
		return this.project.getId();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name == null ? this.name : name;
	}

	public Long getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Long lastVisit) {
		this.lastVisit = lastVisit == null ? this.lastVisit : lastVisit;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? this.password : password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? this.phone : phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address == null ? this.address : address;
	}

	public void setAddress(Map<String, String> params) {
		this.address = (params == null || params.isEmpty()) ? this.address
				: new Address(params.get("street"), params.get("city"), params.get("state"), params.get("zip"),
						params.get("country"));
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project == null ? this.project : project;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes.putAll(attributes);
	}

	@SuppressWarnings("unchecked")
	public void update(Map<String, Object> params) {
		this.setName((String) params.get("name"));
		this.setLastVisit((Long) params.get("lastVisit"));
		this.setPassword((String) params.get("password"));
		this.setPhone((String) params.get("phone"));
		this.setAddress((Map<String, String>) params.get("address"));
		this.setProject((Project) params.get("project"));
		this.setAttributes((Map<String, String>) params.get("attributes"));
	}

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		if (other == null)
			return false;
		if (!(other instanceof Client))
			return false;
		if (((Client) other).id == null || this.id == null)
			return false;
		if (!((Client) other).id.equals(this.id))
			return false;
		return true;
	}

}
