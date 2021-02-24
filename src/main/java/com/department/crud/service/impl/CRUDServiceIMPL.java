/**
 * 
 */
package com.department.crud.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.department.crud.domain.Department;
import com.department.crud.domain.Employee;
import com.department.crud.dto.EmployeeDepartmentInputDTO;
import com.department.crud.dto.EmployeeDetails;
import com.department.crud.repository.DepartmentRepository;
import com.department.crud.repository.EmployeeRepository;

/**
 * @author : Parth, Sarthi
 * @createdon : 24 Feb 2021
 */
@Service
public class CRUDServiceIMPL {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	/**
	 * 
	 * @param empId
	 * @return
	 */

	public String getDepartmentByEmpId(Integer empId) {

		Optional<Employee> optionalEmp = employeeRepository.findById(empId);

		if (optionalEmp.isPresent()) {

			return optionalEmp.get().getDepartment().getDeptName();

		} else {
			// LOGGER ERROR- Exception hadnling
			System.err.println("No Employee Exists with EmpId : " + empId);
		}

		return null;

	}

	/**
	 * 
	 * @param deptId
	 * @return
	 */

	public List<String> getEmployeeFromDept(Long deptId) {

		Optional<Department> optional = departmentRepository.findById(deptId);

		if (optional.isPresent()) {

			return optional.get().getEmployees().stream().map(Employee::getEmployeeName).collect(Collectors.toList());
		} else {

			// ERROR handling
			System.err.println("No Department Exists with DeptId: " + deptId);

		}

		return new ArrayList<>();

	}

	public void saveDepartment(List<EmployeeDepartmentInputDTO> dtos) {

		dtos.stream().forEach(eachDto -> {

			// Check if Department already present

			Optional<Department> optionalDepart = departmentRepository.findById(eachDto.getDepartmentId());

			if (optionalDepart.isPresent()) {
				// Update the Department Details-- like if any new Employee

				Department department = (Department) optionalDepart.get();

				eachDto.getEmployeeDetails().stream().forEach(emp -> {

					Optional<Employee> optionalEmp = employeeRepository.findById(emp.getEmployeeId());

					if (!optionalEmp.isPresent()) {
						Employee employee = getEmployeeObj(emp);
						employee.setDepartment(department);
						department.getEmployees().add(employee);
					}

				});

				departmentRepository.save(department);

			} else {
				// Create a new Department
				Department department = getDeptEmployeeDto(eachDto);
				departmentRepository.save(department);
			}

		});

	}

	/**
	 * @param emp
	 * @return
	 */
	private Employee getEmployeeObj(EmployeeDetails emp) {
		Employee employee = new Employee();
		employee.setAddress(emp.getAddress());
		employee.setEmployeeId(emp.getEmployeeId());
		employee.setEmployeeName(emp.getEmployeeName());
		employee.setPhoneNumber(emp.getPhoneNumber());

		return employee;
	}

	/**
	 * @param eachDto
	 * @return
	 */
	private Department getDeptEmployeeDto(EmployeeDepartmentInputDTO eachDto) {

		Department department = new Department();
		department.setDepartmentId(eachDto.getDepartmentId());
		department.setDeptName(eachDto.getDepartmentName());
		department.setDescription(eachDto.getDescription());

		Set<Employee> employess = new HashSet<>();

		if (!CollectionUtils.isEmpty(eachDto.getEmployeeDetails())) {

			eachDto.getEmployeeDetails().stream().forEach(eachemp -> {

				Employee employee = new Employee();

				employee.setEmployeeId(eachemp.getEmployeeId());
				employee.setAddress(eachemp.getAddress());
				employee.setDepartment(department);
				employee.setEmployeeName(eachemp.getEmployeeName());
				employee.setPhoneNumber(eachemp.getPhoneNumber());

				employess.add(employee);
			});

			department.setEmployees(employess);

		}
		return department;
	}

}
