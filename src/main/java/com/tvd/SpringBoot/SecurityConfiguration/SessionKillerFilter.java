package com.tvd.SpringBoot.SecurityConfiguration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SessionKillerFilter implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		 
			Collection<? extends GrantedAuthority> auth=authentication.getAuthorities();
			for (GrantedAuthority grantedAuthority : auth) {
				System.out.println("---"+grantedAuthority);
			}
		if(auth!=null){
			response.sendRedirect("/home");
		}
		
	}
}
