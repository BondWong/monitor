package com.huntingweb.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huntingweb.monitor.domain.Client;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, String> {

}
