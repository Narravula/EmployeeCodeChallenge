package com.emp.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.model.Employee;
import com.emp.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	Random random;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * A service method to return List of all Employees
	 * @return
	 */
	public List<Employee>  getEmployeeList(){
		return employeeRepository.findAll();
	}
	
	/**
	 * A Service method return Details of an Employee based on given ID
	 * @param id
	 * @return
	 */
	public Employee getEmployee(Long id ){
		Optional<Employee> data = employeeRepository.findById(id);
		if(!data.isPresent())
			new IllegalArgumentException("Invalid ID Found");
		
		return data.get();
	}
	
	/**
	 * A Service method to add an new Employee
	 * @param emp
	 * @return
	 */
	public Employee addEmployee(Employee emp){
		
		if(emp.getPhoto() == null){
			int num = random.nextInt(100);
			emp.setPhoto("https://randomuser.me/api/portraits/men/"+num+".jpg");
		}
		
		return employeeRepository.save(emp);
		
	}
	
}
