package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * Batch insert dish flavor
     * @param flavors
     */
    void batchInsert(List<DishFlavor> flavors);

    /**
     * Delete dish flavor by dish id
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * Batch delete dish flavor
     * @param ids
     */
    void batchDelete(List<Long> ids);

    /**
     * Query dish flavor by dish id
     * @param dishId
     * @return List<DishFlavor> dish flavor list
     */
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
