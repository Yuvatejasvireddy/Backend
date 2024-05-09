package com.teju.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teju.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

	@Query(value="select * from task where assigneduserid=:userid",nativeQuery=true)
	List<Task> findByAssignedUser(Long userid);
	

}
