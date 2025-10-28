package com.kaismemo.trade.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus 配置
 *
 * @author reallth
 * @since 1.0.0
 */
@Configuration
@MapperScan("com.kaismemo.trade.mapper")
public class MybatisPlusConfig {
    /**
     * 乐观锁插件
     * <p>
     * 可以识别 PO 上标注了 @Version 的字段，将之作为乐观锁版本号，在访问数据库时执行乐观锁逻辑（更改时添加版本约束）
     *
     * @return {@link MybatisPlusInterceptor}
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
