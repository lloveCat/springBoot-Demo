package com.lhh.demo.util;

import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * Created by lai.huihui on 2020/6/8.
 * 百度翻译api工具类
 */
public class TranslateUtils {
    private static final String appid = "20200608000489807";
    private static final String apiKey = "ZIlGW7Gw5dHhwTRLUCHm";
    private static final String httpApiUrl = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    private static final String httpsApiUrl = "https://fanyi-api.baidu.com/api/trans/vip/translate";

    public static void translate(String source) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);// 设置超时
        requestFactory.setReadTimeout(10000);
        MultiValueMap<String,String> requestParams = new LinkedMultiValueMap<String, String>();       //参数
        requestParams.add("q", source);
        requestParams.add("from", "auto");
        requestParams.add("to", "en");
        requestParams.add("appid", appid);
        String salt = String.valueOf(System.currentTimeMillis());
        requestParams.add("salt", salt);
        String signBody = appid + source + salt + apiKey;
        requestParams.add("sign", MD5Util.MD5(signBody).toLowerCase());
        RestTemplate httpClient = new RestTemplate(requestFactory);       //restful方式访问http模板
        HttpHeaders headers = new HttpHeaders();            //http头部
        HttpMethod method = HttpMethod.POST;                //http发送方式
        headers.setContentType(new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("utf-8")));  //http请求方式
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, headers);
        ResponseEntity<String> response = httpClient.exchange(httpApiUrl, method, requestEntity, String.class);
        System.out.println(response.toString());
    }

    public static void main(String ars[]){
        translate("我的祖国--我是赖晖晖");
    }
}
