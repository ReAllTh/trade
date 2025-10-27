package com.kaismemo.trade.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 持久化实体类的基类
 * <p>
 * 提供了持久化对象的公共管理字段的映射，所有 po 都需要继承这个基类
 *
 * @author reallth
 * @since 1.0.0
 */
@Data
public class BasePo {
    @TableId(type = IdType.ASSIGN_ID)
    private long id;
    private long version;
    private boolean isDeleted;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deletedAt;
}
