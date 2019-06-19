package com.springboot.bootlearn.bootService;

import com.alibaba.fastjson.JSONObject;
import com.springboot.bootlearn.bootDao.AccountDao;
import com.springboot.bootlearn.bootMoudle.AccountMoudle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;
    //JDBC
    @Override
    public List<AccountMoudle> select_account_all(String parJson) {

        return accountDao.select_accountById(parJson);

    }
}

