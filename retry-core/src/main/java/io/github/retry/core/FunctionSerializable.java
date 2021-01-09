package io.github.retry.core;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * 可序列化的函数式接口
 * @author zhangjie
 */
@FunctionalInterface
public interface FunctionSerializable<T,R> extends Serializable {

    R apply(T var1);

    /**
     * 这个方法返回的SerializedLambda是重点
     * @return
     * @throws Exception
     */
    default SerializedLambda getSerializedLambda() throws Exception {
        //从function取出序列化方法
        Method write = this.getClass().getDeclaredMethod("writeReplace");
        write.setAccessible(true);
        return (SerializedLambda) write.invoke(this);
    }


}
