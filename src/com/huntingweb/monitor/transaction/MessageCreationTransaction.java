package com.huntingweb.monitor.transaction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.huntingweb.monitor.domain.Message;
import com.huntingweb.monitor.domain.Project;
import com.huntingweb.monitor.domain.factory.Factory;
import com.huntingweb.monitor.repository.ProjectRepository;

@Component
@Qualifier("MesCraTrans")
public class MessageCreationTransaction implements Transaction{
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Object execute(Map<String, Object> params, Object... metaParams) {
		// TODO Auto-generated method stub
		String projectId = (String) metaParams[0];
		Message message = Factory.message().build(params);
		Project project = projectRepository.findOne(projectId);
		project.addMessage(message);
		projectRepository.save(project);
		return message;
	}

}
