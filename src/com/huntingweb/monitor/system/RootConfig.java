package com.huntingweb.monitor.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableJpaRepositories(basePackages = "com.huntingweb.monitor.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.huntingweb.monitor" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class RootConfig {
	// persistence
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean lemf = new LocalEntityManagerFactoryBean();
		lemf.setPersistenceUnitName("Monitor");
		return lemf;
	}

	// transactionManager for transaction
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactory().getObject());
		return manager;
	}

	// in order to make EntityManager in every repository, which is a singleton,
	// thread-safe (a entityManager per thread)
	@Bean
	public PersistenceAnnotationBeanPostProcessor postProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}
	
}
