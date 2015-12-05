package com.huntingweb.monitor.domain.factory;

import java.util.Map;

import com.huntingweb.monitor.domain.Client;
import com.huntingweb.monitor.domain.Message;
import com.huntingweb.monitor.domain.Project;
import com.huntingweb.monitor.domain.Progress;

public class Factory {
	public static ProjectBuilder project() {
		return new ProjectBuilder();
	}

	public static ClientBuilder client() {
		return new ClientBuilder();
	}
	
	public static ProgressBuilder progress() {
		return new ProgressBuilder();
	}
	
	public static MessageBuilder message() {
		return new MessageBuilder();
	}

	public static class ProjectBuilder {
		public Project build(Map<String, Object> params) {
			Project project = new Project((String) params.get("id"));
			project.update(params);
			return project;
		}
	}

	public static class ClientBuilder {
		public Client build(Map<String, Object> params) {
			Client client = new Client((String) params.get("id"));
			client.update(params);
			return client;
		}
	}
	
	public static class ProgressBuilder {
		public Progress build(Map<String, Object> params) {
			Progress progress = new Progress((String) params.get("id"));
			progress.update(params);
			return progress;
		}
	}
	
	public static class MessageBuilder {
		public Message build(Map<String, Object> params) {
			Message message = new Message((String) params.get("id"));
			message.update(params);
			return message;
		}
	}

}
