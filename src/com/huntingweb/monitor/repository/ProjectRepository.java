package com.huntingweb.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huntingweb.monitor.domain.Project;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<Project, String> {

}
