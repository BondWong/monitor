package com.huntingweb.monitor.transaction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.huntingweb.monitor.domain.Progress;
import com.huntingweb.monitor.repository.ProgressRepository;

@Component
@Qualifier("ProgFileDelTrans")
public class ProgressFileDeletionTransaction implements Transaction {
	@Autowired
	private ProgressRepository repository;

	@Override
	public Object execute(Map<String, Object> params, Object... metaParams) {
		// TODO Auto-generated method stub
		String progressId = (String) metaParams[0];
		String fileId = (String) metaParams[1];
		Progress progress = repository.findOne(progressId);
		progress.removeMediaFile(fileId);
		repository.save(progress);
		return null;
	}

}
