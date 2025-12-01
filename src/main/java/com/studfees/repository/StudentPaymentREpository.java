package com.studfees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studfees.entity.StudentFees;
import com.studfees.entity.StudentPayment;
 
@Repository
public interface StudentPaymentREpository extends JpaRepository<StudentPayment, Long>{

	List<StudentPayment> findByStudent(StudentFees student);




}
