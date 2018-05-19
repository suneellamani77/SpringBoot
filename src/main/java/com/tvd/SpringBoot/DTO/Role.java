package com.tvd.SpringBoot.DTO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private int id;
	
	private String name;
	
	@ManyToMany(mappedBy="roles",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	private List<User> users;
	
	@OneToMany(mappedBy="userRoles", fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<PermissionDTO> permission;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<PermissionDTO> getPermission() {
		return permission;
	}

	public void setPermission(List<PermissionDTO> permission) {
		this.permission = permission;
	}

	
}
