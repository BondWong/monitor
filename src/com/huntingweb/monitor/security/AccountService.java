package com.huntingweb.monitor.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.huntingweb.monitor.domain.Client;
import com.huntingweb.monitor.repository.ClientRepository;

@Component
public class AccountService implements UserDetailsService {
	private static final SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
	private static final SimpleGrantedAuthority roleClient = new SimpleGrantedAuthority("ROLE_CLIENT");

	@Autowired
	private ClientRepository repository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (id.equals("admin")) {
			authorities.add(roleAdmin);
			return new User("admin", "123456", authorities);
		} else if (id.equals("test")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_TEST"));
			return new User("test", "123456", authorities);
		}
		Client client = repository.findOne(id);
		authorities.add(roleClient);
		return new User(client.getId(), client.getPassword(), authorities);
	}

}