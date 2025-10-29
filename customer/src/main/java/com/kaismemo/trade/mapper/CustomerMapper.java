package com.kaismemo.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaismemo.trade.domain.po.CustomerPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * customer 的持久化层
 * <p>
 * 提供了将持久化对象持久化到数据库中的能力
 *
 * @author reallth
 * @since 1.0.0
 */
@Mapper
public interface CustomerMapper extends BaseMapper<CustomerPo> {
}
