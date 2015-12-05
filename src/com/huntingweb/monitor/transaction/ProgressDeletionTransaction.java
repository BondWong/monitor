package com.huntingweb.monitor.transaction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.huntingweb.monitor.domain.Progress;
import com.huntingweb.monitor.domain.Project;
import com.huntingweb.monitor.repository.ProgressRepository;
import com.huntingweb.monitor.repository.ProjectRepository;

@Component
@Qualifier("ProDelTrans")
public class ProgressDeletionTransaction implements Transaction {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ProgressRepository progressRepository;

	@Override
	public Object execute(Map<String, Object> params, Object... metaParams) {
		// TODO Auto-generated method stub
		String projectId = (String) metaParams[0];
		String progressId = (String) metaParams[1];
		Project project = projectRepository.findOne(projectId);
		Progress progress = progressRepository.findOne(progressId);
		project.removeProgress(progress);
		projectRepository.save(project);
		return null;
	}

}
