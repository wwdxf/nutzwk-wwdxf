package cn.wizzer.app.wwdxf.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * 测试is 字段
 */
@Table("test_is_info")
public class Test_is_info extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Name
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String id;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
