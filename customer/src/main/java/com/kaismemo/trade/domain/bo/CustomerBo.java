package com.kaismemo.trade.domain.bo;

import lombok.Data;

/**
 * customer 的业务实体类
 * <p>
 * 此类对象用来承载会造成状态改变的信息
 *
 * @author reallth
 * @since 1.0.0
 */
@Data
public class CustomerBo {
    private String name;
    private String email;
    private String gender;
    private String country;
}
