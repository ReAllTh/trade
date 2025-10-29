package com.kaismemo.trade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 分页数据包装类
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
public class PageData<T> {
    private Long total;
    private Long pages;
    private Long current;
    private Long pageSize;
    private List<T> data;
}
