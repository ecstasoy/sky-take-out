package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Employee management
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "API for employee management")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * login
     *
     * @param employeeLoginDTO
     * @return EmployeeLoginVO
     */
    @PostMapping("/login")
    @ApiOperation(value = "Employee login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("Employee login：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //When the employee logs in successfully, a token is generated and returned to the front end
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * logout
     *
     * @return String
     */
    @PostMapping("/logout")
    @ApiOperation(value = "Employee logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * add new employee
     *
     * @param employeeDTO
     * @return Result
     */
    @PostMapping
    @ApiOperation(value = "Add employee")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Add employee：{}", employeeDTO);
        System.out.println("Current thread ID: " + Thread.currentThread().getId());
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * query employee list
     *
     * @param employeePageQueryDTO
     * @return PageResult
     */
    @GetMapping("/page")
    @ApiOperation(value = "Query employee list")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("Query employee list：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * start or stop employee account
     *
     * @param status
     * @param id
     * @return Result
     */
    // Non-query return value does not need to use generic types
    @PostMapping("/status/{status}")
    @ApiOperation(value = "Start or stop employee account")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("Start or stop employee account：status={}, id={}", status, id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * query employee by id
     *
     * @param id
     * @return Employee
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Query employee by id")
    public Result<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    @PutMapping
    @ApiOperation(value = "Update employee information")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Update employee information：{}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }
}
