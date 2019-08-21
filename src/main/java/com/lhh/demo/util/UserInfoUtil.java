package com.lhh.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lhh.demo.ExceptionRe.TimeOutException;
import com.lhh.demo.pojo.LoginInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.net.util.URLUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserInfoUtil {

    public static String appId;
    public static String appSecret;
    public static String requestUrl;

    public LoginInfo getSessionKeyOrOpenId(String code) throws Exception{
            MultiValueMap<String,String> requestParams = new LinkedMultiValueMap<String, String>();       //参数
            requestParams.add("appid", appId);
            requestParams.add("secret", appSecret);
            requestParams.add("js_code", code);
            requestParams.add("grant_type","uthorization_code");
            RestTemplate httpClient = new RestTemplate();       //restful方式访问http模板
            HttpHeaders headers = new HttpHeaders();            //http头部
            HttpMethod method = HttpMethod.POST;                //http发送方式
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);  //http请求方式
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, headers);
            ResponseEntity<String> response = httpClient.exchange(requestUrl, method, requestEntity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            LoginInfo  loginInfo= mapper.readValue(response.getBody(), LoginInfo.class);
            return loginInfo;
    }

//    @Cacheable(value = "my-redis-cache1")
    public HttpSession getSession() {
        System.out.println("获取session");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return session;
    }

    public LoginInfo checkSessionTimeOut() throws TimeOutException{
        HttpSession session= getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("userLoginInfo");
        if (loginInfo == null) {
            throw new TimeOutException("登录超时，请重新登录");
        }
        return loginInfo;
    }
    @Value("${WXAppId}")
    public void setAppId(String appId) { UserInfoUtil.appId = appId; }
    @Value("${WXAppSecret}")
    public void setAppSecret(String appSecret) {
        UserInfoUtil.appSecret = appSecret;
    }
    @Value("${WXLoginRequestUrl}")
    public void setRequestUrl(String requestUrl) {
        UserInfoUtil.requestUrl = requestUrl;
    }
}
