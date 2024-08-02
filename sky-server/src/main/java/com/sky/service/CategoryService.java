package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import java.util.List;

public interface CategoryService {

    /**
     * Add new category
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * Query by page
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * Delete category by id
     * @param id
     */
    void deleteById(Long id);

    /**
     * Update category
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * Start or stop category
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * Query category by type
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
