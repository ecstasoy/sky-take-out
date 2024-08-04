package com.sky.service;

import com.sky.dto.DishDTO;

public interface DishService {
    /**
     * Save dish with flavor
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);
}
