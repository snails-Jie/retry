package io.github.retry.core;

import io.github.retry.core.entity.RetryEntity;

import java.util.List;

/**
 * 数据接口
 * @author zhangjie
 */
public interface RegistryStore {
    /**
     * 上传数据
     * @param retryData 重试数据
     * @return
     */
    void add(RetryEntity retryData);

    /**
     * 查询待重试的所有数据
     * @return
     */
    List<RetryEntity> findAll();


}
