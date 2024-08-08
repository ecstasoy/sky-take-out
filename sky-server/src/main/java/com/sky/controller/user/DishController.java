package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@Api(tags = "User Dish APIs")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Query dishes by category id
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Query dishes by category id")
    public Result<List<DishVO>> list(Long categoryId) {
        //Construct keys in Redis, rule: dish_categoryId
         String key =  "dish_" + categoryId;

        //Check if the data entry is in Redis
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if(list != null && !list.isEmpty()) {
            log.info("Get data from Redis: {}", list);
            return Result.success(list);
        }

        //If not existed, query the data from the database and save it to Redis
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);//查询起售中的菜品

        list = dishService.listWithFlavor(dish);
        redisTemplate.opsForValue().set(key, list);

        return Result.success(list);
    }

}
