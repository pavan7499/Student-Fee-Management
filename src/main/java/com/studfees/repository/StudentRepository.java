package com.studfees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studfees.entity.StudentFees;
@Repository
public interface StudentRepository extends JpaRepository<StudentFees, Long> {

	 
	
}
