package com.sky.controller.user;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Api(tags = "Order APIs")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    @ApiOperation("Submit order")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        log.info("Submit order: {}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /**
     * Order payment
     *
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    @ApiOperation("Order payment")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("Order payment：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("Generate pre-payment transaction：{}", orderPaymentVO);

        //Simulate order payment success
        orderService.paySuccess(ordersPaymentDTO.getOrderNumber());
        log.info("Simulated order payment success: {}", ordersPaymentDTO.getOrderNumber());
        return Result.success(orderPaymentVO);
    }

    /**
     * Query user orders
     *
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    @GetMapping("/historyOrders")
    @ApiOperation("Query user orders")
    public Result<PageResult> page(int page, int pageSize, Integer status) {
        PageResult pageResult = orderService.pageQueryUser(page, pageSize, status);
        return Result.success(pageResult);
    }

    /**
     * Query order details
     *
     * @param orderId
     * @return
     */
    @GetMapping("orderDetail/{orderId}")
    @ApiOperation("Query order details")
    public Result<OrderVO> details(@PathVariable Long orderId) {
        OrderVO orderVO = orderService.getOrderDetail(orderId);
        return Result.success(orderVO);
    }

    /**
     * Cancel order
     *
     * @param orderId
     * @return
     */
    @PutMapping("/cancel/{orderId}")
    @ApiOperation("Cancel order")
    public Result cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrderById(orderId);
        return Result.success();
    }

    /**
     * Repeat order
     *
     * @param orderId
     * @return
     */
    @PostMapping("/repetition/{orderId}")
    @ApiOperation("Repeat order")
    public Result repeatOrder(@PathVariable Long orderId) {
        orderService.repeatOrder(orderId);
        return Result.success();
    }
}
