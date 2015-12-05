package com.huntingweb.monitor.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginNavigationHandler implements AuthenticationSuccessHandler {
	private static final SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
	// private static final SimpleGrantedAuthority roleClient = new
	// SimpleGrantedAuthority("ROLE_CLIENT");

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (authentication.getAuthorities().contains(roleAdmin))
			response.sendRedirect("/management");
		else
			response.sendRedirect("/");
	}

}
