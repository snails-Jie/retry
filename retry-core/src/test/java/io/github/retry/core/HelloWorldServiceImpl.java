package io.github.retry.core;

/**
 * @ClassName HelloWorldServiceImpl
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/12/23 22:14
 **/
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String returnHelloWorld() {
        return "hello world";
    }

    @Override
    public String returnHelloWorldWithName(User user) {
        int i = 1/0;
        return "Hello world Name";
    }
}
