package io.github.retry.core.internal;

import io.github.retry.core.RegistryStore;
import io.github.retry.core.Retry;
import io.github.retry.core.RetryConfig;
import io.github.retry.core.entity.RetryEntity;

import java.util.function.Predicate;

/**
 * @ClassName RetryImpl
 * @Description: 重试实现类
 * @author: zhangjie
 * @Date: 2020/12/23 11:49
 **/
public class RetryImpl<T> implements Retry {

    private final Predicate<Throwable> exceptionPredicate;

    private RegistryStore registryStore;

    private RetryEntity retryData;


    public RetryImpl(RetryConfig config){
        this.exceptionPredicate = config.getExceptionPredicate();
        this.registryStore = config.getRegistryStore();
        this.retryData = config.getRetryData();
    }


    @Override
    public Context context() {
        return new ContextImpl();
    }

    public final class ContextImpl implements Retry.Context<T>{

        private ContextImpl(){
        }

        @Override
        public void onError(Exception exception){
                retryData.setErrorMessage(exception.getMessage());
                registryStore.add(retryData);
        }



    }
}
