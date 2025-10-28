package com.kaismemo.trade.domain;

import com.baomidou.mybatisplus.annotation.*;
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
    private Long id;

    @Version
    private Long version;

    @TableLogic
    @TableField(select = false)
    private Boolean isDeleted;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime deletedAt;
}
