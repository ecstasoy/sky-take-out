package com.sky.dto;

import com.sky.entity.DishFlavor;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO implements Serializable {

    private Long id;
    //name
    private String name;
    //category id
    private Long categoryId;
    //price
    private BigDecimal price;
    //image url
    private String image;
    //description
    private String description;
    //status
    private Integer status;
    //flavors
    private List<DishFlavor> flavors = new ArrayList<>();

}
