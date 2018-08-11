package com.emp.ctrl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.model.Employee;
import com.emp.service.EmployeeService;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/emp")
@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	

	@Autowired
	Gson gson;
	
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	
	/**
	 * REST API to provide all Employee Details
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "Find All Employees", notes = "returns List of Employees in DB")
	public List<Employee>  getEmployeeList(){
		return employeeService.getEmployeeList();
	}
	
	/**
	 * REST API to fetch details a specific Employee
	 * @param id
	 * @return
	 */
	@GetMapping
	@RequestMapping("/{id}")
	@ApiOperation(value = "Find an Employee by ID", notes = "returns specified Employee of its ID")
	public Employee getEmployee(@PathVariable Long id ){
		return employeeService.getEmployee(id);
	}
	
	
	/**
	 * REST API to Add a new Employee
	 * @param employee
	 * @return
	 */
	@PostMapping
	@ApiOperation(value = "Creates a new Employee in DB", notes = "ID and Photo URL are auto-generated.")
	public Employee addEmployee(@RequestBody Employee employee){
		Employee emp = employeeService.addEmployee(employee);
		simpMessagingTemplate.convertAndSend("/topic/emplist",gson.toJson(employeeService.getEmployeeList()));
		return emp;
	}

}
