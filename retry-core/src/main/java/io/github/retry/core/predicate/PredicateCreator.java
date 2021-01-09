package io.github.retry.core.predicate;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @ClassName PredicateCreator
 * @Description: 谓词创造者
 * @author: zhangjie
 * @Date: 2020/12/23 14:06
 **/
public class PredicateCreator {

    public static Optional<Predicate<Throwable>> createExceptionsPredicate(Class<? extends Throwable>... recordExceptions){
        return exceptionPredicates(recordExceptions);
    }


    /**
     * 取反异常
     * @param ignoreExceptions
     * @return
     */
    public static Optional<Predicate<Throwable>> createNegatedExceptionsPredicate(Class<? extends Throwable>... ignoreExceptions){
        return exceptionPredicates(ignoreExceptions)
                .map(Predicate::negate);
    }

    /**
     * reduce：聚合函数
     * @param recordExceptions 异常列表
     * @return 异常属于配置列=异常列表范围内任意一个都可以
     */
    private static Optional<Predicate<Throwable>> exceptionPredicates(Class<? extends Throwable>[] recordExceptions){
        return Arrays.stream(recordExceptions)
                .distinct()
                .map(PredicateCreator::makePredicate)
                .reduce(Predicate::or);
    }

    private static Predicate<Throwable> makePredicate(Class<? extends Throwable> exClass){
        return (Throwable e) -> exClass.isAssignableFrom(e.getClass());
    }
}
