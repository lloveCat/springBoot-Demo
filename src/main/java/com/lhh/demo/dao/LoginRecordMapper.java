package com.lhh.demo.dao;

import com.lhh.demo.pojo.LoginRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginRecordMapper {

    public int add(LoginRecord loginRecord) throws Exception;

    public List<LoginRecord> findAll() throws Exception;

    public List<LoginRecord> findForOnePerson(String userId) throws Exception;
}
