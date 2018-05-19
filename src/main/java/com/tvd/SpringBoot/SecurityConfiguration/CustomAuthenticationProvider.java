package com.tvd.SpringBoot.SecurityConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.tvd.SpringBoot.UserRepository;
import com.tvd.SpringBoot.DTO.PermissionDTO;
import com.tvd.SpringBoot.DTO.Role;
import com.tvd.SpringBoot.DTO.User;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserRepository userRepo;
	

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		
		String username=auth.getName();
		String password=auth.getCredentials().toString();
		
		
		List<GrantedAuthority> grant=new ArrayList<GrantedAuthority>();
		
		try {
			int userId=userRepo.findUser(username, password);
		
		
		
		
		if(userId>0){
			
			User user=userRepo.findOne(userId);
			for (Role role : user.getRoles()) {
				
				for (PermissionDTO perm : role.getPermission()) {
					
					String url = perm.getUrl();
					if(perm.getView() == true){
						String authority = "ROLE_"+url+"_view_Y";
						 grant.add(new SimpleGrantedAuthority(authority));
						//System.out.println(authority);
						if(perm.getCreate() == true){
							authority = "ROLE_"+url+"_create_Y";
							grant.add(new SimpleGrantedAuthority(authority));
							//System.out.println(authority);
						}
						if(perm.getDeleteAny() == true){
							authority = "ROLE_"+url+"_deleteAny_Y";
							grant.add(new SimpleGrantedAuthority(authority));
							//System.out.println(authority);
						}
						if(perm.getDeleteOwn()==true){
							authority = "ROLE_"+url+"_deleteOwn_Y";
							grant.add(new SimpleGrantedAuthority(authority));
							//System.out.println(authority);
						}
						if(perm.getEditAny()==true){
							authority = "ROLE_"+url+"_editAny_Y";
							grant.add(new SimpleGrantedAuthority(authority));
							//System.out.println(authority);
						}
						
						if(perm.getEditOwn()==true){
							authority = "ROLE_"+url+"_editOwn_Y";
							grant.add(new SimpleGrantedAuthority(authority));
							//System.out.println(authority);
						}
						
					}
					
				}
				
			}
			 return new UsernamePasswordAuthenticationToken
		              (username, password,grant);
		}
		
		else{
			return null;
		}
		
		} catch (Exception e) {
			System.out.println("bad credential");
			 throw new
             BadCredentialsException("External system authentication failed");
		}
		/*
		else{
			 throw new
             BadCredentialsException("External system authentication failed");
		}*/
		// TODO Auto-generated method stub
	//	return new UsernamePasswordAuthenticationToken(name,password, grant);
	}

	@Override
	public boolean supports(Class<?> arg) {
		return arg.equals(UsernamePasswordAuthenticationToken.class);
	}
	

}
