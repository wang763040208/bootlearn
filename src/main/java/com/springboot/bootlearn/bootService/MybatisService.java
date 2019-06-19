package com.springboot.bootlearn.bootService;

import net.sf.json.JSONObject;
import com.springboot.bootlearn.bootMoudle.MybatisMoudle;

import java.util.List;

public interface MybatisService {

    public List<MybatisMoudle> select();

    public MybatisMoudle selectByPerKeId(String perKeId);

    MybatisMoudle insertOrUpdateMybatis(String paramsStr, String operation);

    MybatisMoudle deleteById(String perKeId);
}
