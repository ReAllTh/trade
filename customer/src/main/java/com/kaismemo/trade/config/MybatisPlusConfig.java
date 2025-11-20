package com.kaismemo.trade.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
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
     * 插件注册
     *
     * @return {@link MybatisPlusInterceptor}
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 乐观锁插件，拦截 update 语句，在更新条件中限定版本
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 分页插件，连接 select 语句，增加 limit {offset},{size} 条件，注意非覆盖索引的情况下，会有深分页问题
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
