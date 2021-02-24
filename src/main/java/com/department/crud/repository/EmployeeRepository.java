/**
 * 
 */
package com.department.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.department.crud.domain.Employee;

/**
 * @author : Parth, Sarthi
 * @createdon : 24 Feb 2021
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
