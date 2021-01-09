package io.github.retry.core;

import io.github.retry.core.entity.RetryEntity;
import io.github.retry.core.predicate.PredicateCreator;

import java.util.function.Predicate;

/**
 * @ClassName RetryConfig
 * @Description: 重试配置
 * @author: zhangjie
 * @Date: 2020/12/23 13:53
 **/
public class RetryConfig{

    private static final Predicate<Throwable> DEFAULT_RECORD_FAILURE_PREDICATE = throwable -> true;


    private Class<? extends Throwable>[] retryExceptions = new Class[0];

    private Class<? extends Throwable>[] ignoreExceptions = new Class[0];

    private Predicate<Throwable> exceptionPredicate;

    private RegistryStore registryStore;

    private RetryEntity retryData;


    public static Builder custom(){
        return new Builder();
    }



    public Predicate<Throwable> getExceptionPredicate() {
        return exceptionPredicate;
    }

    public RegistryStore getRegistryStore(){
        return this.registryStore;
    }

    public RetryEntity getRetryData(){
        return this.retryData;
    }


    public static class Builder<T>{

        private Predicate<Throwable> retryOnExceptionPredicate;

        private Class<? extends Throwable>[] retryExceptions = new Class[0];
        private Class<? extends Throwable>[] ignoreExceptions = new Class[0];
        private RegistryStore registryStore;
        private RetryEntity retryData;



        public Builder() {
        }

        /**
         * 将重试配置设置Builder中的属性
         * @param baseConfig
         */
        public Builder(RetryConfig baseConfig){
            this.retryExceptions = baseConfig.retryExceptions;
            this.ignoreExceptions = baseConfig.ignoreExceptions;
            this.registryStore = baseConfig.registryStore;
            this.retryData = baseConfig.retryData;
        }


        /**
         * 根据builder的属性去设置重试配置
         * @return
         */
        public RetryConfig build(){
            RetryConfig config = new RetryConfig();
            config.exceptionPredicate = createExceptionPredicate();
            config.registryStore = registryStore;
            config.retryData = retryData;
            return config;
        }

        public Builder registryStore(RegistryStore registryStore){
            this.registryStore = registryStore;
            return this;
        }

        public Builder registerRetryData(RetryEntity retryData){
            this.retryData = retryData;
            return this;
        }

        private Predicate<Throwable> createExceptionPredicate() {
            return createRetryOnExceptionPredicate()
                    .and(PredicateCreator.createExceptionsPredicate(ignoreExceptions)
                            .orElse(DEFAULT_RECORD_FAILURE_PREDICATE));

        }

        private Predicate<Throwable> createRetryOnExceptionPredicate() {
            return PredicateCreator.createExceptionsPredicate(retryExceptions)
                    .orElseGet(() -> DEFAULT_RECORD_FAILURE_PREDICATE);
        }


    }


}
