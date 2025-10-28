package com.kaismemo.trade.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * mybatis plus 的元对象处理器
 * <p>
 * 给 PO 上标注了 @TableField(fill = FieldFill.?) 的字段填充自定义值
 *
 * @author reallth
 * @since 1.0.0
 */
@Component
public class BasePoMetaObjectHandler implements MetaObjectHandler {

    /**
     * 在插入时，给对应字段填充自定义值
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "isDeleted", Boolean.class, false);
    }

    /**
     * 在更新时，给对应字段填充自定义值
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        this.strictUpdateFill(metaObject, "updatedAt", LocalDateTime.class, now);
        Object isDeletedVal;
        if ((isDeletedVal = this.getFieldValByName("isDeleted", metaObject)) != null
                && isDeletedVal.equals(true))
            this.strictUpdateFill(metaObject, "deletedAt", LocalDateTime.class, now);
    }
}
