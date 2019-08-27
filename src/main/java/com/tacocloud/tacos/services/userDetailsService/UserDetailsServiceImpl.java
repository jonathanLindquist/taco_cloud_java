package com.tacocloud.tacos.services.userDetailsService;

import com.tacocloud.tacos.data.UserRepository;
import com.tacocloud.tacos.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Primary // use to mark as preferred bean if more than one of this type exists
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (nonNull(user)) {
			return user;
		}
		throw new UsernameNotFoundException("User " + username + " not found");
	}
}
