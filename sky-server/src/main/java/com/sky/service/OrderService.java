package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    /**
     * Submit order
     *
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * Order payment
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * Order success, update order status
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * Query user orders
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    PageResult pageQueryUser(int page, int pageSize, Integer status);

    /**
     * Query order details
     * @param orderId
     * @return
     */
    OrderVO getOrderDetail(Long orderId);

    /**
     * Cancel order by order id
     * @param orderId
     */
    void cancelOrderById(Long orderId);

    /**
     * Repeat order
     * @param orderId
     */
    void repeatOrder(Long orderId);

    /**
     * Page query order
     * @param ordersPageQueryDTO
     * @return
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * Confirm order
     * @param ordersConfirmDTO
     */
    void confirmOrder(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * Reject order
     * @param ordersRejectionDTO
     */
    void rejectOrder(OrdersRejectionDTO ordersRejectionDTO);

    /**
     * Cancel order
     * @param ordersCancelDTO
     */
    void cancelOrder(OrdersCancelDTO ordersCancelDTO);

    /**
     * Deliver order
     * @param orderId
     */
    void deliverOrder(Long orderId);

    /**
     * Complete order
     * @param orderId
     */
    void completeOrder(Long orderId);

    /**
     * Remind order
     * @param orderId
     */
    void remindOrder(Long orderId);

    /**
     * Query order statistics
     * @return
     */
    OrderStatisticsVO statistics();
}
