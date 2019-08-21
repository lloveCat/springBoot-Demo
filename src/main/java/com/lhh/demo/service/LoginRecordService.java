package com.lhh.demo.service;

import com.lhh.demo.pojo.LoginRecord;

import java.util.List;

public interface LoginRecordService {

    public int addLoginRecord(LoginRecord loginRecord)throws Exception;

    public List<LoginRecord> searchLoginRecordList() throws Exception;

    public List<LoginRecord> searchLoginRecordForOnePerson(String userId) throws Exception;
}
