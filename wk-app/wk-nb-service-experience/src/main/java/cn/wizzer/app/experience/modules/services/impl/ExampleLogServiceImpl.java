package cn.wizzer.app.experience.modules.services.impl;

import cn.wizzer.app.test.modules.services.ExampleLogService;
import cn.wizzer.app.wwdxf.modules.models.Example_log;
import com.alibaba.dubbo.config.annotation.Service;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Times;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.mongo.ZMo;
import org.nutz.mongo.ZMoCo;
import org.nutz.mongo.ZMoDB;
import org.nutz.mongo.ZMoDoc;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zyang
 * @date 2018/4/9.
 */
@IocBean(create = "init")
@Service(interfaceClass = ExampleLogService.class)
public class ExampleLogServiceImpl implements ExampleLogService {
    @Inject
    private ZMoDB zMoDB;

    private ZMoCo zMoCo;

    public void init(){
        this.zMoCo = zMoDB.cc(Example_log.class.getSimpleName(),false);
    }

    @Override
    public void insert(Example_log log) {
        if(log.getId()==null){
            log.setId(R.UU32());
        }
        zMoCo.insert(ZMoDoc.NEW(Json.toJson(log,JsonFormat.tidy())).putv("_id",log.getId()));
    }

    @Override
    public void update(Example_log log) {
        ZMoDoc queryDoc = ZMoDoc.NEW().putv("id",log.getId());
        zMoCo.update(queryDoc,ZMoDoc.NEW(Json.toJson(log,JsonFormat.tidy())));
    }

    @Override
    public List<Example_log> query(Example_log query, Pager pager) {
        if(pager==null){
            pager=new Pager(1,10);
        }
        return zMoCo.query(Example_log.class,ZMoDoc.NEW(Json.toJson(query,JsonFormat.tidy())),pager);
    }

    /**
     * mongo 模糊查询
     * @param query
     * @return
     */
    @Override
    public List<Object> query_many(Example_log query) {
        ZMoDoc queryDoc = ZMoDoc.NEW();
        queryDoc.put("name", ZMoDoc.NEW("$regex", "ceshi"));
//        ZMoDoc timeRange = ZMoDoc.NEW();
//        if (Strings.isNotBlank(startTime)) {
//            try {
//                long startS = Times.ams(startTime) / 1000L;
//                timeRange.put("$gt", startS);
//            } catch (Exception e) {
//            }
//        }
//        if (Strings.isNotBlank(endTime)) {
//            try {
//                long endS = Times.ams(endTime) / 1000L + 3599 * 24;
//                timeRange.put("$lte", endS);
//            } catch (Exception e) {
//            }
//        }
//        queryDoc.put("createAt", timeRange);
        DBCursor cur = zMoCo.find(queryDoc);
        cur.skip(0);
        cur.limit(10);
        List<Object> objects = new ArrayList<>();
        while (cur.hasNext()) {
            DBObject obj = cur.next();
            objects.add(obj);
        }
        return objects;
    }

    @Override
    public void update_one() {
        ZMoDoc queryDoc = ZMoDoc.NEW();
        queryDoc.put("name", "ceshi1");
        DBCursor cur = zMoCo.find(queryDoc);
        List<DBObject> objects = new ArrayList<>();
        while (cur.hasNext()) {
            DBObject obj = cur.next();
            objects.add(obj);
        }
        ZMoDoc doc = zMoCo.findOne(ZMoDoc.NEW("id", "rrmqsrimnkj09ojbefr4i9b5ng"));
        Example_log logstr = ZMo.me().fromDocToObj(doc, Example_log.class);
        logstr.setName("ceshi1");
        logstr.setNote("test");
        logstr.setCrateAt(Times.getTS());
        logstr.setLogType("0");
        System.out.println("logstr::::"+Json.toJson(logstr));
        zMoCo.update(queryDoc, ZMoDoc.NEW(Json.toJson(logstr, JsonFormat.tidy())));

    }
    @Override
    public void remove() {
        ZMoDoc doc = zMoCo.findOne(ZMoDoc.NEW("id", "poauoa6328hd0q243pg39otgod"));
        zMoCo.remove(doc);
    }

    @Override
    public long count(Example_log query) {
        return zMoCo.count(ZMoDoc.NEW(Json.toJson(query,JsonFormat.tidy())));
    }
}
