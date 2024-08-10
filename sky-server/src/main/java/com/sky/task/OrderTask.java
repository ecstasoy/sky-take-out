package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Order task
 */
@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * Process timeout order
     */
    @Scheduled(cron = "0 * * * * ? ")
    public void processTimeOutOrder() {
        log.info("Process timeout order: {}", LocalDateTime.now());
        //select * from orders where status = 0 and create_time < (now() - interval 15 minute)
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTime(Orders.PENDING_PAYMENT, LocalDateTime.now().minusMinutes(15));

        if(ordersList != null && !ordersList.isEmpty()) {
            ordersList.forEach(orders -> {
                log.info("Timeout order: {}", orders);
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("Timeout order");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            });
        }
    }

    /**
     * Process delivering order
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveringOrder() {
        log.info("Process delivering order: {}", LocalDateTime.now());
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTime(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().minusMinutes(60));
            ordersList.forEach(orders -> {
                log.info("Delivering order: {}", orders);
                orders.setStatus(Orders.COMPLETED);
                orders.setDeliveryTime(LocalDateTime.now());
                orderMapper.update(orders);
            });
    }
}
