package com.tvd.SpringBoot;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tvd.SpringBoot.DTO.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	@Query(value = "SELECT user_id FROM USER WHERE NAME=:username AND PASSWORD=:password",
            nativeQuery=true
    )
    public int findUser(@Param("username")String username,@Param("password") String password);
	
}
