package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {

    //Employee name
    private String name;

    //Page number
    private int page;

    //Number of records per page
    private int pageSize;

}
