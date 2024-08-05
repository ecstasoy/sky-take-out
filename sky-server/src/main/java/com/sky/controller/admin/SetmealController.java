package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "APIs for setmeal management")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * Add new setmeal
     * @param setmealDTO
     * @return Result
     */
    @PostMapping
    @ApiOperation(value = "Add new setmeal")
    public Result saveWithDish(@RequestBody SetmealDTO setmealDTO) {
        log.info("Save setmeal with dish: {}", setmealDTO);
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    /**
     * Query setmeal by page
     * @param setmealPageQueryDTO
     * @return Result
     */
    @GetMapping("/page")
    @ApiOperation("Query setmeal by page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Delete setmeal by id(s)
     * @param ids
     * @return Result
     */
    @DeleteMapping
    @ApiOperation("Delete setmeal by id(s)")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("Delete setmeal: {}", ids);
        setmealService.batchDelete(ids);
        return Result.success();
    }

    /**
     * Query setmeal by id
     * @param id
     * @return Result
     */
    @GetMapping("/{id}")
    @ApiOperation("Query setmeal by id")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    /**
     * Update setmeal
     * @param setmealDTO
     * @return Result
     */
    @PutMapping
    @ApiOperation("Update setmeal")
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        log.info("Update setmeal: {}", setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * Start or stop setmeal
     * @param status
     * @param id
     * @return Result
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Start or stop setmeal")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("Start or stop setmeal: status={}, id={}", status, id);
        setmealService.startOrStop(status, id);
        return Result.success();
    }
}
