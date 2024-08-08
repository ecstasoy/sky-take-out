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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @Autowired
    private RedisTemplate redisTemplate;

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

        String key =  "dish_" + dishDTO.getCategoryId();
        cleanCache(key);

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

        //Delete the cache
        cleanCache("dish_*");

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

    /**
     * Update dish
     * @param dishDTO
     * @return Result
     */
    @PutMapping
    @ApiOperation(value = "Update dish")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("Update dish: {}", dishDTO);
        dishService.updateWithFlavor(dishDTO);

        //Delete the cache
        cleanCache("dish_*");

        return Result.success();
    }

    /**
     * Query dish by category id
     * @param categoryId
     * @return Result<List<Dish>>
     */
    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId) {
        log.info("Query dish by category id: {}", categoryId);
        List<Dish> dishes = dishService.list(categoryId);
        return Result.success(dishes);
    }

    /**
     * Enable or disable dish
     * @param status
     * @param id
     * @return Result
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Enable or disable dish")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        log.info("Start or stop dish: {}, {}", status, id);
        dishService.startOrStop(status, id);

        //Delete the cache
        cleanCache("dish_*");

        return Result.success();
    }

    private void cleanCache(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
