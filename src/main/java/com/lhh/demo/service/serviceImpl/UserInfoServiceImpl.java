package com.lhh.demo.service.serviceImpl;

import com.lhh.demo.dao.UserInfoMapper;
import com.lhh.demo.pojo.UserInfo;
import com.lhh.demo.service.UserInfoService;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {           //这些方法返回UserInfo对象主要是用于保存UserInfo入缓存

    @Resource
    private UserInfoMapper userInfoMapper;
    private BeanFactory myBeanFactory;

    public void setMyBeanFactory(BeanFactory beanFactory) {
        myBeanFactory = beanFactory;
    }

    @Cacheable(value = "user", key = "#userInfo.openId", unless = "#result eq null")       //添加一次，后续不再添加openId相同的记录，并将本次记录保存入缓存
    @Override
    public UserInfo addUserInfo(UserInfo userInfo) throws Exception{
        return userInfo;
    }

    @CacheEvict(value = "user", key = "#openId", condition = "#result gt 0")            //删除openId对应的数据库记录和缓存记录
    @Override
    public int deleteUserInfo(String openId) throws Exception{
        return userInfoMapper.deleteUserInfo(openId);
    }

    @CachePut(value = "user", key = "#userInfo.openId", unless = "#result gt 0")        //执行后重新将openId对应的记录放入，主要是更新缓存
    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) throws Exception{
        return userInfo;
    }
    @CachePut(value = "user", key = "#userInfo.openId", unless = "#result gt 0")
    @Override
    public UserInfo updateUserCitiesInfo(UserInfo userInfo) throws  Exception{          //执行后重新将openId对应的记录放入，主要是更新缓存
        return userInfo;
    }

    @Override
    @Cacheable(value = "user", key = "#openId", unless = "#result eq null")             //将搜索出来的openId对应的记录放入缓存，下次访问直接取出
    public UserInfo searchUserInfo(String openId) throws Exception{
        System.out.println("数据库搜索");
        return userInfoMapper.searchUserInfo(openId);
    }

    @CachePut(value = "user", key = "allUser", unless = "#result eq null")
    @Override
    public List<UserInfo> searchUserList() throws Exception{
        return userInfoMapper.searchUserInfoList();
    }
}
