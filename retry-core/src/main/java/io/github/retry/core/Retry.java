package io.github.retry.core;

import io.github.retry.core.entity.RetryEntity;
import io.github.retry.core.internal.RetryImpl;

/**
 * @author zhangjie
 */
public interface Retry {


    /**
     * 创建默认配置的重试实例
     * @param registryStore 重试数据存储方式
     * @param retryData 重试数据
     * @return
     */
    static Retry ofRegistryStoreAndData(RegistryStore registryStore, RetryEntity retryData){
        RetryConfig retryConfig = RetryConfig.custom()
                                                .registryStore(registryStore)
                                                .registerRetryData(retryData)
                                                .build();
        return of(retryConfig);
    }

    /**
     * 根据重试配置RetryConfig创建重试实例
     * @param retryConfig
     * @return
     */
    static Retry of(RetryConfig retryConfig){
        return new RetryImpl(retryConfig);
    }

    /**
     * 创建重试的Function(带参数)
     * @param retry 重试设置
     * @param function 方法
     * @param <T> 参数
     * @param <R> 返回结果
     * @return
     */
    static <T,R> FunctionSerializable<T,R> decorateFunction(Retry retry, FunctionSerializable<T, R> function){
        return (T t) -> {
            Retry.Context<T> context = retry.context();
            try{
                R result = function.apply(t);
                return result;
            }catch (Exception exception){
                context.onError(exception);
                throw exception;
            }
        };
    }


    /**
     * 创建重试上下文
     * @param <T>
     * @return
     */
    <T> Retry.Context<T> context();


    /**
     * 重试上下文，对错误、正确做操作
     * @param <T>
     */
    interface Context<T> {
        /**
         * 处理检查异常
         * @param exception 异常
         * @throws Exception
         */
        void onError(Exception exception);
    }


}
