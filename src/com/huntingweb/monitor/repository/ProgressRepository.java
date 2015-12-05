package com.huntingweb.monitor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huntingweb.monitor.domain.Progress;

@Repository
@Transactional
public interface ProgressRepository extends JpaRepository<Progress, String> {
	@Query("SELECT progresses FROM Project project INNER JOIN project.progresses progresses WHERE project.id = :projectId")
	public Page<Progress> fetchByProjectId(@Param("projectId") String projectId, Pageable page);
}
