package com.kaismemo.trade.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * mapstruct converter 测试配置类
 * <p>
 * mapstruct converter 在测试时直接 @SpyBean 没用，因为 @WebMvcTest 默认只会加载 Web 层相关的组件，
 * 而直接 @Import(XXXConverterImpl.class) 又使得测试代码与框架实现向耦合，未来框架实现的类名变了名字会导致相关测试类全部无法工作
 * 所以此处用测试配置类加载 {@link com.kaismemo.trade.converter} 包下的 converter
 *
 * @author reallth
 * @since 1.0.0
 */
@TestConfiguration
@ComponentScan(basePackages = "com.kaismemo.trade.converter")
public class ConverterTestConfig {
}
