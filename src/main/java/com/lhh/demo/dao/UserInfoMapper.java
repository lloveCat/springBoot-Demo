package com.lhh.demo.dao;

import com.lhh.demo.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    public int addUserInfo(UserInfo userInfo) throws Exception;

    public int deleteUserInfo(String openId) throws Exception;

    public int updateUserAllInfo(UserInfo userInfo) throws Exception;

    public int updateUserCitiesInfo(UserInfo userInfo) throws Exception;

    public UserInfo searchUserInfo(String openId) throws Exception;

    public List<UserInfo> searchUserInfoList() throws Exception;
}
