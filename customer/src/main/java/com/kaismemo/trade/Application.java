package com.kaismemo.trade;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring Boot 启动类
 * <p>
 * 启动流程如下：<br>
 * 1. 创建启动计时器和用于管理早期对象的 {@link DefaultBootstrapContext}，
 * 然后通知所有的 {@link SpringApplicationRunListener}：应用已经开始启动<br>
 * 2. 加载运行参数、应用环境，然后打印一个 Banner，
 * 然后根据应用类型创建一个 {@link ConfigurableApplicationContext} 的应用上下文<br>
 * 3. 利用引导上下文、运行参数、应用环境等，初始化应用上下文<br>
 * 4. 刷新应用上下文，包括：
 *    <ol>
 *        <li>初始化 {@link BeanFactory}</li>
 *        <li>执行 {@link BeanFactoryPostProcessor}</li>
 *        <li>注册 {@link BeanPostProcessor}</li>
 *        <li>创建并初始化所有单例 Bean</li>
 *        <li>然后发布上下文刷新事件</li>
 *    </ol>
 * 5. 启动内嵌的 Web 服务器<br>
 * 6. 通知所有 {@link SpringApplicationRunListener}：应用已经启动完成<br>
 * 8. 查找并执行应用上下文中的所有 {@link CommandLineRunner} 和 {@link ApplicationRunner} 接口的 Bean
 *
 * @author reallth
 * @since 1.0.0
 */
@SpringBootApplication
class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
