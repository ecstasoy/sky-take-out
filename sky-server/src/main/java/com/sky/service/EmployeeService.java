package com.sky.service;

import com.sky.constant.StatusConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import org.springframework.beans.BeanUtils;

public interface EmployeeService {

    /**
     * employee login
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * add new employee
     * @param employeeDTO
     * @return
     */
    void save(EmployeeDTO employeeDTO);
}
