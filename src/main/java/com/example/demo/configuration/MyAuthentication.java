package com.example.demo.configuration;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.beans.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyAuthentication implements AuthenticationProvider {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
       
        return grantedAuthorities;
    }

	@Override
	public boolean supports(Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		List<User> users = userService.getMailId(username);
        System.out.println("Yes");
		if (users.size() > 0) {
			if (passwordEncoder.matches(pwd, users.get(0).getPassword())) {
				return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(users.get(0).getRole()));
			} else {
				throw new BadCredentialsException("Invalid password!");
			}
		}else {
			throw new BadCredentialsException("No user registered with this details!");
		}
    }
}