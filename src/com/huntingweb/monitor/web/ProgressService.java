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
import com.huntingweb.monitor.domain.Progress;
import com.huntingweb.monitor.repository.ProgressRepository;
import com.huntingweb.monitor.transaction.Transaction;

@RestController
public class ProgressService {
	@Autowired
	private ProgressRepository repository;
	@Autowired
	@Qualifier("ProCraTrans")
	private Transaction progressCreationTransaction;
	@Autowired
	@Qualifier("ProDelTrans")
	private Transaction progressDeletionTransaction;

	@RequestMapping(value = "/progress/{projectId:\\d+}", method = POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Progress> create(@PathVariable("projectId") String projectId,
			@RequestBody Map<String, Object> params) {
		Progress progress = (Progress) progressCreationTransaction.execute(params, projectId);
		return ResponseEntity.ok(progress);
	}

	@RequestMapping(value = "/progress/{id:\\d+}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Progress> get(@PathVariable("id") String id) {
		Progress progress = repository.findOne(id);
		return ResponseEntity.ok(progress);
	}

	@RequestMapping(value = "/progress/{projectId:\\d+}/{page:\\d+}/{size:\\d+}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Progress>> fetch(@PathVariable("projectId") String projectId,
			@PathVariable("page") int page, @PathVariable("size") int size) {
		Pageable pageable = new PageRequest(page, size);
		List<Progress> progresses = Lists.newArrayList(repository.fetchByProjectId(projectId, pageable).iterator());
		progresses = Lists.newArrayList(progresses.stream().sorted(new Comparator<Progress>() {

			@Override
			public int compare(Progress p1, Progress p2) {
				// TODO Auto-generated method stub
				return p1.getDateTime() - p2.getDateTime() > 0 ? -1 : 1;

			}

		}).iterator());
		return ResponseEntity.ok(progresses);
	}

	@RequestMapping(value = "/progress/{id:\\d+}", method = PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Progress> update(@PathVariable("id") String id, @RequestBody Map<String, Object> params) {
		Progress progress = repository.findOne(id);
		progress.update(params);
		repository.save(progress);
		return ResponseEntity.ok(progress);
	}

	@RequestMapping(value = "/progress/{projectId:\\d+}/{progressId:\\d+}", method = DELETE)
	public ResponseEntity<Void> delete(@PathVariable("projectId") String projectId,
			@PathVariable("progressId") String progressId) {
		progressDeletionTransaction.execute(null, projectId, progressId);
		return ResponseEntity.ok().build();
	}
}
