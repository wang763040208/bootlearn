package com.springboot.bootlearn.bootService;

import com.alibaba.fastjson.JSONObject;
import com.springboot.bootlearn.bootMoudle.AccountMoudle;

import java.util.List;

public interface AccountService {
    //JDBC
    List<AccountMoudle> select_account_all(String parJson);
}
