package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        //Check if the item is already in the shopping cart
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);

        //If the item is already in the shopping cart, update the quantity
         if(list != null && list.size() > 0) {
             ShoppingCart cart = list.get(0);
             cart.setNumber(cart.getNumber() + 1);
             shoppingCartMapper.update(cart);
         } else {
             //If the item is not in the shopping cart, add it to the shopping cart
             Long dishId = shoppingCartDTO.getDishId();
             if(dishId != null) {
                 Dish dish = dishMapper.getById(dishId);

                 shoppingCart.setName(dish.getName());
                 shoppingCart.setImage(dish.getImage());
                 shoppingCart.setAmount(dish.getPrice());
             } else {
                 Long setmealId = shoppingCartDTO.getSetmealId();
                 Setmeal setmeal = setmealMapper.getById(setmealId);

                 shoppingCart.setName(setmeal.getName());
                 shoppingCart.setImage(setmeal.getImage());
                 shoppingCart.setAmount(setmeal.getPrice());
             }
             shoppingCart.setNumber(1);
             shoppingCart.setCreateTime(LocalDateTime.now());
             shoppingCartMapper.insert(shoppingCart);
         }

    }

    public List<ShoppingCart> showShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(userId)
                .build();
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);
        return list;
    }

    public void subShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);

        if(list != null && list.size() > 0) {
            ShoppingCart cart = list.get(0);
            cart.setNumber(cart.getNumber() - 1);
            if(cart.getNumber() <= 0) {
                shoppingCartMapper.delete(cart.getId());
            } else {
                shoppingCartMapper.update(cart);
            }
        }
    }

    public void cleanShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        shoppingCartMapper.deleteByUserId(userId);
    }
}
