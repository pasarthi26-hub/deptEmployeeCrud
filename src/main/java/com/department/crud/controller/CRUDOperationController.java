/**
 * 
 */
package com.department.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.crud.dto.EmployeeDepartmentInputDTO;
import com.department.crud.service.impl.CRUDServiceIMPL;

/**
 * @author : Parth, Sarthi
 * @createdon : 24 Feb 2021
 */

@RestController
@RequestMapping("/crudOperation")
public class CRUDOperationController {

	@Autowired
	private CRUDServiceIMPL crudService;

	@PostMapping("/saveDepartmentEmployee")
	public ResponseEntity<Void> saveDeplartmentEmployee(@RequestBody List<EmployeeDepartmentInputDTO> dtos) {

		crudService.saveDepartment(dtos);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getEmployeeByDept/{deptId}")
	public List<String> getEmployeesByDept(@PathVariable("deptId") Long deptId) {

		return crudService.getEmployeeFromDept(deptId);
	}

	@GetMapping("/getDeptByEmp/{empId}")
	public String getDepartmentByEmp(@PathVariable("empId") Integer empId) {
		
		//Either we can return Department name or Response Entity for Department

		return crudService.getDepartmentByEmpId(empId);
	}

}
