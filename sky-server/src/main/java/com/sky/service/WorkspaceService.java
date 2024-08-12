package com.sky.service;

import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import java.time.LocalDateTime;

public interface WorkspaceService {

    /**
     * Statistics business data based on time period
     * @param begin
     * @param end
     * @return
     */
    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);

    /**
     * Query order management data
     * @return
     */
    OrderOverViewVO getOrderOverView();

    /**
     * Query dish overview
     * @return
     */
    DishOverViewVO getDishOverView();

    /**
     * Query set meal overview
     * @return
     */
    SetmealOverViewVO getSetmealOverView();

}
