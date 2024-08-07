package com.sky.service;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    /**
     * Save setmeal with dish
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * Query setmeal by page
     * @param setmealPageQueryDTO
     * @return page result
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * Delete setmeal
     * @param ids
     */
    void batchDelete(List<Long> ids);

    /**
     * Get setmeal by id
     * @param id
     * @return setmeal
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * Update setmeal
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * Start or stop setmeal
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * Query with conditions
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * Query list of dish items by id
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
