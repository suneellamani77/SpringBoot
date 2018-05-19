package com.tvd.SpringBoot.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tvd.SpringBoot.PermissionRepository;
import com.tvd.SpringBoot.RolesRepository;
import com.tvd.SpringBoot.UserRepository;
import com.tvd.SpringBoot.DTO.PermissionDTO;
import com.tvd.SpringBoot.DTO.PermissionWrapper;
import com.tvd.SpringBoot.DTO.Role;
import com.tvd.SpringBoot.DTO.User;

@RestController
public class FirstBootController {
	
	
	HttpSession Session=null;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RolesRepository Rolerepository;
	
	@Autowired
	private PermissionRepository permssionRepo;

	@GetMapping(value={"/","/home","/index"})
	@PreAuthorize("hasAnyRole('home_view_Y')")
	public ModelAndView  index(){ 
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("title", "Home");
		mv.addObject("userClicksHome", true);
		return mv;
	}  

	@GetMapping(value="/addUser")
	public ModelAndView  addUser(){ 
		ModelAndView mv=new ModelAndView("home");

		List<User> data=(List<User>) repository.findAll();
		
		
		mv.addObject("title", "About Us");
		mv.addObject("data", data);
		mv.addObject("data2", Rolerepository.findAll());
		mv.addObject("userClicksAbout", true);
		return mv;
	}  

	@GetMapping(value="/addRole")
	public ModelAndView  addRole(){ 
		ModelAndView mv=new ModelAndView("home");
		
		List<Role> roles=(List<Role>) Rolerepository.findAll();
		for (Role role : roles) {
			System.err.println(role+"----");
		}
		mv.addObject("title", "Service");
		mv.addObject("userClicksServices", true);
		mv.addObject("roles",roles);
		return mv;
	} 

	@GetMapping(value="/addPermission")
	public ModelAndView  addPermission(@RequestParam(value="url", required=false) String url){ 
		ModelAndView mv=new ModelAndView("home");
		List<PermissionDTO> perms = new ArrayList<PermissionDTO>();
		
		if(url!=null){
			List<Object[]> perm1=permssionRepo.getPermission(url);
			for (Object[] p : perm1) {
				
				PermissionDTO perm=new PermissionDTO();
				Role role=new Role();
				
				try {
					role.setId(Integer.parseInt(p[0].toString()));
				} catch (NullPointerException e) {
				}
				
				
				try {
					role.setName(p[1].toString());
				} catch (Exception e) {
				}
				perm.setUserRoles(role);
				
				try{
					perm.setId(Integer.parseInt(p[2].toString()));
				}
				catch (Exception e) {
				}
				
				try{
					perm.setView(Boolean.valueOf(p[3].toString()));
				}
				catch (Exception e) {
				}
				try{
					perm.setCreate(Boolean.valueOf(p[4].toString()));
				}
				catch (Exception e) {
				}
				
				
				try{
					perm.setEditAny(Boolean.valueOf(p[5].toString()));
				}
				catch (Exception e) {
				}
				
				try{
					perm.setEditOwn(Boolean.valueOf(p[6].toString()));
				}
				catch (Exception e) {
				}
				
				try{
					perm.setDeleteAny(Boolean.valueOf(p[7].toString()));
				}
				catch (Exception e) {
				}
				
				try{
					perm.setDeleteOwn(Boolean.valueOf(p[8].toString()));
				}
				catch (Exception e) {
				}
				
				try {
					perm.setUrl(p[9].toString());
				} catch (Exception e) {
				}
				
				perms.add(perm);
			}
		}
		
		
		
		mv.addObject("data",perms);
		mv.addObject("title", "Contact Us");
		mv.addObject("userClicksContact", true);
		return mv;
	} 


	@PostMapping(value={"/saveUser"})
	public ModelAndView  saveUser(@ModelAttribute User dto){ 
		List<Role>  r1=new ArrayList<Role>();	
		
		for(Role r: dto.getRoles()){
		if(r.getId()>0){
			Role r2=new Role();
			r2.setId(r.getId());
			r1.add(r2);
		}
		
		System.err.println("----------"+r.getId());
	}
	
		dto.setRoles(r1);
		repository.save(dto);
		return new ModelAndView("redirect:/addUser");  
		
	}  

	@GetMapping(value={"/delete"})
	public ModelAndView  delete(@RequestParam int id){ 
		System.out.println("id is --------------"+id);
		repository.delete(id);
		return new ModelAndView("redirect:/addUser");  
	}  

	@GetMapping(value={"/edit/{id}"})
	public ModelAndView  edit(@PathVariable int id, @ModelAttribute User dto){ 

		User data1=repository.findOne(id);
		ModelAndView mv=new ModelAndView("home");
		
		List<User> data=(List<User>) repository.findAll();
		mv.addObject("data", data);
		mv.addObject("data2", Rolerepository.findAll());
		mv.addObject("title", "About Us");
		mv.addObject("data1", data1);
		mv.addObject("userClicksAbout", true);
		return mv;
	}  
	
	@PostMapping(value={"/saveRole"})
	public ModelAndView  saveRole(@ModelAttribute Role dto){ 
		System.err.println("name of the user is "+dto.getName());
		Rolerepository.save(dto);
		return new ModelAndView("redirect:/addRole");  
	}  
	
	
	@RequestMapping(value={"/index11"})
	public ModelAndView index11(Principal principal,HttpServletRequest request){
		Session=request.getSession();
		Session.setAttribute("username", principal.getName());
		System.out.println("user name is "+principal.getName());
		return new ModelAndView("redirect:/user");
	}
	
	/*@RequestMapping(value={"/test"})
	public ModelAndView home(){
		ModelAndView mv=new ModelAndView("test");
		return mv;
	}
	
	@RequestMapping(value={"/user"})
    public ModelAndView welcome(){
		ModelAndView mv=new ModelAndView("user");
        return mv;
    }
	
	
	@RequestMapping(value="/admin")
	public ModelAndView admin(){
		System.out.println("admin page");
		ModelAndView mv=new ModelAndView("admin");
		return mv;
	}*/
	
	@RequestMapping(value={"/login"})
	public ModelAndView login(Model model, String error, String logout,Principal p, HttpServletRequest request){
		
		if(error!=null){
			model.addAttribute("error", "Your UserName and Password is invalid!!");
		}
		
		if(logout!= null){
			model.addAttribute("message","You Have been Logged out Successfully");
		}
			return new ModelAndView("login");
	}
	
	@RequestMapping(value={"/logout"})
	public ModelAndView logoutsuccessful(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		SecurityContextHolder.clearContext();
		if(session!=null){
			session.invalidate();
		}
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/403")
	public ModelAndView Error403(){
		
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("title", "403");
		mv.addObject("accessDenied", true);
		return mv;
		
	}
	
	@PostMapping(value="/savePermissions")
	public ModelAndView savePermissions(@ModelAttribute PermissionWrapper dto,@RequestParam(value="url", required=false) String url){
		List<PermissionDTO> permission=dto.getPermissions();
		for (PermissionDTO p : permission) {
			p.setUrl(url);
			permssionRepo.save(p);
		}
		
		
		return new ModelAndView("redirect:/addPermission");
	}
	
	@GetMapping(value={"/deleteRole"})
	public ModelAndView  deleteRole(@RequestParam int id){ 
		Rolerepository.delete(id);
		return new ModelAndView("redirect:/addRole");  
	}  

	@GetMapping(value={"/editRole/{id}"})
	public ModelAndView  editRole(@PathVariable int id, @ModelAttribute User dto){ 

		Role role=Rolerepository.findOne(id);
		ModelAndView mv=new ModelAndView("home");
		
		List<Role> roles=(List<Role>) Rolerepository.findAll();
		mv.addObject("title", "Service");
		mv.addObject("userClicksServices", true);
		mv.addObject("roles",roles);
		mv.addObject("data1", role);
		return mv;
	}  
	
}
