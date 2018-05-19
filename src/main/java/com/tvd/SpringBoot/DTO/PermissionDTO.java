package com.tvd.SpringBoot.DTO;

import javax.persistence.*;

@Entity
public class PermissionDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PERM_ID")
	private int id;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="PERM_CREATE")
	private Boolean create=false;
	
	
	@Column(name="PERM_VIEW")
	private Boolean view=false;
	
	@Column(name="PERM_EDIT_OWN")
	private Boolean editOwn=true;
	
	@Column(name="PERM_EDIT_ANY")
	private Boolean editAny=false;
	
	@Column(name="PERM_DELETE_OWN")
	private Boolean deleteOwn=true;
	
	@Column(name="PERM_DELETE_ANY")
	private Boolean deleteAny=false;
	
	
	@ManyToOne( fetch=FetchType.EAGER)
	@JoinColumn(name="ROLE_ID")
	private Role userRoles;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Boolean getCreate() {
		return create;
	}


	public void setCreate(Boolean create) {
		this.create = create;
	}


	public Boolean getView() {
		return view;
	}


	public void setView(Boolean view) {
		this.view = view;
	}


	public Boolean getEditOwn() {
		return editOwn;
	}


	public void setEditOwn(Boolean editOwn) {
		this.editOwn = editOwn;
	}


	public Boolean getEditAny() {
		return editAny;
	}


	public void setEditAny(Boolean editAny) {
		this.editAny = editAny;
	}


	public Boolean getDeleteOwn() {
		return deleteOwn;
	}


	public void setDeleteOwn(Boolean deleteOwn) {
		this.deleteOwn = deleteOwn;
	}


	public Boolean getDeleteAny() {
		return deleteAny;
	}


	public void setDeleteAny(Boolean deleteAny) {
		this.deleteAny = deleteAny;
	}


	public Role getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(Role userRoles) {
		this.userRoles = userRoles;
	}
	
	
	
	
	
}
