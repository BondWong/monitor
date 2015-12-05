package com.huntingweb.monitor.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.huntingweb.monitor.domain.Client;
import com.huntingweb.monitor.domain.Project;
import com.huntingweb.monitor.domain.factory.Factory;
import com.huntingweb.monitor.repository.ClientRepository;
import com.huntingweb.monitor.repository.ProjectRepository;

@RestController
public class ClientService {
	@Autowired
	private ClientRepository repository;
	@Autowired
	private ProjectRepository projectRespository;

	@RequestMapping(value = "/client", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> create(@RequestBody Map<String, Object> params) {
		Project project = projectRespository.findOne((String) params.get("project"));
		params.put("project", project);
		Client client = Factory.client().build(params);
		client = repository.save(client);
		return ResponseEntity.ok(client);
	}

	@RequestMapping(value = "/client/{page:\\d+}/{size:\\d+}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Client>> fetch(@PathVariable("page") int page, @PathVariable("size") int size) {
		List<Client> clients = Lists.newArrayList(repository.findAll(new PageRequest(page, size)).iterator());
		return ResponseEntity.ok(clients);
	}

	@RequestMapping(value = "/client/{id:\\d+}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> get(@PathVariable String id) {
		Client client = repository.findOne(id);
		return ResponseEntity.ok(client);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/client/{id:\\d+}", method = PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> update(@PathVariable("id") String id, @RequestBody Map<String, Object> params) {
		Project project = projectRespository.findOne((String) ((Map<String, Object>) params.get("project")).get("id"));
		params.put("project", project);
		Client client = repository.findOne(id);
		client.update(params);
		client = repository.save(client);
		return ResponseEntity.ok(client);
	}

	@RequestMapping(value = "/client/{id:\\d+}", method = DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		repository.delete(id);
		return ResponseEntity.ok().build();
	}

}
