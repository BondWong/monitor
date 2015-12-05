package com.huntingweb.monitor.transaction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.huntingweb.monitor.domain.Project;
import com.huntingweb.monitor.repository.ProjectRepository;

@Component
@Qualifier("ProjFileDelTrans")
public class ProjectFileDeletionTransaction implements Transaction {
	@Autowired
	private ProjectRepository repository;

	@Override
	public Object execute(Map<String, Object> params, Object... metaParams) {
		// TODO Auto-generated method stub
		String projectId = (String) metaParams[0];
		String fileId = (String) metaParams[1];
		Project project = repository.findOne(projectId);
		project.removeFile(fileId);
		repository.save(project);
		return null;
	}

}
