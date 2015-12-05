package com.huntingweb.monitor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huntingweb.monitor.domain.Message;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, String> {
	@Query("SELECT m FROM Project p, Message m INNER JOIN p.messages messages WHERE p.id = :projectId AND m IN messages AND m.publisher = :publisher ORDER BY m.dateTime DESC")
	public Page<Message> fetchByPublisher(@Param("projectId") String projectId, @Param("publisher") String publisher,
			Pageable pageable);
}
