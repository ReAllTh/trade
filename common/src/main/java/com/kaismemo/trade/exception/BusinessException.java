package com.kaismemo.trade.exception;

import lombok.Getter;

/**
 * 业务异常类
 *
 * @author reallth
 * @since 1.0.0
 */
@Getter
public class BusinessException extends RuntimeException {
    private final int code;
    private final String message;

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
