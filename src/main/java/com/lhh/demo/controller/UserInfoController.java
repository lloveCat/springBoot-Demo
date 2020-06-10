package com.lhh.demo.controller;

import com.lhh.demo.ExceptionRe.NullCodeException;
import com.lhh.demo.pojo.LoginInfo;
import com.lhh.demo.pojo.LoginRecord;
import com.lhh.demo.pojo.UserInfo;
import com.lhh.demo.service.LoginRecordService;
import com.lhh.demo.service.UserInfoService;
import com.lhh.demo.util.ResultInfo;
import com.lhh.demo.util.UserInfoUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@RequestMapping("user")
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoUtil userInfoUtil;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LoginRecordService loginRecordService;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @RequestMapping("onLogin")
    @ResponseBody
    public ResultInfo onLogin(String code, UserInfo userInfo) throws Exception {
        ResultInfo result = null;
        LoginInfo loginInfo = null;
        LoginRecord loginRecord = null;
        if (StringUtils.isEmpty(code)) {
            throw new NullCodeException("凭证为空");
        }
        loginInfo = userInfoUtil.getSessionKeyOrOpenId(code);
        //登录记录
        Timestamp loginDate = new Timestamp((new Date()).getTime());
        String dateString = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(loginDate);
        loginRecord = new LoginRecord(loginInfo.getOpenid(), dateString);

        //用户记录
        userInfo.setOpenId(loginInfo.getOpenid());
        UserInfo exitUserInfo = userInfoService.searchUserInfo(loginInfo.getOpenid());
        if (exitUserInfo == null) {
            userInfoService.addUserInfo(userInfo);
        }

        loginRecordService.addLoginRecord(loginRecord);

        //生成session3rdKey
//              String session3rdKey = UUID.randomUUID().toString().replaceAll("-", "");     //对本次登录用户生成服务sessionKey
        HttpSession session = userInfoUtil.getSession();
        if (session.getAttribute("userLoginInfo") != null) {
            session.removeAttribute("userLoginInfo");
        };
        session.setAttribute("userLoginInfo", loginInfo);      //app与服务器之间的sessionId映射app与微信服务器间的sessionkey
        result = ResultInfo.success("登录成功", session.getId());
        return result;
    }

    @RequestMapping("addCity")
    @ResponseBody
    public ResultInfo addCity(String city) throws Exception {
        ResultInfo result = null;
        LoginInfo loginInfo = userInfoUtil.checkSessionTimeOut();
        UserInfo exitUserInfo = userInfoService.searchUserInfo(loginInfo.getOpenid());
        if (exitUserInfo != null && exitUserInfo.getChooseCities().indexOf(city) == -1) {       //判断是否已有该城市记录
            String addString = "".equals(exitUserInfo.getChooseCities()) ? city : ("," + city);
            exitUserInfo.setChooseCities(exitUserInfo.getChooseCities() + addString);
            userInfoService.updateUserCitiesInfo(exitUserInfo);
            result = ResultInfo.success("添加用户偏爱城市成功");
        } else {
            result = ResultInfo.success("用户偏爱城市未发生变更");
        }
        return result;
    }

    @RequestMapping("deleteCity")
    @ResponseBody
    public ResultInfo deleteCity(String city) throws Exception {
        ResultInfo result = null;
        LoginInfo loginInfo = userInfoUtil.checkSessionTimeOut();
        UserInfo exitUserInfo = userInfoService.searchUserInfo(loginInfo.getOpenid());
        int cityIndex = exitUserInfo.getChooseCities().indexOf(city);
        if (exitUserInfo != null && cityIndex != -1) {       //判断是否已有该城市记录
            String replaceString;
            if (exitUserInfo.getChooseCities().length() == city.length()) {     //只有一个城市记录
                replaceString = city;
            } else {
                replaceString = cityIndex > 0 ? ("," + city) : (city + ",");    //有多个城市记录
            }
            //有该城市记录，根据城市未知设置替换字符串
            exitUserInfo.setChooseCities(exitUserInfo.getChooseCities().replace(replaceString, ""));
            userInfoService.updateUserCitiesInfo(exitUserInfo);
            result = ResultInfo.success("删除用户偏爱城市成功");
        } else {
            result = ResultInfo.success("用户偏爱城市未发生变更");
        }
        return result;
    }

    @RequestMapping("searchUser")
    @ResponseBody
    public ResultInfo searchUser(String openId) throws Exception{
        ResultInfo result = ResultInfo.success();
        UserInfo user= userInfoService.searchUserInfo(openId);
        result.setData(user);
        return result;
    }

}
