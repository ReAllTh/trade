package com.kaismemo.trade.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaismemo.trade.converter.CustomerConverter;
import com.kaismemo.trade.domain.bo.CustomerBo;
import com.kaismemo.trade.domain.po.CustomerPo;
import com.kaismemo.trade.domain.req.CustomerQueryReq;
import com.kaismemo.trade.domain.vo.CustomerVo;
import com.kaismemo.trade.entity.PageData;
import com.kaismemo.trade.exception.BusinessException;
import com.kaismemo.trade.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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

    @Transactional(rollbackFor = Exception.class)
    public void signup(CustomerBo customerBo) {
        CustomerPo po = customerConverter.toPo(customerBo);
        po.setSignupDate(LocalDate.now());
        customerMapper.insert(po);
    }

    @Transactional(readOnly = true)
    public PageData<CustomerVo> query(CustomerQueryReq customerQueryReq) {
        LambdaQueryWrapper<CustomerPo> queryWrapper = new LambdaQueryWrapper<>() {{
            likeLeft(customerQueryReq.getName() != null, CustomerPo::getName, customerQueryReq.getName());
            likeLeft(customerQueryReq.getEmail() != null, CustomerPo::getEmail, customerQueryReq.getEmail());
            eq(customerQueryReq.getGender() != null, CustomerPo::getGender, customerQueryReq.getGender());
            likeLeft(customerQueryReq.getCountry() != null, CustomerPo::getCountry, customerQueryReq.getCountry());
            ge(customerQueryReq.getSignupFromDate() != null, CustomerPo::getSignupDate, customerQueryReq.getSignupFromDate());
            le(customerQueryReq.getSignupToDate() != null, CustomerPo::getSignupDate, customerQueryReq.getSignupToDate());
        }};
        Page<CustomerPo> page = new Page<>(customerQueryReq.getPage(), customerQueryReq.getPageSize());
        Page<CustomerPo> pageResult = customerMapper.selectPage(page, queryWrapper);
        List<CustomerVo> resultList = pageResult.getRecords().stream().map(customerConverter::toVo).toList();
        return new PageData<>(pageResult.getTotal(), pageResult.getPages(), pageResult.getCurrent(), pageResult.getSize(), resultList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateByEmail(CustomerBo customerBo) {
        LambdaQueryWrapper<CustomerPo> existWrapper = new LambdaQueryWrapper<>() {{
            eq(CustomerPo::getEmail, customerBo.getEmail());
        }};
        CustomerPo updatedCustomerPo = customerConverter.toPo(customerBo);
        int updatedCnt = customerMapper.update(updatedCustomerPo, existWrapper);
        if (updatedCnt == 0)
            throw new BusinessException(100401, "update failed.");
    }
}
