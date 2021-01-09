package io.github.retry.core.decorators;

import io.github.retry.core.FunctionSerializable;
import io.github.retry.core.Retry;

/**
 * Decorator构建器，可用于将多个装饰器应用于Supplier、Callable、Function、Runnable、CompletionStage或Consumer
 * 装饰器按构建器链的顺序应用
 * @author zhangjie
 */
public interface Decorators {

    /**
     * 装饰Function
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    static <T,R> DecorateFunction<T,R> ofFunction(FunctionSerializable<T,R> function){
        return new DecorateFunction(function);
    }

    class DecorateFunction<T,R>{
        private FunctionSerializable<T,R> function;

        private DecorateFunction(FunctionSerializable<T,R> function){
            this.function = function;
        }

        public DecorateFunction<T,R> withRetry(Retry retryContext){
            function = Retry.decorateFunction(retryContext,function);
            return this;
        }

        public FunctionSerializable<T, R> decorate() {
            return function;
        }
    }
}
