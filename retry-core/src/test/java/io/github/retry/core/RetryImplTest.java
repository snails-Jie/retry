package io.github.retry.core;

import io.github.retry.core.decorators.Decorators;
import io.github.retry.core.entity.RetryEntity;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @ClassName RetryImplTest
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/12/23 22:15
 **/
public class RetryImplTest {
    private HelloWorldService helloWorldService;
    private User user;
    private RegistryStore registryStore;

    @Before
    public void setUp(){
        registryStore = mock(RegistryStore.class);
        helloWorldService = new HelloWorldServiceImpl();
        user = new User("zhangjie",24);

    }

    @Test
    public void testParamRetry(){
        RetryEntity retryData = new RetryEntity("221312","relayBuyService","pushOrder","ssss","空指针异常");

        FunctionSerializable<User, String> function = Decorators
                                                    .ofFunction(helloWorldService::returnHelloWorldWithName)
                                                    .withRetry(Retry.ofRegistryStoreAndData(registryStore,retryData))
                                                    .decorate();
        String result = function.apply(user);
        assertThat(result).isEqualTo("Hello world Name");
    }

}
