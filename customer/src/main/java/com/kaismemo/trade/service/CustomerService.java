package com.kaismemo.trade.service;

import com.kaismemo.trade.converter.CustomerConverter;
import com.kaismemo.trade.domain.bo.CustomerBo;
import com.kaismemo.trade.domain.po.CustomerPo;
import com.kaismemo.trade.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * customer 的业务逻辑类
 * <p>
 * 提供 customer 相关接口的业务逻辑实现
 *
 * @author reallth
 * @since 1.0.0
 */
@Service
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerConverter customerConverter;

    public CustomerService(CustomerMapper customerMapper, CustomerConverter customerConverter) {
        this.customerMapper = customerMapper;
        this.customerConverter = customerConverter;
    }

    @Transactional
    public void signup(CustomerBo customerBo) {
        CustomerPo po = customerConverter.toPo(customerBo);
        po.setSignupDate(LocalDate.now());
        customerMapper.save(po);
    }
}
