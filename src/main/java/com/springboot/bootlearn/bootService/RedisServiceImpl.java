package com.springboot.bootlearn.bootService;

import com.springboot.bootlearn.bootRedis.RedisUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public String setRedis_typeString(String paramsStr) {
        JSONObject paramsJson = new JSONObject();
        try {
            paramsJson = new JSONObject().fromObject(paramsStr);
        }catch(Exception e){
            return "操作失败，JSON字符串转化为JSON对象异常，请检查是否为JSON格式字符串！";
        }
        boolean boolSetRedis_typeString = redisUtil.set(paramsJson.getString("key"), paramsJson.getString("value"));
        if(!boolSetRedis_typeString){
            return "操作失败，将数据插入redis缓存失败，服务器内部异常！";
        }
        //设置缓存失效时间为一分钟
        boolean boolSet_expire = redisUtil.expire(paramsJson.getString("key"),1*60);
        if(!boolSet_expire){
            return "操作失败，设置redis缓存key失效时间失败，服务器内部异常！";
        }
        return "操作成功，将数据插入redis缓存，并设置redis缓存key失效时间成功！";
    }
}
