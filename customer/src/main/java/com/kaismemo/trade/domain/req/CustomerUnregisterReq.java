package com.kaismemo.trade.domain.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * customer 的注销请求实体类
 * <p>
 * 此类对象承载了请求数据，需要做请求参数校验
 *
 * @author reallth
 * @since 1.0.0
 */
@Data
public class CustomerUnregisterReq {
    @NotBlank(message = "email should not be null")
    @Email(message = "invalid email")
    private String email;
}
