package com.lhh.demo.service.serviceImpl;

import com.lhh.demo.dao.LoginRecordMapper;
import com.lhh.demo.pojo.LoginRecord;
import com.lhh.demo.service.LoginRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginRecordServiceImpl implements LoginRecordService {

    @Resource
    private LoginRecordMapper loginRecordMapper;

    @Override
    public int addLoginRecord(LoginRecord loginRecord) throws Exception{
        return loginRecordMapper.add(loginRecord);
    }

    @Override
    public List<LoginRecord> searchLoginRecordList() throws Exception {
        return loginRecordMapper.findAll();
    }

    @Override
    public List<LoginRecord> searchLoginRecordForOnePerson(String userId) throws Exception{
        return loginRecordMapper.findForOnePerson(userId);
    }
}
