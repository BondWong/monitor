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
import com.huntingweb.monitor.domain.Project;
import com.huntingweb.monitor.domain.factory.Factory;
import com.huntingweb.monitor.repository.ProjectRepository;

@RestController
public class ProjectService {
	@Autowired
	private ProjectRepository repository;

	@RequestMapping(value = "/project", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> create(@RequestBody Map<String, Object> params) {
		Project project = Factory.project().build(params);
		project = repository.save(project);
		return ResponseEntity.ok(project);
	}

	@RequestMapping(value = "/project/{id:\\d+}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> get(@PathVariable("id") String id) {
		Project project = repository.findOne(id);
		return ResponseEntity.ok(project);
	}

	@RequestMapping(value = "/project/{page:\\d{1,}}/{size:\\d{1,}}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Project>> fetch(@PathVariable("page") int page, @PathVariable("size") int size) {
		List<Project> projects = Lists.newArrayList(repository.findAll(new PageRequest(page, size)).iterator());
		return ResponseEntity.ok(projects);
	}

	@RequestMapping(value = "/project/{id:\\d+}", method = PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> update(@PathVariable("id") String id, @RequestBody Map<String, Object> params) {
		Project project = repository.findOne(id);
		project.update(params);
		repository.save(project);
		return ResponseEntity.ok(project);
	}

	@RequestMapping(value = "/project/{id:\\d+}", method = DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		repository.delete(id);
		return ResponseEntity.ok().build();
	}

}
