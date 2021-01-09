package io.github.retry.core.entity;

import java.io.Serializable;

/**
 * @ClassName RetryEntity
 * @Description: 重试实体类
 * @author: zhangjie
 * @Date: 2020/12/23 15:01
 **/
public class RetryEntity implements Serializable {

    /** 业务ID */
    private String businessId;
    /** 重试服务名 */
    private String serviceName;
    /** 重试方法名 */
    private String serviceMethod;
    /** 已序列化的重试方法参数 */
    private String serviceMethodParam;

    private String errorMessage;

    public RetryEntity() {
    }

    public RetryEntity(String businessId, String serviceName, String serviceMethod, String serviceMethodParam, String errorMessage) {
        this.businessId = businessId;
        this.serviceName = serviceName;
        this.serviceMethod = serviceMethod;
        this.serviceMethodParam = serviceMethodParam;
        this.errorMessage = errorMessage;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public String getServiceMethodParam() {
        return serviceMethodParam;
    }

    public void setServiceMethodParam(String serviceMethodParam) {
        this.serviceMethodParam = serviceMethodParam;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
