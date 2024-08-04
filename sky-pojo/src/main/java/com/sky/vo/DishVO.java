package com.sky.vo;

import com.sky.entity.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishVO implements Serializable {

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
    //status 0: disable 1: enable
    private Integer status;
    //create time
    private LocalDateTime updateTime;
    //update time
    private String categoryName;
    //flavors
    private List<DishFlavor> flavors = new ArrayList<>();

    //private Integer copies;
}
