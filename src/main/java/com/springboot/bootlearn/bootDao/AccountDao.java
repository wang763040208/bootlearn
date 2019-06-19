package com.springboot.bootlearn.bootDao;

import com.springboot.bootlearn.bootMoudle.AccountMoudle;

import java.util.List;

public interface AccountDao {
    //JDBC
    List<AccountMoudle> select_accountById(String parId);
}
