package cn.wizzer.app.wwdxf.modules.models;

import java.io.Serializable;

/**
 * example_log
 * Created by wizzer on 2018/3/21.
 */
public class Example_log implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String name;

    private String note;

    private Long crateAt;
    // 日志类型
    private String logType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCrateAt() {
        return crateAt;
    }

    public void setCrateAt(Long crateAt) {
        this.crateAt = crateAt;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }
}
