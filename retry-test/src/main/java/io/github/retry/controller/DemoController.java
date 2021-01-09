package io.github.retry.controller;

import io.github.retry.core.FunctionSerializable;
import io.github.retry.core.Retry;
import io.github.retry.core.decorators.Decorators;
import io.github.retry.core.entity.RetryEntity;
import io.github.retry.entity.Order;
import io.github.retry.retry.RetryDao;
import io.github.retry.service.RelayBuyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @ClassName DemoController
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/12/24 17:08
 **/
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private RelayBuyService relayBuyService;

    @Resource
    private RetryDao retryDao;

    @RequestMapping("/order")
    public String testOrder(String name){
        Order order = new Order();
        order.setOrderNumber("1231231");
        order.setOrderUser("zhangjie");

        RetryEntity retryData = new RetryEntity("221312","relayBuyService","pushOrder","ssss","空指针异常");


        FunctionSerializable<Order,String> function = Decorators
                                                        .ofFunction(relayBuyService::pushOrder)
                                                        .withRetry(Retry.ofRegistryStoreAndData(retryDao,retryData))
                                                        .decorate();

        return function.apply(order);
    }
}
