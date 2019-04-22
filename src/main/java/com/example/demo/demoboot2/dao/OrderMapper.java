package com.example.demo.demoboot2.dao;

import com.example.demo.demoboot2.vo.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-14 10:32
 * @Description:
 */
@Repository
public interface OrderMapper {

    List<OrderVO> queryOrderList(@Param("orderNo") String orderNo);
//    List<OrderVO> queryOrderList(String orderNo);
}
