package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

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

    /**
     * Delete dish
     * @param ids
     */
    void batchDelete(List<Long> ids);

    /**
     * Query dish by id with flavor
     * @param id
     * @return DishVO
     */
    DishVO getByIdWithFlavor(Long id);


    /**
     * Update dish with flavor
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * Query dish by category id
     * @param categoryId
     * @return List<Dish>
     */
    List<Dish> list(Long categoryId);

    /**
     * Query list of dishes with flavors by category id
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
