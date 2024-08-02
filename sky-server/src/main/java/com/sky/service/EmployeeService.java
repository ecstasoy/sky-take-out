package com.sky.service;

import com.sky.constant.StatusConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import org.springframework.beans.BeanUtils;

public interface EmployeeService {

    /**
     * employee login
     * @param employeeLoginDTO
     * @return Employee
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * add new employee
     * @param employeeDTO
     * @return void
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * Query employee list by page
     * @param employeePageQueryDTO
     * @return PageResult
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * start or stop employee
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * query employee by id
     * @param id
     * @return Employee
     */
    Employee getById(Long id);

    /**
     * update employee information
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
