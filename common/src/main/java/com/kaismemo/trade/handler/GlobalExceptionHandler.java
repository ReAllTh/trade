package com.kaismemo.trade.handler;

import com.kaismemo.trade.entity.Response;
import com.kaismemo.trade.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

/**
 * 全局异常处理器
 * <p>
 * {@code @RestControllerAdvice} 注解是 {@code @ControllerAdvice} 和 {@code @ResponseBody} 组成的复合注解；
 * {@code @ControllerAdvice} 将这个类标注为一个 AdviceBean 注册到容器中，而 {@code @ResponseBody} 将返回值转换为 JSON 的形式。
 * <p>
 * {@link DispatcherServlet} 在异常发生时，委托 {@link ExceptionHandlerExceptionResolver} 处理异常。
 * {@link ExceptionHandlerExceptionResolver} 在容器启动时作为一个普通单例 Bean 被加载到容器中，
 * 在初始化阶段后期，通过 {@code afterPropertiesSet()} 扫描容器中所有 Bean，
 * 把其中的 AdviceBean 和 {@link ExceptionHandlerMethodResolver} 用 {@link LinkedHashMap} 缓存起来，
 * 而创建 {@link ExceptionHandlerMethodResolver} 时会把这个 AdviceBean 声明的 {@link Exception} 和对应的 {@link Method} 缓存起来，
 * 这样，{@link ExceptionHandlerExceptionResolver} 就可以通过遍历内部的 {@link LinkedHashMap}，找到对应的处理方法，并调用执行。
 *
 * @author reallth
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Response<?> handleBusinessException(BusinessException e) {
        return new Response<>(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public Response<?> handleGeneralException(Exception e) {
        log.error(e.getLocalizedMessage(), e);
        return new Response<>(500, "system error: " + e.getLocalizedMessage(), null);
    }
}
