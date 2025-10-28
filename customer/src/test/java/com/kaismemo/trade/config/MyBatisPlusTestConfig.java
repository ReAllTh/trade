package com.kaismemo.trade.config;

import com.kaismemo.trade.handler.BasePoMetaObjectHandler;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

/**
 * mybatis plus 测试配置类
 * <p>
 * 使用 @MyBatisPlusTest 的时候，启动的上下文中，只包含有限的 Bean，
 * 需要用这种方式把额外需要的 Bean 注入上下文中
 *
 * @author reallth
 * @since 1.0.0
 */
@TestConfiguration
@Import({MybatisPlusConfig.class, BasePoMetaObjectHandler.class})
public class MyBatisPlusTestConfig {
}
