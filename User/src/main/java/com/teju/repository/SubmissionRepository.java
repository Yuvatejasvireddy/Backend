package com.teju.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teju.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long> {

	@Query(value="select * from submission where taskid=:id",nativeQuery=true)
	List<Submission> findbyTaskid(Long id);

	
}
