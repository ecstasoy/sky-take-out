package com.sky.controller.admin;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Slf4j
@Api(tags = "Admin order APIs")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Page query order
     *
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/conditionSearch")
    @ApiOperation("Condition search order")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        log.info("Condition search order: {}", ordersPageQueryDTO);
        PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Query order details
     *
     * @param orderId
     * @return
     */
    @GetMapping("/details/{orderId}")
    @ApiOperation("Query order details")
    public Result<OrderVO> orderDetails(@PathVariable Long orderId) {
        log.info("Query order details: {}", orderId);
        OrderVO orderVO = orderService.getOrderDetail(orderId);
        return Result.success(orderVO);
    }

    /**
     * Confirm order
     *
     * @param ordersConfirmDTO
     * @return
     */
    @PutMapping("/confirm")
    @ApiOperation("Confirm order")
    public Result confirmOrder(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        log.info("Confirm order: {}", ordersConfirmDTO);
        orderService.confirmOrder(ordersConfirmDTO);
        return Result.success();
    }

    /**
     * Reject order
     *
     * @param ordersRejectionDTO
     * @return
     */
    @PutMapping("/reject")
    @ApiOperation("Reject order")
    public Result rejectOrder(@RequestBody OrdersRejectionDTO ordersRejectionDTO) {
        log.info("Reject order: {}", ordersRejectionDTO);
        orderService.rejectOrder(ordersRejectionDTO);
        return Result.success();
    }

    /**
     * Cancel order
     *
     * @param ordersCancelDTO
     * @return
     */
    @PutMapping("/cancel")
    @ApiOperation("Cancel order")
    public Result cancelOrder(@RequestBody OrdersCancelDTO ordersCancelDTO) {
        log.info("Cancel order: {}", ordersCancelDTO);
        orderService.cancelOrder(ordersCancelDTO);
        return Result.success();
    }

    /**
     * Order delivery
     *
     * @param orderId
     * @return
     */
    @PutMapping("/delivery/{orderId}")
    @ApiOperation("Order delivery")
    public Result deliverOrder(@PathVariable Long orderId) {
        log.info("Order delivery: {}", orderId);
        orderService.deliverOrder(orderId);
        return Result.success();
    }

    @PutMapping("/complete/{orderId}")
    @ApiOperation("Complete order")
    public Result completeOrder(@PathVariable Long orderId) {
        log.info("Complete order: {}", orderId);
        orderService.completeOrder(orderId);
        return Result.success();
    }
}
