package com.sky.service.impl;

import com.github.pagehelper.*;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.*;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
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

        // Set the create time and update time of the employee
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        // Set current user as the creator and updater of the employee
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

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
}
