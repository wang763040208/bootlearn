package com.springboot.bootlearn.bootService;

//import com.alibaba.fastjson.JSONObject;
import com.springboot.bootlearn.bootDao.MybatisDao;
import com.springboot.bootlearn.bootMoudle.MybatisMoudle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.json.JSONObject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class MybatisServiceImpl implements MybatisService{

    @Autowired
    private MybatisDao mybatisDao;
    @Override
    public List<MybatisMoudle> select() {
        List<MybatisMoudle> mybatisMoudleList = mybatisDao.select();
        return mybatisMoudleList;
    }

    @Override
    public MybatisMoudle selectByPerKeId(String perKeId) {
        MybatisMoudle mybatisMoudle =  mybatisDao.selectByPerKeId(perKeId);
        return mybatisMoudle;
    }

    /**
     * 有个小BUG记住了，添加时重复的ID异常，这里没有抓捕，代码先这样，记得寻找方法并改正。
     * @param
     * @return
     * Transactional事务控制注解
     */
    @Override
    @Transactional
    public MybatisMoudle insertOrUpdateMybatis(String paramsStr, String operation) {
        MybatisMoudle mybatisMoudle = new MybatisMoudle();
        JSONObject paramsJson = new JSONObject();
        try {
            paramsJson = new JSONObject().fromObject(paramsStr);
        }catch(Exception e){
            mybatisMoudle.setId("操作失败，JSON字符串转化为JSON对象异常，请检查是否为JSON格式字符串");
            return mybatisMoudle;
        }
        try {
            mybatisMoudle = (MybatisMoudle)JSONObject.toBean(paramsJson,MybatisMoudle.class);
        }catch (Exception e){
            mybatisMoudle.setId("操作失败，JSON对象转化java对象失败，请检查JSON的key是否正确！");
            return mybatisMoudle;
        }
        //添加条数
        int insertMybatisSum = 0;
        //修改条数
        int updateMybaitsSum = 0;
        //测试@Transactional注解是否有开启事务管理的功能------2019-4-5日测试成功
        //int insertMybatisTran = 0;
        if("insert" .equals(operation)) {
            insertMybatisSum = mybatisDao.insertMybatis(mybatisMoudle);
            //测试业务控制,模拟故障  于2019年4月1日模拟完成测试OK
            /*mybatisMoudle.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            mybatisMoudle.setQueue_name(UUID.randomUUID().toString().replaceAll("-", ""));
            mybatisMoudle.setQueue_status("4");
            mybatisMoudle.setRedis_name(UUID.randomUUID().toString().replaceAll("-", ""));
            mybatisMoudle.setRedis_status("6");
            insertMybatisTran = mybatisDao.insertMybatis(mybatisMoudle);*/
            if(insertMybatisSum == 0/* || insertMybatisTran == 0*/){
                mybatisMoudle.setId("添加失败，请检查ID（主键）是否已存在于数据库，或服务器内部异常。");
                return mybatisMoudle;
            }
            mybatisMoudle.setId("添加成功！");
            return mybatisMoudle;
        }else if("update".equals(operation)){
            updateMybaitsSum = mybatisDao.updateMybatis(mybatisMoudle);
            if(updateMybaitsSum == 0){
                mybatisMoudle.setId("修改失败，请检查ID（主键）是否已存在于数据库，或服务器内部异常。");
                return mybatisMoudle;
            }
            mybatisMoudle.setId("修改成功！");
            return mybatisMoudle;
        }
        mybatisMoudle.setId("操作失败,请检查operation参数是否正确，或服务器内部异常");
        return mybatisMoudle;
    }

    @Override
    @Transactional
    public MybatisMoudle deleteById(String perKeId) {
        MybatisMoudle mybatisMoudle = new MybatisMoudle();
        int deleteMybatisSum = mybatisDao.deleteById(perKeId);
        if(deleteMybatisSum == 0){
            mybatisMoudle.setId("删除失败，数据库中并无需要删除的数据，或服务器内部异常");
            return mybatisMoudle;
        }
        mybatisMoudle.setId("删除成功！");
        return mybatisMoudle;
    }
}
