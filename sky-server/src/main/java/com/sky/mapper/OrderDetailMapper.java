package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    /**
     * Batch insert order details
     *
     * @param orderDetailList
     */
    void batchInsert(List<OrderDetail> orderDetailList);
}
