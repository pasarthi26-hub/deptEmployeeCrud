/**
 * 
 */
package com.department.crud.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Parth, Sarthi
 * @createdon : 24 Feb 2021
 */
public class EmployeeDepartmentInputDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5392347130093612751L;

	private Long departmentId;
	private String departmentName;
	private String description;
	private List<EmployeeDetails> employeeDetails;


	/**
	 * @return the departmentId
	 */
	public Long getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the employeeDetails
	 */
	public List<EmployeeDetails> getEmployeeDetails() {
		return employeeDetails;
	}

	/**
	 * @param employeeDetails the employeeDetails to set
	 */
	public void setEmployeeDetails(List<EmployeeDetails> employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

}
