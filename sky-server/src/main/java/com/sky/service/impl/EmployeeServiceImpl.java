package com.sky.service.impl;

import com.github.pagehelper.*;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.*;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * employee login
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1.query the employee by username
        Employee employee = employeeMapper.getByUsername(username);

        //2.check the employee status
        if (employee == null) {
            //The account does not exist
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //Encrypt password passed in by user
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //Password error
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //Account locked
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3.return the employee
        return employee;
    }

    /**
     * add new employee
     * @param employeeDTO
     */
    public void save(EmployeeDTO employeeDTO) {
        System.out.println("Current thread ID: " + Thread.currentThread().getId());
        Employee employee = new Employee();

        // Copy the properties of the source object to the target object
        BeanUtils.copyProperties(employeeDTO, employee);

        // Set the status of the employee to normal
        employee.setStatus(StatusConstant.ENABLE);

        // Set the default password for the employee
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        employeeMapper.insert(employee);
    };

    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        // Begin paging query
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        // Query employee list
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

        // Get the total number of records and the list of records
        long total = page.getTotal();
        List<Employee> records = page.getResult();

        return new PageResult(total, records);
    }

    public void startOrStop(Integer status, Long id) {
        // update employee set status = #{status} where id = #{id}
        Employee employee = Employee.builder().status(status).id(id).build();
        employeeMapper.update(employee);
    }

    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        employee.setPassword("******");
        return employee;
    }

    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        employeeMapper.update(employee);
    }
}
