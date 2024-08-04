package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

public interface DishService {
    /**
     * Save dish with flavor
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * Query dish by page
     * @param dishPageQueryDTO
     * @return page result
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);
}
