package com.kaismemo.trade.domain.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * customer 的更新请求实体类
 * <p>
 * 此类对象承载了请求数据，需要做请求参数校验
 *
 * @author reallth
 * @since 1.0.0
 */
@Data
public class CustomerUpdateReq {
    @Size(min = 2, max = 32, message = "the length of name should be between 2 and 32")
    private String name;

    @NotBlank(message = "email should not be null")
    @Email(message = "invalid email")
    private String email;

    @Pattern(regexp = "^(Male|Female)$", message = "gender should be 'Male' or 'Female'")
    private String gender;

    private String country;
}