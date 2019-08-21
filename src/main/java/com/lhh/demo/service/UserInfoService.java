package com.lhh.demo.service;

import com.lhh.demo.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {

    public UserInfo addUserInfo(UserInfo userInfo) throws Exception;

    public int deleteUserInfo(String openId) throws Exception;

    public UserInfo updateUserInfo(UserInfo userInfo) throws Exception;

    public UserInfo updateUserCitiesInfo(UserInfo userInfo) throws  Exception;

    public UserInfo searchUserInfo(String openId) throws Exception;

    public List<UserInfo> searchUserList() throws Exception;

}
