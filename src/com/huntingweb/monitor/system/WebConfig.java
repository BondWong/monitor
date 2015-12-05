package com.huntingweb.monitor.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.huntingweb.monitor.web")
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResovler = new InternalResourceViewResolver();
		viewResovler.setPrefix("/");
		viewResovler.setSuffix(".jsp");
		viewResovler.setExposeContextBeansAsAttributes(true);
		return viewResovler;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/*
		 * registry.addResourceHandler("app.js", "admin/app.js",
		 * "admin/controllers.js", "admin/project-management/controllers.js")
		 * .addResourceLocations("Angular-App/", "Angular-App/admin/",
		 * "Angular-App/admin/project-management/",
		 * "Angular-App/admin/client-management/");
		 */
	}

}
