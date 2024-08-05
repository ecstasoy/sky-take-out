package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * Query setmeal ids by dish id
     * @param dishIds
     * @return List<Long> setmeal ids
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    /**
     * Batch insert setmeal dish
     * @param setmealDishes
     */
    void batchInsert(List<SetmealDish> setmealDishes);

    /**
     * Delete by setmeal id
     * @param setmealId
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    /**
     * Query by setmeal id
     * @param setmealId
     * @return List<SetmealDish>
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
}
