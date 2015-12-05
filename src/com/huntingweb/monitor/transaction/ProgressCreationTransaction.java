package com.huntingweb.monitor.transaction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.huntingweb.monitor.domain.Progress;
import com.huntingweb.monitor.domain.Project;
import com.huntingweb.monitor.domain.factory.Factory;
import com.huntingweb.monitor.repository.ProjectRepository;

@Component
@Qualifier("ProCraTrans")
public class ProgressCreationTransaction implements Transaction {
	@Autowired
	private ProjectRepository repository;

	@Override
	public Object execute(Map<String, Object> params, Object... metaParams) {
		// TODO Auto-generated method stub
		String projectId = (String) metaParams[0];
		Progress progress = Factory.progress().build(params);
		Project project = repository.findOne(projectId);
		project.addProgress(progress);
		repository.save(project);
		return progress;
	}

}
