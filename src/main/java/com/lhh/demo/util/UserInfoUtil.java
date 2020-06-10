package com.lhh.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lhh.demo.ExceptionRe.TimeOutException;
import com.lhh.demo.pojo.LoginInfo;
import com.lhh.demo.util.emsBean.EmsAddress;
import com.lhh.demo.util.emsBean.EmsCreateOrderRequest;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.net.io.SocketInputStream;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserInfoUtil {

    public static String appId;
    public static String appSecret;
    public static String requestUrl;

    public LoginInfo getSessionKeyOrOpenId(String code) throws Exception{
//        System.setProperty("proxyType", "4");
//        System.setProperty("proxyPort", Integer.toString(8080));
//        System.setProperty("proxyHost", "10.9.26.12");
//        System.setProperty("proxySet", "true");
//        System.setProperty("nonProxyHost", "*.byd.com.cn | *.bydhq.com.cn | *.bydsz.com.cn | 10.*.*.* | 192.168.*.");
//        SocketInputStream
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
    public static void main(String args[]) {
        FileInputStream in = null;
        String request = null;
        try {
            in = new FileInputStream("C:\\Users\\lai.huihui\\Desktop\\新建文本文档.txt");
            byte[] bs = new byte[4096];
            in.read(bs);
            request = new String(bs, "utf-8");
            System.out.println(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        EmsCreateOrderRequest orderRequest = JSON.parseObject(request, EmsCreateOrderRequest.class);
        System.out.println(orderRequest);
        emsCreateOrder(orderRequest);
    }

    public static void emsCreateOrder(EmsCreateOrderRequest request) {
        String requestBodyMsg = JSON.toJSONString(request);
        String url = "https://211.156.195.195/pcpErp-web/a/pcp/orderService/OrderReceiveBack";
        String key = "ks00217p647894Ak";
        MultiValueMap<String,String> requestParams = new LinkedMultiValueMap<String, String>();       //参数
        requestParams.add("logistics_interface", requestBodyMsg);
        requestParams.add("data_digest", md5AndBase64(requestBodyMsg + key));
        System.out.println(requestParams.getFirst("data_digest"));
        requestParams.add("msg_type","B2C_TRADE");
        requestParams.add("ecCompanyId", "90000002449155");
        requestParams.add("data_type", "JSON");
        RestTemplate httpClient = new RestTemplate();       //restful方式访问http模板
        HttpHeaders headers = new HttpHeaders();            //http头部
        HttpMethod method = HttpMethod.POST;                //http发送方式
        headers.setContentType(new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("utf-8")));  //http请求方式
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, headers);
        ResponseEntity<String> response = httpClient.exchange(url, method, requestEntity, String.class);
    }

    private static String md5AndBase64(String msg) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] md5 = md.digest(msg.getBytes());

//        System.out.println(mdResult);
        return Base64.encode(md5);
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
