package com.springboot.bootlearn.bootController;
import com.springboot.bootlearn.bootAsyncService.BootAsyncService;
import com.springboot.bootlearn.bootAsyncService.BootAsyncServiceImpl;
import com.springboot.bootlearn.bootRedis.RedisUtil;
import com.springboot.bootlearn.bootService.RedisService;
import com.springboot.bootlearn.bootDao.JpaDao;
import com.springboot.bootlearn.bootMoudle.AccountMoudle;
import com.springboot.bootlearn.bootMoudle.MybatisMoudle;
import com.springboot.bootlearn.bootService.AccountService;
import com.springboot.bootlearn.bootService.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class LearnController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private MybatisService mybatisService;
    @Autowired
    private JpaDao jpaDao;
    @Autowired
    private RedisService redisService;
    @Autowired
    private BootAsyncService bootAsyncService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * author:王泓霏
     * date：2019-3-27
     * schedule：JDBC
     * @param operation
     * @param parStr
     * @return
     */
    @RequestMapping(value = "jdbc/operationTable/{operation}", method = RequestMethod.GET)
    public List<AccountMoudle> jdbc_select_account_all(@PathVariable(value = "operation", required = false) String operation,
                                                  @RequestParam(value = "parameter", required = false)String parStr){

        if(null == parStr){
            parStr = "IS NOT NULL";
        }
        if("select".equals(operation)){
            List<AccountMoudle> accountList = accountService.select_account_all(parStr);
            if(accountList.size() > 0){
                return accountList;
            }else {
                accountList.get(0);
            }
        }else if("delete".equals(operation)){

        }else if("insert".equals(operation)){

        }else if("update".equals(operation)){

        }
        return null;
    }

    /**
     * author:王泓霏
     * date：2019-3-27
     * schedule：JPA
     * @param
     * @param
     * @return
     * remark:这块代码有毒，JPA循环添加，将new对象的操作放入for循环中，非建表等特殊情况不要解开注释
     */
    @RequestMapping(value = "jpa/operationTable/{operation}", method = RequestMethod.GET)
    public String jpa_select_account_all(@PathVariable(value = "operation", required = false)String operation){
        /*if(null == operation){
            return "operation 为 null";
        }
        if("insert".equals(operation)){
            List<String> returnList = new ArrayList<String>(5);
            for (int i = 0; i < 5;i++) {
                JpaDataMoudle jpaDataMoudle = new JpaDataMoudle();
                jpaDataMoudle.setQueueName("tyxb_queue_for" + i);
                jpaDataMoudle.setQueueStatus(1 + i);
                jpaDataMoudle.setRedisName("kingCard_redis_for" + i);
                jpaDataMoudle.setRedisStatus(1 + i);
                jpaDataMoudle = jpaDao.saveAndFlush(jpaDataMoudle);
                returnList.add(jpaDataMoudle.getId());
            }
        return returnList.toString();
        }*/
        return "格式错误";
    }

    /**
     * author:王泓霏
     * date：2019-3-28
     * schedule：mybatis
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "myBatis/operationTable/{operation}", method = RequestMethod.POST)
    public List<MybatisMoudle> mybatisSql(@PathVariable(value = "operation", required = false)String operation,
                                          @RequestParam(value = "id", required = false) String perKeId, MybatisMoudle mybatisMoudle,
                                          @RequestParam(value = "paramsStr", required = false)String paramsStr){
        List<MybatisMoudle> mybatisMoudleList = new ArrayList<MybatisMoudle>();
        if("select".equals(operation)){
            if(perKeId == null || "".equals(perKeId)){
                mybatisMoudleList = mybatisService.select();
                return mybatisMoudleList;
            }
            mybatisMoudle = mybatisService.selectByPerKeId(perKeId);
            mybatisMoudleList.add(mybatisMoudle);
            return mybatisMoudleList;
        }else if("insert".equals(operation) || "update".equals(operation)){
            if("".equals(paramsStr) || paramsStr == null){
                mybatisMoudle.setId("操作失败，paramsStr为空");
                mybatisMoudleList.add(mybatisMoudle);
                return mybatisMoudleList;
            }
            mybatisMoudle = mybatisService.insertOrUpdateMybatis(paramsStr, operation);
            mybatisMoudle = clearMybatisMoudle(mybatisMoudle);
            mybatisMoudleList.add(mybatisMoudle);
            return mybatisMoudleList;
        }else if("delete".equals(operation)){
            if("".equals(perKeId) || perKeId == null){
                mybatisMoudle.setId("添加失败，id(唯一标识)为空");
                mybatisMoudleList.add(mybatisMoudle);
                return mybatisMoudleList;
            }
            mybatisMoudle = mybatisService.deleteById(perKeId);
            mybatisMoudleList.add(mybatisMoudle);
            return mybatisMoudleList;
        }
        mybatisMoudle.setId("操作错误");
        mybatisMoudleList.add(mybatisMoudle);
        return mybatisMoudleList;
    }

    /**
     * author:王泓霏
     * date：2019-3-28
     * schedule：nosql-redis
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "redis/operationRedis/{operation}", method = RequestMethod.POST)
    public String operationRedis(@PathVariable(value = "operation", required = false)String operation,
                           @RequestParam(value = "paramsStr", required = false)String paramsStr){
        if("set".equals(operation)){
            String strReturn = redisService.setRedis_typeString(paramsStr);
            return strReturn;
        }
        return "操作错误！";
    }

    /**
     * author:王泓霏
     * date：2019-3-28
     * schedule：nosql-redis
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "redis/putBigDataForRedis", method = RequestMethod.POST)
    public String putBigDataForRedis(){
        bootAsyncService.putBigDataForRedis();
        //redisUtil.clear();
        return "接口调用成功，数据正在同步，请查看Redis核对数据！";
    }

    /**
     * author 王泓霏
     * date 2019-04-04
     * 清空返回moudle类中的无必要返回属性内的值
     * @param mybatisMoudle
     * @return
     */
    private MybatisMoudle clearMybatisMoudle(MybatisMoudle mybatisMoudle){
        mybatisMoudle.setQueue_status(null);
        mybatisMoudle.setQueue_name(null);
        mybatisMoudle.setRedis_name(null);
        mybatisMoudle.setRedis_status(null);
        return mybatisMoudle;
    }

}











