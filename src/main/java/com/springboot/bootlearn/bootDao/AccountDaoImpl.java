package com.springboot.bootlearn.bootDao;

import com.springboot.bootlearn.bootMoudle.AccountMoudle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //JDBC
    @Override
    public List<AccountMoudle> select_accountById(String parId) {
        if("IS NOT NULL" .equals(parId)){
            List<AccountMoudle> accountMoudlesList = jdbcTemplate.query("SELECT * FROM `account`",new Object[]{}, new BeanPropertyRowMapper(AccountMoudle.class));
            if(accountMoudlesList != null && accountMoudlesList.size() > 0){
                return accountMoudlesList;
            }else{
                return null;
            }
        }
        List<AccountMoudle> accountMoudlesList = jdbcTemplate.query("select * from account where id = ?", new Object[]{parId}, new BeanPropertyRowMapper(AccountMoudle.class));
        if(accountMoudlesList != null && accountMoudlesList.size() > 0){
            return accountMoudlesList;
        }else{
            return null;
        }
    }



}
