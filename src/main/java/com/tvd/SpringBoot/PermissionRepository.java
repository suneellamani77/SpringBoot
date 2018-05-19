package com.tvd.SpringBoot;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tvd.SpringBoot.DTO.PermissionDTO;

public interface PermissionRepository extends CrudRepository<PermissionDTO, Integer>{
	
	@Query(value = "SELECT u.role_id,u.name,y.PERM_ID,y.PERM_VIEW,y.PERM_CREATE,y.PERM_EDIT_ANY,y.PERM_EDIT_OWN,"
			+ " y.PERM_DELETE_ANY,y.PERM_DELETE_OWN,y.URL FROM role u LEFT JOIN permissiondto Y ON y.ROLE_ID=u.ROLE_ID AND url=:url",
            nativeQuery=true
    )
    public List<Object[]> getPermission(@Param("url") String url);

}
