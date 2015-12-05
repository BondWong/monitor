package com.huntingweb.monitor.transaction;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface Transaction {
	public Object execute(Map<String, Object> params, Object... metaParams);
}
