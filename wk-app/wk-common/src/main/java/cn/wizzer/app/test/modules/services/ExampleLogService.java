package cn.wizzer.app.test.modules.services;

import cn.wizzer.app.wwdxf.modules.models.Example_log;
import org.nutz.dao.pager.Pager;

import java.util.List;


public interface ExampleLogService {
    /**
     * 插入
     */
    void insert(Example_log log);

    /**
     * 更新
     */
    void update(Example_log log);

    /**
     * 查询
     * @param query
     * @param pager
     * @return
     */
    List<Example_log> query(Example_log query, Pager pager);

    /**
     * 查询
     * @param query
     * @return
     */
    List<Object> query_many(Example_log query);

    /**
     * 查询
     * @return
     */
    void update_one();

    /**
     * 查询
     * @return
     */
    void remove();

    /**
     * 统计
     * @param query
     * @return
     */
    long count(Example_log query);
}
