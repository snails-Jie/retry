package io.github.retry.service;

/**
 * @ClassName RelayBuyService
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/12/24 17:06
 **/

import io.github.retry.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class RelayBuyService {

    public String pushOrder(Order order){
        int a = 1/0;
        return "下单成功";
    }
}
