package io.github.retry.core;

import io.github.retry.core.decorators.Decorators;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @ClassName RetryTest
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/12/23 16:54
 **/
public class RetryTest {

    private HelloWorldService helloWorldService;

    private User user;

    @Before
    public void setUp(){
        user = new User("zhangjie",24);
        helloWorldService = mock(HelloWorldService.class);
    }


    @Test
    public void testParamRetry(){
        given(helloWorldService.returnHelloWorldWithName(user)).willReturn("Hello world Name");
        FunctionSerializable<User,String> decoratedFunction = Decorators.ofFunction(helloWorldService::returnHelloWorldWithName)
                                                                .decorate();
        String result = decoratedFunction.apply(user);
        assertThat(result).isEqualTo("Hello world Name");


        Map<String,FunctionSerializable> map = new HashMap<>();
        map.put("test",decoratedFunction);
        FunctionSerializable function =  map.get("test");
        System.out.println(function.apply("Name"));
    }
}
