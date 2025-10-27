package com.kaismemo.trade.entity;

import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * 统一的响应体
 *
 * @author reallth
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Response<T> ok(T data) {
        return new Response<>(200, "success", data);
    }

    public static Response<?> error(int code, String message) {
        return new Response<>(code, message, null);
    }
}
