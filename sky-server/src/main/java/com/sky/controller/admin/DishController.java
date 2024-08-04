package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
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

    @PostMapping
    @ApiOperation(value = "Add new dish")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("Save dish: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation(value = "Query dish by page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("Query dish by page: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete dish (in batch)")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("Delete dish: {}", ids);
        dishService.batchDelete(ids);
        return Result.success();
    }
}
