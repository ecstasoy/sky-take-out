package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * Query employee by username
     * @param username
     * @return Employee
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Insert("insert into employee(username, name, password, phone, sex, id_number, create_time, update_time, create_user, update_user, status) " +
            "values(#{username}, #{name}, #{password}, #{phone}, #{sex}, #{idNumber}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser}, #{status})")
    @AutoFill(value=OperationType.INSERT)
    void insert(Employee employee);

    /**
     * Query employee list by page
     * @param employeePageQueryDTO
     * @return Page<Employee>
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Dynamically update employee information
     * @param employee
     */
    @AutoFill(value=OperationType.UPDATE)
    void update(Employee employee);

    /**
     * Query employee by id
     * @param id: employee id
     * @return Employee
     */
    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);
}
