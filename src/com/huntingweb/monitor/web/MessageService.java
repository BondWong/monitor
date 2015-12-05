package com.huntingweb.monitor.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.huntingweb.monitor.domain.Message;
import com.huntingweb.monitor.repository.MessageRepository;
import com.huntingweb.monitor.transaction.Transaction;

@RestController
public class MessageService {
	@Autowired
	@Qualifier("MesCraTrans")
	private Transaction mesCraTrans;
	@Autowired
	private MessageRepository repository;

	@RequestMapping(value = "/message/{projectId:\\d+}", method = POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> create(@PathVariable("projectId") String projectId,
			@RequestBody Map<String, Object> params) {
		Message message = (Message) mesCraTrans.execute(params, projectId);
		return ResponseEntity.ok(message);
	}

	@RequestMapping(value = "/message/{projectId:\\d+}/{page:\\d+}/{size:\\d+}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Message>> fetch(@PathVariable("projectId") String projectId,
			@PathVariable("page") int page, @PathVariable("size") int size) {
		Pageable pageable = new PageRequest(page, size);
		List<Message> messages = Lists.newArrayList(repository.findAll(pageable).iterator());
		messages = Lists.newArrayList(messages.stream().sorted(new Comparator<Message>() {

			@Override
			public int compare(Message m1, Message m2) {
				// TODO Auto-generated method stub
				return m1.getDateTime() - m2.getDateTime() > 0 ? -1 : 1;

			}

		}).iterator());
		return ResponseEntity.ok(messages);
	}

	@RequestMapping(value = "/message/{publisher:\\w+}/{projectId:\\d+}/{page:\\d+}/{size:\\d+}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Message>> fetchByPublisher(@PathVariable("publisher") String publisher,
			@PathVariable("projectId") String projectId, @PathVariable("page") int page,
			@PathVariable("size") int size) {
		Pageable pageable = new PageRequest(page, size);
		List<Message> messages = Lists
				.newArrayList(repository.fetchByPublisher(projectId, publisher, pageable).iterator());
		return ResponseEntity.ok(messages);
	}

	@RequestMapping(value = "/message/{id:\\d+}", method = PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> update(@PathVariable("id") String id, @RequestBody Map<String, Object> params) {
		Message message = repository.findOne(id);
		message.update(params);
		repository.save(message);
		return ResponseEntity.ok(message);
	}

}
