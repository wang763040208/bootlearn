package com.springboot.bootlearn.bootDao;

import com.springboot.bootlearn.bootMoudle.JpaDataMoudle;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Transactional
public interface JpaDao extends JpaRepository<JpaDataMoudle,String> {

}
