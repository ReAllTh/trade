package com.kaismemo.trade.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kaismemo.trade.domain.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * customer 的持久化实体类
 * <p>
 * 此类对象用来做数据库字段映射，并作为数据载体被持久化到数据库中
 *
 * @author reallth
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_customers")
public class CustomerPo extends BasePo {
    private String name;
    private String email;
    private String gender;
    private LocalDate signupDate;
    private String country;
}
