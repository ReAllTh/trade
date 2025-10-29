package com.kaismemo.trade.converter;

import com.kaismemo.trade.domain.bo.CustomerBo;
import com.kaismemo.trade.domain.po.CustomerPo;
import com.kaismemo.trade.domain.req.CustomerSignupReq;
import com.kaismemo.trade.domain.vo.CustomerVo;
import org.mapstruct.Mapper;

/**
 * customer pojo 转换器
 * <p>
 * 提供 customer 各层 pojo 之间的转换能力
 *
 * @author reallth
 * @since 1.0.0
 */
@Mapper(componentModel = "spring")
public interface CustomerConverter {
    CustomerBo toBo(CustomerSignupReq customerSignupReq);
    CustomerPo toPo(CustomerBo customerBo);
    CustomerVo toVo(CustomerPo customerPo);
}
