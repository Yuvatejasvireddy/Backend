package com.teju.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teju.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	
	@Query(value="select * from users where email=:username",nativeQuery=true)
	public User findbyEmail(String username);
    
	@Query(value = "SELECT * FROM users WHERE role = 'user'", nativeQuery = true)
    List<User> findAllUsersByUserRole();



	
}
