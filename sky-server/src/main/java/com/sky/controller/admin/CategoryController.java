package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Category management
 */
@RestController
@RequestMapping("/admin/category")
@Api(tags = "APIs for category management")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Add new category
     * @param categoryDTO
     * @return Result
     */
    @PostMapping
    @ApiOperation("Add new category")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO){
        log.info("Add new category：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /**
     * Query by page
     * @param categoryPageQueryDTO
     * @return Result
     */
    @GetMapping("/page")
    @ApiOperation("Query by page")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("Query by page：{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Delete category by id
     * @param id
     * @return Result
     */
    @DeleteMapping
    @ApiOperation("Delete category by id")
    public Result<String> deleteById(Long id){
        log.info("Delete category：{}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * Update category
     * @param categoryDTO
     * @return Result
     */
    @PutMapping
    @ApiOperation("Update category")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * Start or stop category
     * @param status
     * @param id
     * @return Result
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Start or stop category")
    public Result<String> startOrStop(@PathVariable("status") Integer status, Long id){
        categoryService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * Query category by type
     * @param type
     * @return Result
     */
    @GetMapping("/list")
    @ApiOperation("Query category by type")
    public Result<List<Category>> list(Integer type){
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
