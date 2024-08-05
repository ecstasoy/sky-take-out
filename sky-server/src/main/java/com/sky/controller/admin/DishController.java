package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Dish controller
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "APIs for dish management")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * Add new dish
     * @param dishDTO
     * @return Result
     */
    @PostMapping
    @ApiOperation(value = "Add new dish")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("Save dish: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * Query dish by page
     * @param dishPageQueryDTO
     * @return Result<PageResult>
     */
    @GetMapping("/page")
    @ApiOperation(value = "Query dish by page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("Query dish by page: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Update dish
     * @param ids
     * @return Result
     */
    @DeleteMapping
    @ApiOperation(value = "Delete dish (in batch)")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("Delete dish: {}", ids);
        dishService.batchDelete(ids);
        return Result.success();
    }

    /**
     * Query dish by id
     * @param id
     * @return Result<DishVO>
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Query dish by id")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("Query dish by id: {}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation(value = "Update dish")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("Update dish: {}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId) {
        log.info("Query dish by category id: {}", categoryId);
        List<Dish> dishes = dishService.list(categoryId);
        return Result.success(dishes);
    }
}
