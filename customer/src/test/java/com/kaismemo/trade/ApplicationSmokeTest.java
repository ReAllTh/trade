package com.kaismemo.trade;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 应用程序的冒烟测试类
 * <p>
 * 在进行其他测试之前，简单测试应用能否成功启动
 *
 * @author reallth
 * @since 1.0.0
 */
@SpringBootTest
class ApplicationSmokeTest {
    /**
     * 测试应用能否成功启动
     * <p>
     * 测试的真正工作由 @SpringBootTest 注解完成：<br>
     * 1. 它会尝试查找 @SpringBootApplication 注解（即 Application.class）。<br>
     * 2. 它会启动并加载完整的 Spring 应用上下文。<br>
     * 3. 如果在启动过程中（例如，自动配置、组件扫描、依赖注入等）出现任何错误，这个测试就会失败。<br>
     * 4. 如果测试方法成功运行（即使是空的），就证明应用上下文已成功加载。
     */
    @Test
    public void contextLoads() {
    }
}
