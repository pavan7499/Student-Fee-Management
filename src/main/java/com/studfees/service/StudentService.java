package com.studfees.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studfees.entity.StudentFees;
import com.studfees.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository repo;

	public StudentFees adddetails(StudentFees studentfees) {

		return repo.save(studentfees);

	}

	public List<StudentFees> getAllStudents() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public boolean updateStudent(Long id, StudentFees updatedData) {
		
		Optional<StudentFees> exiting = repo.findById(id);

		if (exiting.isPresent()) {
			
			StudentFees st = exiting.get();
			st.setName(updatedData.getName());
			st.setGrade(updatedData.getGrade());
			st.setNumber(updatedData.getNumber());
			st.setSname(updatedData.getSname());
			
			repo.save(st);
			return true;
			
			
			
		}
		return false;
	}

		public boolean deletebyId(Long id) {
	
			Optional<StudentFees> exiting = repo.findById(id);
			
			if(exiting.isPresent()) {
				repo.deleteById(id);
			
				return true;
				
			
			}
			else {
				return false;
			}
			
		}



		public StudentFees getbyid(Long id) {
			Optional<StudentFees> exiting = repo.findById(id);
			

			if(exiting.isPresent()) {
				return exiting.get();
				
				
			}
			else {			
			return null;
			}
			
		}

	
}
